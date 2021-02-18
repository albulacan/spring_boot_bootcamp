package com.springboot.bootcamp.controller;

import com.springboot.bootcamp.model.ToDo;
import com.springboot.bootcamp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class ToDoRestController {

    private ToDoService toDoService;

    @Autowired
    public ToDoRestController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/")
    ResponseEntity<List<ToDo>> getAll() {
        List<ToDo> toDos = this.toDoService.findAll();
        return new ResponseEntity<>(toDos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ToDo> getById(@PathVariable("id") long id) {
        Optional<ToDo> toDo = this.toDoService.findById(id);
        if (toDo.isPresent()) {
            return new ResponseEntity<>(toDo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    ResponseEntity<ToDo> add(@RequestBody ToDo toDo) {
        Optional<ToDo> tmpToDo = this.toDoService.findByName(toDo.getName());
        if (tmpToDo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            toDo.setId(0);
            toDo.setCreatedOn();
            this.toDoService.save(toDo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<ToDo> update(@PathVariable("id") long id, @RequestBody ToDo toDo) {
        Optional<ToDo> tmpToDo = this.toDoService.findById(id);
        if (tmpToDo.isPresent()) {
            toDo.setId(id);
            toDo.setCreatedOn(tmpToDo.get().getCreatedOn());
            this.toDoService.save(toDo);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ToDo> update(@PathVariable("id") long id) {
        Optional<ToDo> tmpToDo = this.toDoService.findById(id);
        if (tmpToDo.isPresent()) {
            this.toDoService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
