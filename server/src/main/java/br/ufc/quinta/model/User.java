package br.ufc.quinta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@Column(columnDefinition = "text")
	private String matricula;
	@Column(columnDefinition = "text")
	private String nome;
	@Column(columnDefinition = "text")
	private String email;
	@Column(columnDefinition = "text")
	private String curso;
	@Column(columnDefinition = "text")
	private String senha;

	public User(String matricula, String nome, String email, String curso, String senha) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.curso = curso;
		this.senha = senha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
		return "User [matricula=" + matricula + ", nome=" + nome + ", email=" + email + ", curso=" + curso + ", senha="
				+ senha + "]";
	}

}
