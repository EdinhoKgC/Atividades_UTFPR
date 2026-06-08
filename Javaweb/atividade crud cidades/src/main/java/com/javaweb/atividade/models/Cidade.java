package com.javaweb.atividade.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public final class Cidade {
    @NotBlank(message = "{app.cidade.blank}")
    @Size(min = 3, max = 60, message = "{app.cidade.size}")
    private final String nome;

    @NotBlank(message = "{app.uf.blank}")
    @Size(min = 2, max = 2, message = "{app.uf.size}")
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
