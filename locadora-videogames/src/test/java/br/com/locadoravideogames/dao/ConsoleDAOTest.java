package br.com.locadoravideogames.dao;

import java.util.List;

import org.junit.Test;

import br.com.locadoravideogames.entity.Console;

public class ConsoleDAOTest {

	@Test
	// @Ignore
	public void salvar() {
		Console console = new Console();

		console.setNome("Nintendo 3DS");
		console.setFabricante("Nintendo");

		ConsoleDAO consoleDAO = new ConsoleDAO();

		consoleDAO.salvar(console);

	}

	@Test
	public void listar() {

		ConsoleDAO consoleDAO = new ConsoleDAO();

		List<Console> consoles = consoleDAO.listar();

		for (Console console : consoles) {
			System.out.println("Nome do console: " + console.getNome());
			System.out.println("Fabricante: " + console.getFabricante());
		}
	}
}
