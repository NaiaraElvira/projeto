package br.com.naiara.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import br.com.naiara.entity.Telefone;

@Stateless
public class TelefoneDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Telefone> listarTelefone() {
		return entityManager.createQuery("SELECT t from Telefone t", Telefone.class).getResultList();
	}
	
	public void salvarTelefone(Telefone telefone) {
		entityManager.persist(telefone);
	}
	
	public Telefone getById(final Long id) {
        return entityManager.find(Telefone.class, id);
    }
	
	public void excluirTelefone(Long id) {
		Telefone telefone = entityManager.find(Telefone.class, id);
        entityManager.remove(telefone);
	}
	
	public void alterarTelefone(@Valid Telefone telefone) {
	    entityManager.merge(telefone);
	}

}
