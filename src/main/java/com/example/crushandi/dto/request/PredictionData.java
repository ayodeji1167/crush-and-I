package com.example.crushandi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PredictionData {

    @NotBlank
    @Size(min = 3, max = 225)
    private String userName;

    @NotBlank
    private String userGender;

    @NotBlank
    @Size(min = 3, max = 225)
    private String crushName;

    @NotBlank
    private String crushGender;


}
