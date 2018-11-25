package br.edu.ifsuldeminas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifsuldeminas.modelo.Pedido;
import br.edu.ifsuldeminas.modelo.Vacina;

public class VacinaDAO {
	public Vacina listaPorId(Vacina p){
			 EntityManager em = JPAUtil.getEntityManager();
			 String jpql = "SELECT DISTINCT p  FROM Vacina p LEFT JOIN FETCH p.ling WHERE p.id = :pId";
			 TypedQuery<Vacina> query = em.createQuery(jpql,Vacina.class);
			 query.setParameter("pId", p.getId());
			 p = query.getSingleResult();
			 em.close();
			 return p;
			}

	



public List<Vacina> listaPizza(){
	String jpql = "SELECT DISTINCT f FROM Vacina f "
			+ "LEFT JOIN FETCH f.tipo "
			+ "LEFT JOIN FETCH f.ling "
			

			+ "WHERE f.tipo.id = :pTipo";
	
	EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Vacina> query = em.createQuery(jpql, Vacina.class);
	query.setParameter("pTipo",1);
	
	System.out.println(query.toString());
	
	
	List<Vacina> lista = query.getResultList();
	
	em.close();
	
	return lista;
	
}


public List<Vacina> listaRefri(){
	String jpql = "SELECT DISTINCT f FROM Vacina f "
			+ "LEFT JOIN FETCH f.tipo "
			+ "LEFT JOIN FETCH f.ling "
			

			+ "WHERE f.tipo.id = :pTipo";
	
	EntityManager em = JPAUtil.getEntityManager();
	TypedQuery<Vacina> query = em.createQuery(jpql, Vacina.class);
	query.setParameter("pTipo",2);
	
	System.out.println(query.toString());
	
	
	List<Vacina> lista = query.getResultList();
	
	em.close();
	
	return lista;
	
}}