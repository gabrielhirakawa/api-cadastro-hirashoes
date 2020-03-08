package com.hirashoesusers;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.impl.DAO.ClienteDAO;

@SpringBootApplication
public class HirashoesUsersApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(HirashoesUsersApplication.class, args);
//		ClienteDAO cliDAO = new ClienteDAO();
//		Cliente cli = new Cliente();
//		cli.setEmail("gabriel@gmail");
//		cli.setNome("gabrieeeeel");
//		cliDAO.salvar(cli);
//		
		
		
	}

}
