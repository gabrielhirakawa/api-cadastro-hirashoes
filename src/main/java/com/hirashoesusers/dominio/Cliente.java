package com.hirashoesusers.dominio;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends EntidadeImpl {
	
	private String nome;
	private String sobrenome;
	private String cpf;
	private String email;
	private String status;
	private String password;
	
	
	
	public Cliente(String nome, String sobrenome, String cpf, String email, String status, String password,
			List<String> telefones, List<Endereco> enderecos) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.email = email;
		this.status = status;
		this.password = password;
		this.telefones = telefones;
		this.enderecos = enderecos;
	}

	private List<String> telefones = new ArrayList<>();
	private List<Endereco> enderecos = new ArrayList<>();
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cliente() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	
}
