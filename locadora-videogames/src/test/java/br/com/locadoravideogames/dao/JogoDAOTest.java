package br.com.locadoravideogames.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.locadoravideogames.entity.Genero;
import br.com.locadoravideogames.entity.Console;
import br.com.locadoravideogames.entity.Jogo;

//para testar um método, descomentar o @Ignore
public class JogoDAOTest {

	@Test
	@Ignore
	public void salvar() {

		ConsoleDAO consoleDAO = new ConsoleDAO();
		Console console = consoleDAO.buscar(1L);

		GeneroDAO generoDAO = new GeneroDAO();
		Genero genero = generoDAO.buscar(9L);

		Jogo jogo = new Jogo();
		jogo.setNome("Super Mário World");
		jogo.setDescricao("Super Mário");
		jogo.setConsole(console);
		jogo.setGenero(genero);

		JogoDAO jogoDAO = new JogoDAO();
		jogoDAO.salvar(jogo);

	}

	@Test
	@Ignore
	public void listar() {
		JogoDAO jogoDAO = new JogoDAO();
		List<Jogo> jogos = jogoDAO.listar();

		for (Jogo jogo : jogos) {
			System.out.println("Nome: " + jogo.getNome());
			System.out.println("Console: " + jogo.getConsole().getNome());
		}

	}

	@Test
	@Ignore
	public void buscar() {
		JogoDAO jogoDAO = new JogoDAO();
		Jogo jogo = jogoDAO.buscar(18L);

		System.out.println("Jogo: " + jogo.getNome());

	}

	@Test
	@Ignore
	public void excluir() {
		JogoDAO jogoDAO = new JogoDAO();
		Jogo jogo = jogoDAO.buscar(18L);
		jogoDAO.excluir(jogo);
	}

	@Test
	public void editar() {
		JogoDAO jogoDAO = new JogoDAO();
		Jogo jogo = jogoDAO.buscar(19L);

		// Código para pesquisar e alterar a chave estrangeira
		ConsoleDAO consoleDAO = new ConsoleDAO();
		Console console = consoleDAO.buscar(3L);

		System.out.println("Jogo a ser editado: " + jogo.getNome());

		jogo.setDescricao("Lara Croft Tomb Raider");
		jogo.setConsole(console);
		jogoDAO.editar(jogo);

		System.out.println("Jogo editado: " + jogo.getNome());
	}
}
