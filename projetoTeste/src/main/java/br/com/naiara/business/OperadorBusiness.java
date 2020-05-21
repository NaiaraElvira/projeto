package br.com.naiara.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.naiara.dao.OperadorDAO;
import br.com.naiara.entity.Operador;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OperadorBusiness {
	@Inject
	private OperadorDAO operadorDAO;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarOperador(Operador operador) {
		Date dataHoraAtual = new Date();
		operador.setData_cadastro(dataHoraAtual);
		operadorDAO.salvarOperador(operador);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirOperador(Long id) {
		operadorDAO.excluirOperador(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarOperador(Operador operador) {
		operadorDAO.alterarOperador(operador);
	}
	
	public Operador consultarOperador(Long id) {
		return operadorDAO.getById(id);
	}
	
	public List<Operador> listar() {	
		return operadorDAO.listarOperadores();
	}
}
