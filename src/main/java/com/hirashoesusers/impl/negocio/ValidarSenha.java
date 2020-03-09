package com.hirashoesusers.impl.negocio;

import com.hirashoesusers.core.IStrategy;
import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.EntidadeImpl;

public class ValidarSenha implements IStrategy {
	
	@Override
	public String processar(EntidadeImpl entidade) {
		if (entidade instanceof Cliente) {
			Cliente cliente = (Cliente) entidade;
			
			String pass = cliente.getPassword();
			
			if (pass.length() < 6) {
				return "A senha deve conter pelo menos 6 caracteres!";
			}
		} else {
			return "Deve ser registrado um Cliente!";
		}
		return null;
	}
}
