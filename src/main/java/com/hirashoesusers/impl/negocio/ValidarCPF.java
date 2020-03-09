package com.hirashoesusers.impl.negocio;

import com.hirashoesusers.core.IStrategy;
import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.EntidadeImpl;

public class ValidarCPF implements IStrategy {

	@Override
	public String processar(EntidadeImpl entidade) {
		if (entidade instanceof Cliente) {
			Cliente cliente = (Cliente) entidade;
			
			String cpf = cliente.getCpf();
			
			if (cpf.length() != 11) {
				return "CPF inválido!";
			}
		} else {
			return "Deve ser registrado um Cliente!";
		}
		return null;
	}
}
