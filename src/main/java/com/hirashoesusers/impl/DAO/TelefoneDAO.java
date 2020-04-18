package com.hirashoesusers.impl.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.EntidadeImpl;
import com.hirashoesusers.dominio.Telefone;

public class TelefoneDAO extends AbstractJdbcDAO {

	@Override
	public void salvar(EntidadeImpl entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO telefones (user_id, numero, created_at, updated_at)");
			sql.append("VALUES(?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			for(int i=0; i<cliente.getTelefones().size(); i++) {
				pst.setInt(1, cliente.getId());
				pst.setString(2, cliente.getTelefones().get(i).getNumero());
				pst.setDate(3, new Date(0));
				pst.setDate(4, new Date(0));


				pst.execute();
			}
			
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
	public void alterar(EntidadeImpl entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE telefones SET numero=?");
			sql.append("WHERE user_id=?");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, cliente.getTelefones().get(0).getNumero());
			pst.setInt(2, cliente.getId());

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
	public void excluir(EntidadeImpl entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ativar(EntidadeImpl entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeImpl> consultar(EntidadeImpl entidade) throws SQLException {
//		PreparedStatement pst = null;
//		StringBuilder telSql = new StringBuilder();
//		Cliente cliente = (Cliente)entidade;
//		
//		try {
//			telSql.append("SELECT * FROM telefones WHERE user_id = ? ");
//            openConnection();
//            ResultSet rs = pst.executeQuery();
//            pst = connection.prepareStatement(telSql.toString());
//            
//            pst.setInt(1, cliente.getId());
//            rs = pst.executeQuery();
//            
//            List<EntidadeImpl> phones = new ArrayList<EntidadeImpl>();
//            while (rs.next()) {
//            	Telefone t = new Telefone();
//            	System.out.println("print numero:" + rs.getString("numero"));
//            	t.setNumero(rs.getString("numero"));
//            	phones.add(t);
//                        	
//            }
//
//            return phones;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public EntidadeImpl consultarPorId(EntidadeImpl entidade) throws SQLException {
		PreparedStatement pst = null;

		Cliente cliente = (Cliente) entidade;
		StringBuilder sql = new StringBuilder();

		try {
			Telefone t = new Telefone();
			sql.append("SELECT * FROM telefones WHERE user_id=?");
			openConnection();

			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, cliente.getId());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				t.setId(rs.getInt("id"));
				t.setNumero(rs.getString("numero"));
			}
			
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
