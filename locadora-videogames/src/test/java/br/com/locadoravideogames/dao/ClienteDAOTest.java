package br.com.locadoravideogames.dao;

import org.junit.Test;

import br.com.locadoravideogames.entity.Cliente;

public class ClienteDAOTest {

	@Test
	public void salvar() {
		Cliente cliente = new Cliente();

		cliente.setNome("Géssica Mayara");
		cliente.setEndereco("");
		cliente.setNumero("");
		cliente.setBairro("");
		cliente.setCidade("Fortaleza");
		cliente.setEstado("CE");
		cliente.setCep("99999999");
		cliente.setComplemento("");
		cliente.setCelular("85999999999");
		cliente.setCpf("99999999999");
		cliente.setEmail("gessica@mail.com");

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

	}
}
