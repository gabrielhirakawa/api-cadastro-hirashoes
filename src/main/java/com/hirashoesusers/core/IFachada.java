package com.hirashoesusers.core;

import com.hirashoesusers.core.aplicacao.Resultado;
import com.hirashoesusers.dominio.EntidadeImpl;

public interface IFachada {
	
	public Resultado salvar(EntidadeImpl entidade);
	public Resultado alterar(EntidadeImpl entidade);
	public Resultado excluir(EntidadeImpl entidade);
	public Resultado ativar(EntidadeImpl entidade);
	public Resultado consultar(EntidadeImpl entidade);
	public Resultado visualizar(EntidadeImpl entidade);
	public Resultado consultarPorId(EntidadeImpl entidade);

}
