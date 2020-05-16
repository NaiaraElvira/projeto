package br.com.naiara.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import br.com.naiara.entity.Operador;

@Stateless
public class OperadorDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Operador> listarOperadores() {
		return entityManager.createQuery("SELECT a from Operador a", Operador.class).getResultList();
	}
	
	public Operador getById(final Long id) {
        return entityManager.find(Operador.class, id);
    }
	
	public void salvarOperador(@Valid Operador operador) {
    		entityManager.persist(operador);
	}
	
	public void excluirOperador(Long id) {
	            Operador operador = entityManager.find(Operador.class, id);
	            entityManager.remove(operador);
	}
	
	public void alterarOperador(@Valid Operador operador) {
            entityManager.merge(operador);
     }

}
