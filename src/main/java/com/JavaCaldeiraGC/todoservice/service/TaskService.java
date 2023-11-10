package com.JavaCaldeiraGC.todoservice.service;

import com.JavaCaldeiraGC.todoservice.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task editTask(Long id, Task tarefaAtualizada) {
        buscarTarefaPeloId(id).ifPresent(tarefaExistente -> {
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setDataDeVencimento(tarefaAtualizada.getDataDeVencimento());
            tarefaExistente.setAcaoCompleta((tarefaAtualizada.isAcaoCompleta()));
        });
        return tarefaAtualizada;
    }

    public void deleteTask(Long id) {
        buscarTarefaPeloId(id).ifPresent(tasks::remove);
    }

    public Optional<Task> buscarTarefaPeloId(Long id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }
}


