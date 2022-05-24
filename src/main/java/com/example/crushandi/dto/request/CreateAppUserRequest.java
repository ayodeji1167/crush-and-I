package com.example.crushandi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateAppUserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
