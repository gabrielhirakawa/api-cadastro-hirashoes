package com.hirashoesusers.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hirashoesusers.core.aplicacao.Resultado;
import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.impl.controle.Fachada;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	Fachada fachada = new Fachada();
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Resultado> findAll(){		
		Cliente cli = new Cliente();
		Resultado clientes = fachada.consultar(cli);
		return ResponseEntity.ok().body(clientes);
			
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Resultado> insert(@RequestBody Cliente body){
//		Cliente cli = new Cliente();
		Resultado clientes = fachada.salvar(body);
		return ResponseEntity.ok().body(clientes);
			
	}

}
