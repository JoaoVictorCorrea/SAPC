package com.sapc.sapcandroid.controller;

public class Solicitacao {

    String usuario_externo_nome, data, tipo, status;
    int id, idExterno;

    public Solicitacao(String usuario_externo_nome, String data, String tipo, int id, String status, int idExterno) {
        this.usuario_externo_nome = usuario_externo_nome;
        this.data = data;
        this.tipo = tipo;
        this.id = id;
        this.idExterno = idExterno;
        this.status = status;
    }
}
