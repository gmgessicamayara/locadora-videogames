package br.com.locadoravideogames.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class TituloLocacao extends EntidadeGenerica {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Jogo jogo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Locacao locacao;

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

}
