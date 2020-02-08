package br.com.locadoravideogames.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	// Este objeto guarda a fábrica de sessões
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			// Este objeto ler o arquivo hibernate.cfg.xml que tem as informações de conexão
			Configuration configuracao = new Configuration().configure();

			@SuppressWarnings("unused")
			// Registra o serviço: passa para a variavel 'registro' as propriedades da
			// variavel 'configuracao'
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();
			// Cria a fábrica de sessões
			SessionFactory fabrica = configuracao.buildSessionFactory();
			return fabrica;
		} catch (Exception ex) {
			System.err.println("Não foi possível criar a fábrica de sessões ! erro: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

}
