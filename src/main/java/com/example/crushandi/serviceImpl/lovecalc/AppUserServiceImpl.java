package com.example.crushandi.serviceImpl.lovecalc;

import com.example.crushandi.dto.request.CreateAppUserRequest;
import com.example.crushandi.entity.AppUser;
import com.example.crushandi.entity.Role;
import com.example.crushandi.exception.BlogPostException;
import com.example.crushandi.repository.AppUserRepository;
import com.example.crushandi.service.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public AppUser createNewUser(CreateAppUserRequest createUserRequest) {
        for (AppUser appUser : getAllEditors()) {
            if (appUser.getUserName().equals(createUserRequest.getUserName())) {
                throw new BlogPostException("AppUser with name " + createUserRequest.getUserName() + " already exist");
            }
        }

        AppUser appUser = new AppUser();
        appUser.setUserName(createUserRequest.getUserName());
        appUser.setFirstName(createUserRequest.getFirstName());
        appUser.setLastName(createUserRequest.getLastName());
        appUser.setEmail(createUserRequest.getEmail());
        appUser.setPassword(createUserRequest.getPassword());
        appUser.setRole(Role.EDITOR);
        return appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> getAllEditors() {
        List<AppUser> appUserList = appUserRepository.findAll();
        return appUserList.stream().filter(appUser -> appUser.getRole().equals(Role.EDITOR)).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        appUserRepository.deleteById(id);
    }
}
