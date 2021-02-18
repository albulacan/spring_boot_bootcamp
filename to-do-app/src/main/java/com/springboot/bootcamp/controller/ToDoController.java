package com.springboot.bootcamp.controller;

import com.springboot.bootcamp.model.ToDo;
import com.springboot.bootcamp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/todo")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<ToDo> toDos = new ArrayList<>();
        try {
            toDos = this.toDoService.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("todos", toDos);
        model.addAttribute("toDo", new ToDo());
        return "list";
    }

    @GetMapping("/create")
    public String create(ToDo toDo) {
        return "create";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute ToDo toDo) {
        try {
            toDo.setCreatedOn();
            this.toDoService.save(toDo);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/todo/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        try {
            Optional<ToDo> toDo = this.toDoService.findById(id);
            if (toDo.isPresent()) {
                model.addAttribute("toDo", toDo.get());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute ToDo toDo) {
        try {
            Optional<ToDo> tmpToDo = this.toDoService.findById(id);
            if (tmpToDo.isPresent()) {
                toDo.setCreatedOn(tmpToDo.get().getCreatedOn());
            }
            this.toDoService.save(toDo);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/todo/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        try {
            this.toDoService.delete(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/todo/list";
    }

}
