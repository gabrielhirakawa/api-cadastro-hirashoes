package com.hirashoesusers.impl.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.hirashoesusers.core.IDAO;
import com.hirashoesusers.util.Conexao;

public abstract class AbstractJdbcDAO implements IDAO {
	
	protected Connection connection;
	protected boolean ctrlTransaction = true;
	
	public AbstractJdbcDAO(Connection connection) {
		this.connection = connection;
	}
	
	public AbstractJdbcDAO() {
	}
	
	protected void openConnection(){
		try {
			
			if(connection == null || connection.isClosed())
				connection = Conexao.getConnection();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
