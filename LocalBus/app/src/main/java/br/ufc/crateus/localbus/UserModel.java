package br.ufc.crateus.localbus;

import android.text.Editable;

public class UserModel {
    private String email;
    private String nome;
    private String matricula;
    private String curso;
    private String senha;

    public UserModel(String nome, String email, String matricula, String curso, String senha) {
        this.email = email;
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", matricula=" + matricula +
                ", curso='" + curso + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

}
