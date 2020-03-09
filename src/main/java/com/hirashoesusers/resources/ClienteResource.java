package com.hirashoesusers.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hirashoesusers.core.aplicacao.Resultado;
import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.web.command.AlterarCommand;
import com.hirashoesusers.web.command.ConsultarCommand;
import com.hirashoesusers.web.command.DeletarCommand;
import com.hirashoesusers.web.command.ICommand;
import com.hirashoesusers.web.command.SalvarCommand;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	ICommand command;
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Resultado> findAll(){	
		command = new ConsultarCommand();
		Resultado resultado = command.execute(new Cliente());
		return ResponseEntity.ok().body(resultado);
			
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Resultado> insert(@RequestBody Cliente body){
		command = new SalvarCommand();
		Resultado resultado = command.execute(body);
		return ResponseEntity.ok().body(resultado);
			
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cliente cliente, @PathVariable Integer id){
		cliente.setId(id);
		command = new AlterarCommand();
		Resultado resultado = command.execute(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Resultado> delete(@PathVariable Integer id){
		Cliente cliente = new Cliente();
		cliente.setId(id);
		command = new DeletarCommand();
		Resultado resultado = command.execute(cliente);
		return ResponseEntity.ok().body(resultado);
			
	}
	
	

}
