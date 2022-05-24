package com.example.crushandi.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class LoveCalculator {
    @NotBlank
    @Min(3)
    private String userName;

    @NotBlank
    @Min(3)
    private String userGender;

    @NotBlank
    @Min(3)
    private String crushName;

    @NotBlank
    @Min(3)
    private String crushGender;
}
