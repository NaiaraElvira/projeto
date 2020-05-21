package br.com.naiara.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.naiara.dao.PessoaDAO;
import br.com.naiara.entity.Pessoa;
import br.com.naiara.entity.Telefone;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PessoaBusiness {
	@Inject
	private PessoaDAO pessoaDAO;
	
	public List<Pessoa> listar() {		
		return pessoaDAO.listarPessoa();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarPessoa(Pessoa pessoa, String login) {
		Date dataHoraAtual = new Date();
		pessoa.setDataCadastro(dataHoraAtual);
		pessoa.setLogin(login);
		for (Telefone telefone : pessoa.getTelefones()) {
			telefone.setDataCadastro(dataHoraAtual);
			telefone.setPessoa(pessoa);
			telefone.setLogin(login);
		}
		pessoaDAO.salvarPessoa(pessoa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirPessoa(Long id) {
		pessoaDAO.excluirPessoa(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarPessoa(Pessoa pessoa) {
		pessoaDAO.alterarPessoa(pessoa);
	}
	
	public Pessoa consultarPessoa(Long id) {
		return pessoaDAO.getById(id);
	}
}
