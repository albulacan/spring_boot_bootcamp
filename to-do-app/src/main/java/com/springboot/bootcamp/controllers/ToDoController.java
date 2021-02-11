package com.springboot.bootcamp.controllers;

import com.springboot.bootcamp.models.ToDo;
import com.springboot.bootcamp.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/todo")
public class ToDoController {

    private ToDoService toDoService;
    private List<ToDo> toDos;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
        this.toDos = this.toDoService.getAll();
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("todos", this.toDos);
        model.addAttribute("selectedId", 0);
        return "list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("toDo", new ToDo());
        return "create";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute ToDo toDo) {
        long id = this.toDos.stream().max(Comparator.comparing(ToDo::getId)).get().getId() + 1;
        toDo.setId(id);
        toDo.setCreatedOn();
        this.toDos.add(toDo);
        return "redirect:/todo/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        ToDo toDo = this.toDos.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(null);
        model.addAttribute("toDo", toDo);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute ToDo toDo) {
        this.toDos = this.toDos.stream().map(x -> {
            if (x.getId() == id) {
                toDo.setCreatedOn(x.getCreatedOn());
                return toDo;
            }
            return x;
        }).collect(Collectors.toList());
        return "redirect:/todo/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        this.toDos.removeIf(x -> x.getId() == id);
        return "redirect:/todo/list";
    }

}
