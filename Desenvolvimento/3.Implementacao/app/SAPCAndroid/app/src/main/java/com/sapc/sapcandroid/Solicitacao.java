package com.sapc.sapcandroid;

public class Solicitacao {

    String usuario_externo_nome, data, tipo, status;
    int id;

    public Solicitacao(String usuario_externo_nome, String data, String tipo, int id, String status) {
        this.usuario_externo_nome = usuario_externo_nome;
        this.data = data;
        this.tipo = tipo;
        this.id = id;
        this.status = status;
    }
}
