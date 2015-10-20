package com.melhorCaminho.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.melhorCaminho.config.HibernateUtil;
import com.melhorCaminho.model.Ponto;

public class PontoDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();

	@SuppressWarnings("unchecked")
	public List<Ponto> listarPontos() {
		Query query = this.session.createQuery("FROM ponto");
		List<Ponto> lista  = query.list();
		return lista;
	}
	
	
	public Ponto listarById(Integer id){
		Query query = this.session.createQuery("FROM ponto where id = :id").setParameter("id", id);
		return (Ponto) query.uniqueResult();
	}
	
	public Ponto listarByNome(String nome) {
		Query query = this.session.createQuery("FROM ponto where nome = :nome").setParameter("nome", nome);
		return (Ponto) query.uniqueResult();
	}
	
	public void inserir(Ponto ponto){
		this.session.beginTransaction();
		this.session.save(ponto);
		this.session.getTransaction().commit();
	}
	
	public void deletar(Integer id){
		Ponto ponto = this.listarById(id);
		this.session.beginTransaction();
		this.session.delete(ponto);
		this.session.getTransaction().commit();
	}
}
