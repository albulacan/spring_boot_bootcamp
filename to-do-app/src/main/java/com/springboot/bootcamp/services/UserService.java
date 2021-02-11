package com.springboot.bootcamp.services;

import com.springboot.bootcamp.configs.DatabaseConfig;
import com.springboot.bootcamp.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    
    @Autowired
    private DatabaseConfig dbConfig;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Juan", "Dela Cruz", "Dyan lang"));
        users.add(new User("Maria", "Clara", "Dyan lang"));

        System.out.println(this.dbConfig.getServer());

        return users;
    }
}
