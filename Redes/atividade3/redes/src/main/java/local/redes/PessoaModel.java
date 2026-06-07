package local.redes;

import java.io.Serializable;

// @author Edilson Torres

public class PessoaModel implements Serializable{

    private String nome;
    private int idade;

    public PessoaModel(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
