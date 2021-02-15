package com.springboot.bootcamp.services;

import com.springboot.bootcamp.configs.DatabaseConfig;
import com.springboot.bootcamp.models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ToDoService {

    @Autowired
    private DatabaseConfig dbConfig;

    public List<ToDo> getAll() {
        List<ToDo> toDos = new ArrayList<>();
        try {
            toDos.add(new ToDo(1, "Check Emails", "Work and personal emails"));
            toDos.add(new ToDo(2, "Buy Food", "Lunch and Dinner"));
            toDos.add(new ToDo(3, "Take a Bath", "Every day"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return toDos;
    }

    public void add(ToDo toDo) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(ToDo toDo) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(long id) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
