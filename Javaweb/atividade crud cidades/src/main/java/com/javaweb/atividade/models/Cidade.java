package com.javaweb.atividade.models;

public final class Cidade {

    private final String nome;
    private final String uf;

    public Cidade(final String nome, final String uf){
        this.nome = nome;
        this.uf = uf;
    }
    
    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

}
