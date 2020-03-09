package com.hirashoesusers.web.command;

import com.hirashoesusers.core.aplicacao.Resultado;
import com.hirashoesusers.dominio.EntidadeImpl;

public class AlterarCommand  extends AbstractCommand {

public Resultado execute(EntidadeImpl entidade) {
		
		return fachada.alterar(entidade);
	}

}
