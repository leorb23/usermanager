package com.nisum.usermanager.service;

import com.nisum.usermanager.data.entity.PhoneEntity;
import com.nisum.usermanager.data.entity.UserEntity;
import com.nisum.usermanager.data.repository.IPhoneRepository;
import com.nisum.usermanager.data.repository.IUserRepository;
import com.nisum.usermanager.domain.Phone;
import com.nisum.usermanager.domain.UserRequest;
import com.nisum.usermanager.domain.UserResponse;
import com.nisum.usermanager.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final String EMAIL_ALREADY_EXISTS = "Ya existe un usuario registrado con este correo.";
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPhoneRepository phoneRepository;

    public UserResponse saveUser(UserRequest userRequest) throws UnsupportedEncodingException {
        if(emailExists(userRequest.getEmail())){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, EMAIL_ALREADY_EXISTS);
        }
        else{
            UserResponse userResponse = new UserResponse();

            UserEntity userEntity = new UserEntity();

            userEntity.setId(UUID.randomUUID());
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setName(userRequest.getName());
            userEntity.setPassword(UserUtil.encrypt(userRequest.getPassword()));
            userEntity.setActive(true);
            userEntity.setCreated(LocalDate.now());
            userEntity.setModified(LocalDate.now());
            userEntity.setLastLogin(LocalDate.now());
            userEntity.setToken(UserUtil.createJwt());

            userRepository.save(userEntity);

            List<Phone> requestPhones = userRequest.getPhones();

            List<Phone> responsePhones = new ArrayList<>();

            List<PhoneEntity> userPhones = new ArrayList<>();

            requestPhones.stream().forEach(phone -> {
                PhoneEntity userPhone = new PhoneEntity();
                userPhone.setNumber(phone.getNumber());
                userPhone.setCityCode(phone.getCityCode());
                userPhone.setCountryCode(phone.getCountryCode());
                userPhone.setUser(userEntity);
                phoneRepository.save(userPhone);

                userPhones.add(userPhone);

                Phone phoneForUser = new Phone();

                phoneForUser.setId(userPhone.getId());
                phoneForUser.setNumber(userPhone.getNumber());
                phoneForUser.setCityCode(userPhone.getCityCode());
                phoneForUser.setCountryCode(userPhone.getCountryCode());

                responsePhones.add(phoneForUser);
            });

            userResponse.setId(userEntity.getId());
            userResponse.setCreated(userEntity.getCreated());
            userResponse.setModified(userEntity.getModified());
            userResponse.setLastLogin(userEntity.getLastLogin());
            userResponse.setPhones(responsePhones);
            userResponse.setToken(userEntity.getToken());
            userResponse.setActive(userEntity.isActive());

            return userResponse;
        }
    }

    private boolean emailExists(String email)
    {
        return userRepository.findByEmail(email) != null;
    }
}
