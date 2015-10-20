package com.melhorCaminho.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.melhorCaminho.config.HibernateUtil;
import com.melhorCaminho.model.Mapa;
import com.melhorCaminho.model.Ponto;
import com.melhorCaminho.model.Rota;

public class RotaDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();

	@SuppressWarnings("unchecked")
	public List<Rota> listarRotas() {
		Query query = this.session.createQuery("FROM rota");
		List<Rota> lista = query.list();
		return lista;
	}

	public Rota listarById(Integer id) {
		Query query = this.session.createQuery("FROM rota where id = :id").setParameter("id", id);
		return (Rota) query.uniqueResult();
	}

	public List<Rota> listarByMapa(Mapa mapa) {
		Query query = this.session.createQuery("FROM rota where mapa = :mapa").setParameter("mapa", mapa);
		List<Rota> lista = query.list();
		return lista;
	}

	public Rota listarByMapaOrigemDestino(Mapa mapa, Ponto origem, Ponto destino) {
		Query query = this.session
				.createQuery("FROM rota where mapa = :mapa and origem = :origem and destino = :destino")
				.setParameter("mapa", mapa).setParameter("origem", origem).setParameter("destino", destino);
		return (Rota) query.uniqueResult();
	}

	public void inserir(Rota rota) {
		this.session.beginTransaction();
		this.session.save(rota);
		this.session.getTransaction().commit();
	}

	public void deletar(Integer id) {
		Rota rota = this.listarById(id);
		this.session.beginTransaction();
		this.session.delete(rota);
		this.session.getTransaction().commit();
	}
}
