package br.com.naiara.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import br.com.naiara.entity.Pessoa;

@Stateless
public class PessoaDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Pessoa> listarPessoa() {
		return entityManager.createQuery("SELECT p from Pessoa p", Pessoa.class).getResultList();
	}
	
	public void salvarPessoa(Pessoa pessoa) {
		entityManager.persist(pessoa);
	}
	
	public Pessoa getById(final Long id) {
        return entityManager.find(Pessoa.class, id);
    }
	
	public void excluirPessoa(Long id) {
		Pessoa pessoa = entityManager.find(Pessoa.class, id);
        entityManager.remove(pessoa);
	}
	
	public void alterarPessoa(@Valid Pessoa pessoa) {
	    entityManager.merge(pessoa);
	}
}
