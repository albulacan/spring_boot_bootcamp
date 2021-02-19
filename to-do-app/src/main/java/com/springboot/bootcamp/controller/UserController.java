package com.springboot.bootcamp.controller;

import com.springboot.bootcamp.model.Role;
import com.springboot.bootcamp.model.ToDo;
import com.springboot.bootcamp.model.User;
import com.springboot.bootcamp.service.RoleService;
import com.springboot.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        try {
            users = this.userService.findAll();
            roles = this.roleService.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "user-list";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute User user, Model model) {
        String message = "";
        try {
            User tmpUser = this.userService.findByUserName(user.getUsername());
            if (tmpUser == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                this.userService.save(user);
            } else {
                message = "Username already exists!";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("message", message);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        try {
            Optional<User> user = this.userService.findById(id);
            if (user.isPresent()) {
                model.addAttribute("user", user.get());
                model.addAttribute("roles", this.roleService.findAll());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute User user) {
        try {
            Optional<User> tmpUser = this.userService.findById(id);
            if (tmpUser.isPresent()) {
                user.setId(id);
                user.setUsername(tmpUser.get().getUsername());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                this.userService.save(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/user/list";
    }

}
