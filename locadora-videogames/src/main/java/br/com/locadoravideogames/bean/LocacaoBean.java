package br.com.locadoravideogames.bean;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

import br.com.locadoravideogames.dao.ClienteDAO;
import br.com.locadoravideogames.dao.JogoDAO;
import br.com.locadoravideogames.dao.LocacaoDAO;
import br.com.locadoravideogames.entity.Cliente;
import br.com.locadoravideogames.entity.Jogo;
import br.com.locadoravideogames.entity.Locacao;
import br.com.locadoravideogames.entity.TituloLocacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LocacaoBean implements Serializable {

	private List<Jogo> jogos;
	private List<TituloLocacao> itensdaLocacao = new ArrayList<>();
	private List<Cliente> clientes;
	private List<Jogo> listaJogos = new ArrayList<Jogo>();
	List<TituloLocacao> titulosAlugados;
	private Locacao locacao;

	private int qtdDias;
	private BigDecimal precoParcial = new BigDecimal("0.00");

	@PostConstruct
	public void novo() {
		try {

			locacao = new Locacao();
			locacao.setDataLocacao(new Date());

			JogoDAO jogoDAO = new JogoDAO();
			jogos = jogoDAO.listar("nome");

			for (Jogo jogo : jogos) {
				if (jogo.getDisponivel()) {
					listaJogos.add(jogo);
				}
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a lista de Títulos");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Jogo jogo = (Jogo) evento.getComponent().getAttributes().get("jogoSelecionado");

		TituloLocacao tituloLocacao = new TituloLocacao();

		tituloLocacao.setJogo(jogo);
		itensdaLocacao.add(tituloLocacao);

		calcular();
		listaJogos.remove(jogo);
	}

	public void remover(ActionEvent evento) {

		TituloLocacao titulo = (TituloLocacao) evento.getComponent().getAttributes().get("jogoSelecionado");

		itensdaLocacao.remove(titulo);
		listaJogos.add(titulo.getJogo());

		calcular();

	}

	public void finalizar() {
		try {

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

			if (qtdDias == 1) {
				BigDecimal multiplicador = new BigDecimal(qtdDias);
				locacao.setPrecoTotal(precoParcial.multiply(multiplicador));
			} else if (qtdDias == 2) {
				BigDecimal multiplicador = new BigDecimal(qtdDias);
				locacao.setPrecoTotal(precoParcial.multiply(multiplicador));
			} else if (qtdDias == 3) {
				BigDecimal multiplicador = new BigDecimal(qtdDias);
				locacao.setPrecoTotal(precoParcial.multiply(multiplicador));
				
			}


		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar concluir a locação.");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			
			if(itensdaLocacao.isEmpty()) {
				Messages.addGlobalError("Informe um jogo para a locação!");
				return;
			}else {
				
			LocacaoDAO locacaoDAO = new LocacaoDAO();
			locacaoDAO.salvar(locacao, itensdaLocacao);
			
			for(int i  = 0; i < itensdaLocacao.size(); i ++) {
				Jogo jogo = itensdaLocacao.get(i).getJogo();
				jogo.setDisponivel(false);
				JogoDAO jogoDAO = new JogoDAO();
				jogoDAO.merge(jogo);
				 
				}
			
			Messages.addGlobalInfo("Locação realizada com sucesso!");
			itensdaLocacao = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Selecione um jogo");
			erro.printStackTrace();
		}
	}

	public void calcular() {

		setPrecoParcial(new BigDecimal("0.00"));

		for (int i = 0; i < itensdaLocacao.size(); i++) {
			TituloLocacao tituloLocacao = itensdaLocacao.get(i);

			setPrecoParcial(precoParcial.add(tituloLocacao.getJogo().getPrecoLocacao()));

		}

	}
	
	

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public List<TituloLocacao> getItensdaLocacao() {
		return itensdaLocacao;
	}

	public void setItensdaLocacao(List<TituloLocacao> itensdaLocacao) {
		this.itensdaLocacao = itensdaLocacao;
	}

	public List<Jogo> getListaJogos() {
		return listaJogos;
	}

	public void setListaJogos(List<Jogo> listaJogos) {
		this.listaJogos = listaJogos;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	
	
	public List<TituloLocacao> getTitulosAlugados() {
		return titulosAlugados;
	}

	public void setTitulosAlugados(List<TituloLocacao> titulosAlugados) {
		this.titulosAlugados = titulosAlugados;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}

	public int getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(int qtdDias) {
		this.qtdDias = qtdDias;
	}

}
