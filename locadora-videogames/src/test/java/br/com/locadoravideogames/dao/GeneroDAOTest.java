package br.com.locadoravideogames.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.locadoravideogames.entity.Genero;

public class GeneroDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Genero genero = new Genero();
		genero.setNome("Estratégia");

		GeneroDAO generoDAO = new GeneroDAO();
		generoDAO.salvar(genero);

	}

	@Test
	public void listar() {

		GeneroDAO generoDAO = new GeneroDAO();

		List<Genero> generos = generoDAO.listar();
		System.out.println("Gênero: ");
		for (Genero genero : generos) {
			System.out.println(genero.getNome());
		}
	}
}
