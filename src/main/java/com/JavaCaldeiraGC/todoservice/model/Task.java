package com.JavaCaldeiraGC.todoservice.model;

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


    private String descricao;

    private Date dataDeVencimento;


    private boolean acaoCompleta;


    public Task(String descricao, Date dataDeVencimento, boolean acaoCompleta) {
        this.id = incrementadorDoId.incrementAndGet();
        this.descricao = descricao;
        this.dataDeVencimento = dataDeVencimento;
        this.acaoCompleta = acaoCompleta;
    }

}
