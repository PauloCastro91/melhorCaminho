package com.melhorCaminho.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.melhorCaminho.config.HibernateUtil;
import com.melhorCaminho.model.Mapa;

public class MapaDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	@SuppressWarnings("unchecked")
	public List<Mapa> listarTodos() {
		Query query = this.session.createQuery("FROM mapa");
		List<Mapa> lista = query.list();
		return lista;
	}

	public Mapa listarById(Integer id) {
		Query query = this.session.createQuery("FROM mapa where id = :id").setParameter("id", id);
		return (Mapa) query.uniqueResult();
	}

	public Mapa listarByNome(String nome) {
		Query query = this.session.createQuery("FROM mapa where nome = :nome").setParameter("nome", nome);
		return (Mapa) query.uniqueResult();
	}

	public void inserir(Mapa mapa) {
		this.session.beginTransaction();
		this.session.save(mapa);
		this.session.getTransaction().commit();
	}

	public void deletar(Integer id) {
		Mapa mapa = this.listarById(id);
		this.session.beginTransaction();
		this.session.delete(mapa);
		this.session.getTransaction().commit();
	}
	
	
	
}
