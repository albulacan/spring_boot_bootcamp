package com.springboot.bootcamp.controller;

import com.springboot.bootcamp.model.Role;
import com.springboot.bootcamp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Role> roles = new ArrayList<>();
        try {
            roles = this.roleService.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("roles", roles);
        model.addAttribute("role", new Role());
        return "role-list";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Role role, Model model) {
        String message = "";
        try {
            Role tmpRole = this.roleService.findByName(role.getName());
            if (tmpRole == null) {
                this.roleService.save(role);
            } else {
                message = "Role already exists!";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("message", message);
        return "redirect:/role/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        try {
            Optional<Role> role = this.roleService.findById(id);
            if (role.isPresent()) {
                model.addAttribute("role", role.get());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "update-role";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute Role role) {
        try {
            Optional<Role> tmpRole = this.roleService.findById(id);
            if (tmpRole.isPresent()) {
                role.setId(id);
                this.roleService.save(role);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/role/list";
    }
}
