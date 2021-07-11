package com.tolulope.photoappapiuser.model;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
