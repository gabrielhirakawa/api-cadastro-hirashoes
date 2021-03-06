package com.hirashoesusers.impl.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.Endereco;
import com.hirashoesusers.dominio.EntidadeImpl;
import com.hirashoesusers.dominio.Telefone;

public class EnderecoDAO extends AbstractJdbcDAO {

	@Override
	public void salvar(EntidadeImpl entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO enderecos (user_id, cep, rua, numero, bairro, complemento, cidade, estado, pais, created_at, updated_at)");
			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			for(int i=0; i<cliente.getEnderecos().size(); i++) {
				pst.setInt(1, cliente.getId());
				pst.setString(2, cliente.getEnderecos().get(i).getCep());
				pst.setString(3, cliente.getEnderecos().get(i).getRua());
				pst.setString(4, cliente.getEnderecos().get(i).getNumero());
				pst.setString(5, cliente.getEnderecos().get(i).getBairro());
				pst.setString(6, cliente.getEnderecos().get(i).getComplemento());
				pst.setString(7, cliente.getEnderecos().get(i).getCidade());
				pst.setString(8, cliente.getEnderecos().get(i).getEstado());
				pst.setString(9, cliente.getEnderecos().get(i).getPais());
				pst.setDate(10, new Date(0));
				pst.setDate(11, new Date(0));


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

			sql.append("UPDATE enderecos SET cep=?, rua=?, numero=?, bairro=?, complemento=?, cidade=?, estado=?, pais=?");
			sql.append("WHERE user_id=?");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			
			pst.setString(1, cliente.getEnderecos().get(0).getCep());
			pst.setString(2, cliente.getEnderecos().get(0).getRua());
			pst.setString(3, cliente.getEnderecos().get(0).getNumero());
			pst.setString(4, cliente.getEnderecos().get(0).getBairro());
			pst.setString(5, cliente.getEnderecos().get(0).getComplemento());
			pst.setString(6, cliente.getEnderecos().get(0).getCidade());
			pst.setString(7, cliente.getEnderecos().get(0).getEstado());
			pst.setString(8, cliente.getEnderecos().get(0).getPais());
			pst.setInt(9, cliente.getId());
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
		PreparedStatement pst = null;

		Cliente cliente = (Cliente) entidade;
		StringBuilder sql = new StringBuilder();

		StringBuilder endSql = new StringBuilder();

		sql.append("SELECT * FROM clientes WHERE status='ativo'");

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());

			ResultSet rs = pst.executeQuery();

			endSql.append("SELECT * FROM enderecos WHERE user_id = ? ");
			openConnection();
			pst = connection.prepareStatement(endSql.toString());
			pst.setInt(1, cliente.getId());
			rs = pst.executeQuery();
			List<EntidadeImpl> enderecos = new ArrayList<EntidadeImpl>();

			while (rs.next()) {
				Endereco end = new Endereco();
				end.setId(rs.getInt("id"));
				end.setRua(rs.getString("rua"));
				end.setNumero(rs.getString("numero"));
				end.setBairro(rs.getString("bairro"));
				end.setCep(rs.getString("cep"));
				end.setComplemento(rs.getString("complemento"));
				end.setCidade(rs.getString("cidade"));
				end.setEstado(rs.getString("estado"));
				end.setPais(rs.getString("pais"));
				enderecos.add(end);
			}
			return enderecos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EntidadeImpl consultarPorId(EntidadeImpl entidade) throws SQLException {
		PreparedStatement pst = null;

		Cliente cliente = (Cliente) entidade;
		StringBuilder sql = new StringBuilder();

		try {
			Endereco e = new Endereco();
			sql.append("SELECT * FROM enderecos WHERE user_id=?");
			openConnection();

			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, cliente.getId());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				e.setId(rs.getInt("id"));
				e.setRua(rs.getString("rua"));
				e.setNumero(rs.getString("numero"));
				e.setCep(rs.getString("cep"));
				e.setBairro(rs.getString("bairro"));
				e.setComplemento(rs.getString("complemento"));
				e.setCidade(rs.getString("cidade"));
				e.setEstado(rs.getString("estado"));
				e.setPais(rs.getString("pais"));
			}
			
			return e;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
