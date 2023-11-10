package com.JavaCaldeiraGC.todoservice.model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class Task {
    private static final AtomicLong incrementadorDoId = new AtomicLong(0L);
    private long id;


    @NotEmpty(message = "Por favor preencha o campo conforme o solicitado ")
    private String descricao;

    @NotNull(message = "Por favor preencha o campo conforme o solicitado ")
    @FutureOrPresent //regula a data de vencimento
    private Date dataDeVencimento;

    @AssertFalse(message = "Por favor preencha o campo conforme o solicitado ")
    private boolean acaoCompleta;


    public Task(String descricao, Date dataDeVencimento, boolean acaoCompleta) {
        this.id = incrementadorDoId.incrementAndGet();
        this.descricao = descricao;
        this.dataDeVencimento = dataDeVencimento;
        this.acaoCompleta = acaoCompleta;
    }

}
