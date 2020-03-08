package com.hirashoesusers.core.aplicacao;

import java.util.List;

import com.hirashoesusers.dominio.EntidadeImpl;

public class Resultado extends EntidadeAplicacao {
	
	/**
	 * Mensagem de erro ou n�o, na fachada ou nas regras de neg�cio
	 * Quando a msg n�o for null na valida��o da fachada, n�o deve ser executado o DAO correspondente
	 */
	private String mensagem;
	/**
	 * Lista de entidades consultadas no banco
	 */
	private List<EntidadeImpl> entidades;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<EntidadeImpl> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeImpl> entidades) {
		this.entidades = entidades;
	}

}
