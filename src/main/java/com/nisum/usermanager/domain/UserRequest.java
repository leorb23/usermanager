package com.nisum.usermanager.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}
