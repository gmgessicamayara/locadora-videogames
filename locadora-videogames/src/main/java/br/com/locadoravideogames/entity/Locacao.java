package br.com.locadoravideogames.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Locacao extends EntidadeGenerica {

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLocacao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	@Column(precision = 7, scale = 2, nullable = false)
	private BigDecimal precoTotal;

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

}
