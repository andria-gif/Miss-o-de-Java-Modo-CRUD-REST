package com.JavaCaldeiraGC.todoservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController//é usado para criar um controlador
@RequestMapping("/tasks")//a classe ou o método marcado com
// essa anotação responderá a solicitações feitas para a  url "/tasks"
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
//usada para armazenar tarefas que podem ser
// recuperadas ou modificadas por meio das solicitações HTTP.

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }
    @PostMapping("/add")//. Isso significa que quando alguém fizer uma solicitação POST para o URL que termina em "/add",
    // esse método será chamado para processar a solicitação.
    public ResponseEntity<String> addTask(@RequestBody Task task) { //ResponseEntity é uma classe usada em
        // Spring para encapsular a resposta HTTP retornada pelo método.
        tasks.add(task);
        return new ResponseEntity<>("Task adicionada com sucesso", HttpStatus.CREATED);
    }
}
