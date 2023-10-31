package com.JavaCaldeiraGC.todoservice;

import java.util.Date;

public class Task {
    private int id;
    private String descricao;
    private java.util.Date    dataDeVenvimento;
    private boolean acaoCompleta;

    public Task(int id, String descricao, Date dataDeVenvimento, boolean acaoCompleta) {
        this.id = id;
        this.descricao = descricao;
        this.dataDeVenvimento = dataDeVenvimento;
        this.acaoCompleta = acaoCompleta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDeVenvimento() {
        return dataDeVenvimento;
    }

    public void setDataDeVenvimento(Date dataDeVenvimento) {
        this.dataDeVenvimento = dataDeVenvimento;
    }

    public boolean isAcaoCompleta() {
        return acaoCompleta;
    }

    public void setAcaoCompleta(boolean acaoCompleta) {
        this.acaoCompleta = acaoCompleta;
    }
}
