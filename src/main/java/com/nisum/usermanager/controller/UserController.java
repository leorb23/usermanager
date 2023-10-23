package com.nisum.usermanager.controller;

import com.nisum.usermanager.domain.UserRequest;
import com.nisum.usermanager.service.UserService;
import com.nisum.usermanager.validator.RegisterUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user-manager/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterUserRequestValidator requestValidator;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequest userRequest)
    {
        Map<String, Object> response = new HashMap<>();
        try {
            requestValidator.validate(userRequest);
            return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.OK);
        }
        catch (HttpClientErrorException e){
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<>(response, e.getStatusCode());
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
