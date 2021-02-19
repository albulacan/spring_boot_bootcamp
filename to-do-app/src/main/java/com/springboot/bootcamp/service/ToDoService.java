package com.springboot.bootcamp.service;

import com.springboot.bootcamp.model.ToDo;
import com.springboot.bootcamp.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> findAll() {
       return this.toDoRepository.findAll();
    }

    public Optional<ToDo> findById(long id) {
        return this.toDoRepository.findById(id);
    }

    public Optional<ToDo> findByName(String name) {
        return this.toDoRepository.findByName(name);
    }

    public void save(ToDo toDo) {
        this.toDoRepository.save(toDo);
    }

    public void delete(long id) {
        this.toDoRepository.deleteById(id);
    }
}
