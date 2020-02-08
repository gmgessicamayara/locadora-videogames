package br.com.locadoravideogames.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Genero extends EntidadeGenerica {

	private static final long serialVersionUID = 1L;

	@Column(length = 20, nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
