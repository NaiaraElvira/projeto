package br.com.naiara.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.naiara.dao.TelefoneDAO;
import br.com.naiara.entity.Telefone;

@Stateless
public class TelefoneBusiness {
	@Inject
	private TelefoneDAO telefoneDAO;
	
	public List<Telefone> listar() {		
		return telefoneDAO.listarTelefone();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarTelefone(Telefone telefone) {
		Date dataHoraAtual = new Date();
		telefone.setDataCadastro(dataHoraAtual);
		telefoneDAO.salvarTelefone(telefone);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirTelefone(Long id) {
		telefoneDAO.excluirTelefone(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarTelefone(Telefone telefone) {
		telefoneDAO.alterarTelefone(telefone);
	}
	
	public Telefone consultarTelefone(Long id) {
		return telefoneDAO.getById(id);
	}

}
