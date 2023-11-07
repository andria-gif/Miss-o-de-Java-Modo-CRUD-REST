package com.JavaCaldeiraGC.todoservice.model;


import lombok.Data;

@Data
public class MensagemErro {
    private String mensagem;

    public MensagemErro(String mensagem) {
        this.mensagem = mensagem;
    }
}
