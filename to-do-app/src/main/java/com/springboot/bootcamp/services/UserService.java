package com.springboot.bootcamp.services;

import com.springboot.bootcamp.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Juan", "Dela Cruz", "Dyan lang"));
        users.add(new User("Maria", "Clara", "Dyan lang"));
        return users;
    }
}
