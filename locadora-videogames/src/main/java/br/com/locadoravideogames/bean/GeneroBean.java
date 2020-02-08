package br.com.locadoravideogames.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.locadoravideogames.dao.GeneroDAO;

import br.com.locadoravideogames.entity.Genero;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class GeneroBean implements Serializable {

	private Genero genero;
	private List<Genero> generos;

	public void novo() {
		genero = new Genero();
	}

	public void salvar() {
		try {
			GeneroDAO generoDAO = new GeneroDAO();

			generoDAO.merge(genero);

			genero = new Genero();
			generos = generoDAO.listar();

			Messages.addFlashGlobalInfo("Genero salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o Genero.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {

			genero = (Genero) evento.getComponent().getAttributes().get("generoSelecionado");
			GeneroDAO generoDAO = new GeneroDAO();
			generoDAO.excluir(genero);

			generos = generoDAO.listar();

			Messages.addGlobalInfo("Genero excluído com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o Genero.");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			GeneroDAO generoDAO = new GeneroDAO();

			generos = generoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Generos");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		genero = (Genero) evento.getComponent().getAttributes().get("generoSelecionado");

	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

}
