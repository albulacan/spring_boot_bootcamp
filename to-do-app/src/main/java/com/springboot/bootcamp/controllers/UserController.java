package com.springboot.bootcamp.controllers;

import com.springboot.bootcamp.configs.DatabaseConfig;
import com.springboot.bootcamp.models.User;
import com.springboot.bootcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private DatabaseConfig dbConfig;
    private UserService userService;

    @Autowired
    public UserController(DatabaseConfig dbConfig, UserService userService) {
        this.dbConfig = dbConfig;
        this.userService = userService;
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getUsers();

        System.out.println(this.dbConfig.getName());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
