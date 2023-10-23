package com.nisum.usermanager.validator;

import com.nisum.usermanager.domain.Phone;
import com.nisum.usermanager.domain.UserRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterUserRequestValidator {
    private static final String WRONG_EMAIL_FORMAT = "Formato incorrecto para email";
    private static final String EMPTY_NAME = "El nombre no puede ser vacío";
    private static final String EMPTY_EMAIL = "El email no puede ser vacío";
    private static final String EMPTY_PASSWORD = "El password no puede ser vacío";
    private static final String EMPTY_PHONE_LIST = "La lista de telefonos está vacía";
    private static final String NULL_NAME = "El nombre es requerido";
    private static final String NULL_EMAIL = "El email es requerido";
    private static final String NULL_PASSWORD = "El password es requerido";
    private static final String NULL_PHONE_LIST = "La lista de telefonos es requerida";

    @Value("${regex.password}")
    private String regex;
    @Value("${regex.message}")
    private String regexMessage;
    public void validate(UserRequest userRequest){
        isNullName(userRequest.getName());
        isNullEmail(userRequest.getEmail());
        isNullPassword(userRequest.getPassword());
        isNullPhoneList(userRequest.getPhones());
        isEmptyName(userRequest.getName());
        isEmptyEmail(userRequest.getEmail());
        isEmptyPassword(userRequest.getPassword());
        isEmptyPhoneList(userRequest.getPhones());
        isValidEmail(userRequest.getEmail());
        isValidPassword(userRequest.getPassword());
    }
    private void isNullName(String name){
        if(name == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, NULL_NAME);
    }
    private void isNullEmail(String email){
        if(email == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, NULL_EMAIL);
    }
    private void isNullPassword(String password){
        if(password == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, NULL_PASSWORD);
    }
    private void isNullPhoneList(List<Phone> phones){
        if(phones == null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, NULL_PHONE_LIST);
    }
    private void isEmptyName(String name){
        if(name.isEmpty()) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, EMPTY_NAME);
    }
    private void isEmptyEmail(String email){
        if(email.isEmpty()) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, EMPTY_EMAIL);
    }
    private void isEmptyPassword(String password){
        if(password.isEmpty()) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, EMPTY_PASSWORD);
    }
    private void isEmptyPhoneList(List<Phone> phones){
        if(phones.isEmpty()) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, EMPTY_PHONE_LIST);
    }
    private void isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$");
        Matcher mather = pattern.matcher(email);
        if(!mather.find()) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, WRONG_EMAIL_FORMAT);
    }
    private void isValidPassword(String password){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.find()) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, regexMessage);
    }

}
