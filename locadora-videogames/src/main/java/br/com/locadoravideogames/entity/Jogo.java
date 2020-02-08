package br.com.locadoravideogames.entity;

import java.beans.Transient;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Jogo extends EntidadeGenerica {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Console console;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Genero genero;

	@Column(length = 60, nullable = false)
	private String nome;

	@Column(length = 200, nullable = true)
	private String descricao;

	@Column(precision = 7, scale = 2, nullable = false)
	private BigDecimal precoLocacao;

	@Column(nullable = false)
	private Boolean disponivel;

	@Transient
	public String getDisponivelFormatado() {
		String disponivelFormatado = null;

		if (disponivel == true) {
			disponivelFormatado = "Disponível";
		} else {
			disponivelFormatado = "Indisponível";
		}
		return disponivelFormatado;

	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoLocacao() {
		return precoLocacao;
	}

	public void setPrecoLocacao(BigDecimal precoLocacao) {
		this.precoLocacao = precoLocacao;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

}
