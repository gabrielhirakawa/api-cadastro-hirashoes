package com.hirashoesusers.web.command;

import com.hirashoesusers.core.aplicacao.Resultado;
import com.hirashoesusers.dominio.EntidadeImpl;

public interface ICommand {
	
	public Resultado execute(EntidadeImpl entidade);
}
