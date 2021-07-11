package com.tolulope.photoappapiuser.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserResponseModel {

    private String firstName;

    private String lastName;

    private String password;

    private String email;
}
