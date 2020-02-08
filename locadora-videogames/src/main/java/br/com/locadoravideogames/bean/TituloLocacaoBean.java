package br.com.locadoravideogames.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.management.RuntimeMBeanException;

import org.omnifaces.util.Messages;

 
import br.com.locadoravideogames.dao.TituloLocacaoDAO;
 
import br.com.locadoravideogames.entity.Locacao;
import br.com.locadoravideogames.entity.TituloLocacao;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TituloLocacaoBean implements Serializable{
	private Locacao locacao;
	private List<TituloLocacao> locacoes;
	
	private List<TituloLocacao> locacoesIndisponiveis;
	
	@PostConstruct
	public void listar() {
		try {
			TituloLocacaoDAO tituloLocacaoDAO = new TituloLocacaoDAO();
			locacoes = tituloLocacaoDAO.listar();
	
			
		}catch (RuntimeMBeanException erro) {
			Messages.addGlobalError("Ocorrreu um erro ao listar as locações");
			erro.printStackTrace();
		}
	}

	public Locacao getLocacao() {
		return locacao;
	}
	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}
	
	public List<TituloLocacao> getLocacoes() {
		return locacoes;
	}


	public void setLocacoes(List<TituloLocacao> locacoes) {
		this.locacoes = locacoes;
	}


	public List<TituloLocacao> getLocacoesIndisponiveis() {
		return locacoesIndisponiveis;
	}

	public void setLocacoesIndisponiveis(List<TituloLocacao> locacoesIndisponiveis) {
		this.locacoesIndisponiveis = locacoesIndisponiveis;
	}

}
