package com.JavaCaldeiraGC.todoservice.controller;

import com.JavaCaldeiraGC.todoservice.model.Task;
import com.JavaCaldeiraGC.todoservice.service.TaskService;
import excepiton.PresonalizacaoErro;
import excepiton.RespostaErro;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@RestController//é usado para criar um controlador
@RequestMapping("/tasks")//a classe ou o método marcado com
// essa anotação responderá a solicitações feitas para a  url "/tasks"
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    RespostaErro respostaErro = new RespostaErro();
    PresonalizacaoErro presonalizacaoErro = new PresonalizacaoErro();

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    // Isso significa que quando alguém fizer uma solicitação POST para o URL que termina em "/add",
    // esse método será chamado para processar a solicitação.
    public ResponseEntity addTask(@Valid @RequestBody Task task, BindingResult result) {
        //ResponseEntity é uma classe usada em
        //Spring para encapsular a resposta HTTP retornada pelo método.

        if (result.hasErrors()) {
            respostaErro.setMessagem("Preencha o campo corretamente!");
            respostaErro.setCodigo(HttpStatus.BAD_REQUEST.value());
            respostaErro.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

            List<PresonalizacaoErro> postListaDeErros = new ArrayList<>();

            result.getFieldErrors().forEach(fieldError -> {
                presonalizacaoErro.setMensagem(fieldError.getDefaultMessage());
                postListaDeErros.add(presonalizacaoErro);
            });

            respostaErro.setListaerros(postListaDeErros);
            return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
        }
        taskService.addTask(task);
        return ResponseEntity.ok("Sucesso!");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity edicaoTarefa(@PathVariable Long id, @RequestBody Map<String, Object> update) {
        Optional<Task> tarefaParaEdicao = taskService.buscarTarefaPeloId(id);

        if (tarefaParaEdicao.isPresent()) {
            Task tarefaExistente = tarefaParaEdicao.get();
            for (Map.Entry<String, Object> entry : update.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                try {
                    Field taskField = Task.class.getDeclaredField(field);
                    taskField.setAccessible(true);
                    taskField.set(tarefaExistente,
                            value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    respostaErro.setMessagem("Erro ao editar a tarefa!");
                    respostaErro.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    respostaErro.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                    presonalizacaoErro.setMensagem("Erro ao editar o campo: " + field);
                    respostaErro.getListaerros().add(presonalizacaoErro);
                    return new ResponseEntity<>(respostaErro, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            taskService.editTask(id, tarefaExistente);
            return ResponseEntity.ok(tarefaExistente);
        } else {
            respostaErro.setMessagem("Tarefa " + id + " não encontrada");
            respostaErro.setCodigo(HttpStatus.NOT_FOUND.value());
            respostaErro.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());

            return new ResponseEntity<>(respostaErro, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarTask(@PathVariable Long id) {
        Optional<Task> deletarTarefa = taskService.buscarTarefaPeloId(id);
        if (deletarTarefa.isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Sucesso!");
        } else {
            respostaErro.setMessagem("Tarefa não não encontrada!");
            respostaErro.setCodigo(HttpStatus.NOT_FOUND.value());
            respostaErro.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());

            return new ResponseEntity<>(respostaErro, HttpStatus.NOT_FOUND);
        }
    }
}

