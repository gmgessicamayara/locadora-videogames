package br.com.locadoravideogames.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.locadoravideogames.dao.ConsoleDAO;
import br.com.locadoravideogames.dao.GeneroDAO;
import br.com.locadoravideogames.dao.JogoDAO;
import br.com.locadoravideogames.entity.Console;
import br.com.locadoravideogames.entity.Genero;
import br.com.locadoravideogames.entity.Jogo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class JogoBean implements Serializable {
	private Jogo jogo;
	private List<Jogo> jogos;
	private List<Jogo> jogosAlugados = new ArrayList<Jogo>();
	private List<Genero> generos;
	private List<Console> consoles;

	public void novo() {
		try {
			jogo = new Jogo();

			ConsoleDAO consoleDAO = new ConsoleDAO();
			consoles = consoleDAO.listar();

			GeneroDAO generoDAO = new GeneroDAO();
			generos = generoDAO.listar();

			jogo.setDisponivel(true);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar um novo Jogo");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			JogoDAO jogoDAO = new JogoDAO();
			jogoDAO.merge(jogo);

			jogo = new Jogo();

			ConsoleDAO consoleDAO = new ConsoleDAO();
			consoles = consoleDAO.listar("nome");
			GeneroDAO generoDAO = new GeneroDAO();
			generos = generoDAO.listar("nome");

			jogos = jogoDAO.listar();

			Messages.addGlobalInfo("Jogo salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Jogo.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {

			jogo = (Jogo) evento.getComponent().getAttributes().get("jogoSelecionado");

			JogoDAO jogoDAO = new JogoDAO();
			jogoDAO.excluir(jogo);

			jogos = jogoDAO.listar();

			Messages.addGlobalInfo("Jogo excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o Jogo.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			jogo = (Jogo) evento.getComponent().getAttributes().get("jogoSelecionado");

			ConsoleDAO consoleDAO = new ConsoleDAO();
			consoles = consoleDAO.listar();
			GeneroDAO generoDAO = new GeneroDAO();
			generos = generoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao selecionar um  Genero / Console");
			erro.printStackTrace();
		}
	}

	public void devolver(ActionEvent evento) {
		try {

			jogo = (Jogo) evento.getComponent().getAttributes().get("jogoSelecionado");

			jogo.setDisponivel(true);
			JogoDAO jogoDAO = new JogoDAO();
			jogoDAO.merge(jogo);

			jogos = jogoDAO.listarOrdenado("nome");

			jogosAlugados = new ArrayList<Jogo>();
			for (Jogo jogo : jogos) {
				if (!jogo.getDisponivel()) {
					jogosAlugados.add(jogo);
				}
			}

			Messages.addGlobalInfo("Jogo devolvido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar devolver o jogo");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			JogoDAO jogoDAO = new JogoDAO();
			jogos = jogoDAO.listarOrdenado("nome");

			for (Jogo jogo : jogos) {
				if (!jogo.getDisponivel()) {
					jogosAlugados.add(jogo);
				}
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Jogos");
			erro.printStackTrace();
		}
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public List<Jogo> getJogosAlugados() {
		return jogosAlugados;
	}

	public void setJogosAlugados(List<Jogo> jogosAlugados) {
		this.jogosAlugados = jogosAlugados;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public List<Console> getConsoles() {
		return consoles;
	}

	public void setConsoles(List<Console> consoles) {
		this.consoles = consoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JogoBean other = (JogoBean) obj;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}

}
