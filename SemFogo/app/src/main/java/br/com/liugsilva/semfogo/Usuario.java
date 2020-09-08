package br.com.liugsilva.semfogo;

import java.io.Serializable;

/**
 * Created by Luciano on 15/02/2016.
 */
public class Usuario implements Serializable {

    public static final String ID_USUARIO = "id_usuario";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";
    public static final String SENHA = "senha";
    public static final String ID_FACEBOOK = "id_facebook";
    public static final String PICTURE_FACEBOOK = "picture_facebook";

    private String id_usuario;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String id_facebook;
    private String picture_facebook;

    public Usuario(String id_usuario, String nome, String email, String telefone, String senha, String id_facebook, String picture_facebook) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.id_facebook = id_facebook;
        this.picture_facebook = picture_facebook;
    }

    public String getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(String id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdFacebook() {
        return id_facebook;
    }

    public void setIdFacebook(String id_facebook) {
        this.id_facebook = id_facebook;
    }

    public String getPictureFacebook() {
        return picture_facebook;
    }

    public void setPictureFacebook(String picture_facebook) {
        this.picture_facebook = picture_facebook;
    }
}
