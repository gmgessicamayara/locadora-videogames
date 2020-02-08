package br.com.locadoravideogames.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Console extends EntidadeGenerica {

	@Column(length = 20, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String fabricante;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

}
