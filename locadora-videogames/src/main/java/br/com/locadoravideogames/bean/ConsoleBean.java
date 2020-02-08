package br.com.locadoravideogames.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.locadoravideogames.dao.ConsoleDAO;
import br.com.locadoravideogames.entity.Console;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class ConsoleBean implements Serializable {

	private Console console;
	private List<Console> consoles;

	public void novo() {
		console = new Console();
	}

	public void salvar() {
		try {
			ConsoleDAO consoleDAO = new ConsoleDAO();
			consoleDAO.merge(console);
			console = new Console();
			consoles = consoleDAO.listar();

			Messages.addFlashGlobalInfo("Console salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o Console.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {

			console = (Console) evento.getComponent().getAttributes().get("consoleSelecionado");
			ConsoleDAO consoleDAO = new ConsoleDAO();
			consoleDAO.excluir(console);

			consoles = consoleDAO.listar();

			Messages.addGlobalInfo("Console excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o Console.");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			ConsoleDAO consoleDAO = new ConsoleDAO();
			consoles = consoleDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Consoles");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		console = (Console) evento.getComponent().getAttributes().get("consoleSelecionado");

	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public List<Console> getConsoles() {
		return consoles;
	}

	public void setConsoles(List<Console> consoles) {
		this.consoles = consoles;
	}

}
