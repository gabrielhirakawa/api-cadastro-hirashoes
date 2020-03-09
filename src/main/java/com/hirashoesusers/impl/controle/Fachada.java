package com.hirashoesusers.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hirashoesusers.core.IDAO;
import com.hirashoesusers.core.IFachada;
import com.hirashoesusers.core.IStrategy;
import com.hirashoesusers.core.aplicacao.Resultado;
import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.EntidadeImpl;
import com.hirashoesusers.impl.DAO.ClienteDAO;
import com.hirashoesusers.impl.negocio.ValidarCPF;
import com.hirashoesusers.impl.negocio.ValidarDados;
import com.hirashoesusers.impl.negocio.ValidarSenha;

public class Fachada implements IFachada {
	
	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado resultado;
	
	public Fachada() {
		
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		daos.put(Cliente.class.getName(), clienteDAO);
		
		//instanciando regras
		ValidarDados vrDados = new ValidarDados();
		ValidarCPF vrCPF = new ValidarCPF();
		ValidarSenha vrSenha = new ValidarSenha();
		
		// listas para conter regras de negócios do cliente 
		List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarCliente = new ArrayList<IStrategy>();
		
		rnsSalvarCliente.add(vrDados);
		rnsSalvarCliente.add(vrCPF);
		rnsSalvarCliente.add(vrSenha);
		
		rnsAlterarCliente.add(vrDados);
		rnsAlterarCliente.add(vrCPF);
		rnsAlterarCliente.add(vrSenha);
		
		Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
		rnsCliente.put("SALVAR", rnsSalvarCliente);
		rnsCliente.put("ALTERAR", rnsAlterarCliente);
		
		//map geral de entidades
		rns.put(Cliente.class.getName(), rnsCliente);
		
		
		
		
	}
	

	@Override
	public Resultado salvar(EntidadeImpl entidade) {
		resultado = new Resultado();
		String nomeClasse = entidade.getClass().getName();
		
		String mensagem = executarRegras(entidade, "SALVAR");
		
		if(mensagem == null) {
			IDAO dao = daos.get(nomeClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeImpl> entidades = new ArrayList<EntidadeImpl>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				resultado.setMensagem("Nao foi possivel realizar o registro!");
			}
		} else {
			resultado.setMensagem(mensagem);
		}
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeImpl entidade) {
		resultado = new Resultado();
		String nomeClasse = entidade.getClass().getName();
		
		String mensagem = executarRegras(entidade, "ALTERAR");
		
		if(mensagem == null) {
			IDAO dao = daos.get(nomeClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeImpl> entidades = new ArrayList<EntidadeImpl>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				resultado.setMensagem("Não foi possível atualizar o registro!");
			}
		} else {
			resultado.setMensagem(mensagem);
		}
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeImpl entidade) {
		resultado = new Resultado();
		String nomeClasse = entidade.getClass().getName();
		
		String mensagem = executarRegras(entidade, "EXCLUIR");
		
		if(mensagem == null) {
			IDAO dao = daos.get(nomeClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeImpl> entidades = new ArrayList<EntidadeImpl>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel realizar o registro!");
			}
		} else {
			resultado.setMensagem(mensagem);
		}
		return resultado;
	}

	@Override
	public Resultado ativar(EntidadeImpl entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado consultar(EntidadeImpl entidade) {
		resultado = new Resultado();
		String nomeClasse = entidade.getClass().getName();
		
		String mensagem = executarRegras(entidade, "CONSULTAR");
		
		if(mensagem == null) {
			IDAO dao = daos.get(nomeClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel realizar o registro!");
			}
		} else {
			resultado.setMensagem(mensagem);
		}
		return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeImpl entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeImpl>(1));
		resultado.getEntidades().add(entidade);
		return resultado;
	}
	
	private String executarRegras(EntidadeImpl entidade, String operacao) {
		String nomeClasse = entidade.getClass().getName();
		StringBuilder mensagem = new StringBuilder();
		
		/* Pega todas as regras da entidade */
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nomeClasse);
		
		if(regrasOperacao != null) {	// Existem regras de negocio para essa classe?
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null) {	// Existem regras de negocio para essa operacao dessa classe?
				for(IStrategy regra : regras) {
					String msg = regra.processar(entidade);	// Processa regra por regra
					
					if(msg != null) {
						mensagem.append(msg);
						mensagem.append("\n");
					}
				}
			}
		}
		if(mensagem.length() > 0)
			return mensagem.toString();
		else
			return null;
	}

}
