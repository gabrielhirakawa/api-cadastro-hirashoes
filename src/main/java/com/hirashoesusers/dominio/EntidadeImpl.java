package com.hirashoesusers.dominio;

public abstract class EntidadeImpl implements IEntidade {

	protected Integer id;
	
	public EntidadeImpl() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
