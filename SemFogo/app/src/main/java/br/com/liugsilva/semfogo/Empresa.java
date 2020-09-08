package br.com.liugsilva.semfogo;

import java.io.Serializable;

/**
 * Created by Luciano on 15/02/2016.
 */
public class Empresa implements Serializable {

    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";
    public static final String ENDERECO = "cidade";
    public static final String MENSAGEM = "mensagem";

    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String mensagem;

    public Empresa(String nome, String email, String telefone, String endereco, String mensagem) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
