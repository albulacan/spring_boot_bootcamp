package com.springboot.bootcamp.repository;

import com.springboot.bootcamp.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query(value = "select t from ToDo t where t.name = ?1")
    Optional<ToDo> findByName(String name);

}
