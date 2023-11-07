package com.JavaCaldeiraGC.todoservice.controller;

import com.JavaCaldeiraGC.todoservice.model.Task;
import com.JavaCaldeiraGC.todoservice.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController//é usado para criar um controlador
@RequestMapping("/tasks")//a classe ou o método marcado com
// essa anotação responderá a solicitações feitas para a  url "/tasks"
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    // Isso significa que quando alguém fizer uma solicitação POST para o URL que termina em "/add",
    // esse método será chamado para processar a solicitação.
    public Task addTask (@RequestBody Task task) {
        //ResponseEntity é uma classe usada em
        //Spring para encapsular a resposta HTTP retornada pelo método.
        return taskService.addTask(task);

    }
}
