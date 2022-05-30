package com.example.crushandi.service;

import com.example.crushandi.dto.request.CreateAppUserRequest;
import com.example.crushandi.entity.AppUser;

import java.util.List;

public interface AppUserService {

    AppUser createNewUser(CreateAppUserRequest createUserRequest);

    List<AppUser> getAllEditors();

    void deleteUserById(String id);


}
