package com.hirashoesusers.core;

import java.sql.SQLException;
import java.util.List;

import com.hirashoesusers.dominio.EntidadeImpl;

public interface IDAO {
	
	public void salvar(EntidadeImpl entidade) throws SQLException;
	public void alterar(EntidadeImpl entidade) throws SQLException;
	public void excluir(EntidadeImpl entidade) throws SQLException;
	public void ativar(EntidadeImpl entidade) throws SQLException;
	public List<EntidadeImpl> consultar(EntidadeImpl entidade) throws SQLException;

}
