package com.example.crushandi.controller;

import com.example.crushandi.dto.request.CreateAppUserRequest;
import com.example.crushandi.entity.AppUser;
import com.example.crushandi.service.AppUserService;
import com.example.crushandi.service.BlogPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    private final AppUserService appUserService;
    private final BlogPostService blogPostService;

    public AppUserController(AppUserService appUserService, BlogPostService blogPostService) {
        this.appUserService = appUserService;
        this.blogPostService = blogPostService;
    }

    @PostMapping("/save/editor")
    public AppUser registerEditor(@RequestBody CreateAppUserRequest createAppUserRequest) {
        return appUserService.createNewUser(createAppUserRequest);
    }

    @GetMapping("/get/all")
    public List<AppUser> getAllEditors() {
        return appUserService.getAllEditors();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEditor(@PathVariable String id) {
        blogPostService.deleteAllPostByUserId(id);
        appUserService.deleteUserById(id);
    }
}
