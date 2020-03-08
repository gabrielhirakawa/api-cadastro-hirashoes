package com.hirashoesusers.impl.negocio;

import com.hirashoesusers.core.IStrategy;
import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.EntidadeImpl;

public class ValidarDados implements IStrategy {

	@Override
	public String processar(EntidadeImpl entidade) {
		if (entidade instanceof Cliente) {
			Cliente cliente = (Cliente) entidade;
			
			String nome = cliente.getNome();
			
			if (nome == null || nome.trim().equals("")) {
				return "Campo nome obrigat�rio!";
			}
		} else {
			return "Deve ser registrado um Cliente!";
		}
		return null;
	}
}
