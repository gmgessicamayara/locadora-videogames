package br.com.locadoravideogames.dao;

import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;
import org.hibernate.criterion.Order;
import br.com.locadoravideogames.entity.Jogo;
import br.com.locadoravideogames.entity.Locacao;
import br.com.locadoravideogames.entity.TituloLocacao;
import br.com.locadoravideogames.util.HibernateUtil;

public class LocacaoDAO extends EntidadeGenericaDAO<Locacao> {

	public List<Locacao> buscarPorConsole(Long console) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria consulta = sessao.createCriteria(Jogo.class);
			consulta.add(Restrictions.eq("console.codigo", console));

			consulta.addOrder(Order.asc("nome"));
			@SuppressWarnings("unchecked")
			List<Locacao> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void salvar(Locacao locacao, List<TituloLocacao> titulos) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.save(locacao);

			for (int i = 0; i < titulos.size(); i++) {
				TituloLocacao titulo = titulos.get(i);
				titulo.setLocacao(locacao);
				titulo.getJogo().setDisponivel(false);
				sessao.save(titulo);

			}

			// transacao.commit();
			for (int posicao = 0; posicao < titulos.size(); posicao++) {
				TituloLocacao titulo = titulos.get(posicao);
				titulo.setLocacao(locacao);
				sessao.save(locacao);

			}

			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();

				Messages.addGlobalError("Ocorreu um erro ao tentar realizar a locação");
			}

		} finally {
			sessao.close();
		}
	}

}
