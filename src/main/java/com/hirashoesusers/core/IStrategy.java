package com.hirashoesusers.core;

import com.hirashoesusers.dominio.EntidadeImpl;

public interface IStrategy {
	public String processar(EntidadeImpl entidade);
}
