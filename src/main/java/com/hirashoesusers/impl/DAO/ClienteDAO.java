package com.hirashoesusers.impl.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.EntidadeImpl;

public class ClienteDAO extends AbstractJdbcDAO {

	@Override
	public void salvar(EntidadeImpl entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente)entidade;
		
		try {			
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO clientes  (nome, sobrenome, cpf, email, status, password)");
			sql.append("VALUES(?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getSobrenome());
			pst.setString(3, cliente.getCpf());
			pst.setString(4, cliente.getEmail());
			pst.setString(5, cliente.getStatus());
			pst.setString(6, cliente.getPassword());
			
		
			pst.execute();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void alterar(EntidadeImpl entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeImpl entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ativar(EntidadeImpl entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeImpl> consultar(EntidadeImpl entidade) {
		PreparedStatement pst = null;

		//Cliente cliente = (Cliente) entidade;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM clientes");
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			List<EntidadeImpl> clientes = new ArrayList<EntidadeImpl>();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
	            c.setNome(rs.getString("nome"));
	            c.setEmail(rs.getString("email"));
//	            c.setStatus(rs.getString("c.status"));

				clientes.add(c);
				System.out.println(c.getEmail());
			}
			
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
