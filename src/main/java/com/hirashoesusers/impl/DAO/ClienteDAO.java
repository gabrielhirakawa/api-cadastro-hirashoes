package com.hirashoesusers.impl.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.hirashoesusers.dominio.Cliente;
import com.hirashoesusers.dominio.EntidadeImpl;

public class ClienteDAO extends AbstractJdbcDAO {

	@Override
	public void salvar(EntidadeImpl entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO clientes  (nome, sobrenome, cpf, email, status, password)");
			sql.append("VALUES(?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getSobrenome());
			pst.setString(3, cliente.getCpf());
			pst.setString(4, cliente.getEmail());
			pst.setString(5, cliente.getStatus());
			pst.setString(6, cliente.getPassword());

			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				cliente.setId(rs.getInt(1));
			}

			TelefoneDAO tel = new TelefoneDAO();
			tel.salvar(cliente);
			EnderecoDAO end = new EnderecoDAO();
			end.salvar(cliente);

		} catch (PSQLException e) {
			try {
				connection.setAutoCommit(false);
				connection.rollback();
			} catch (PSQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (PSQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void alterar(EntidadeImpl entidade) {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE clientes SET nome=?, sobrenome=?, cpf=?,");
			sql.append("email=?, password=?");
			sql.append("WHERE id=?");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getSobrenome());
			pst.setString(3, cliente.getCpf());
			pst.setString(4, cliente.getEmail());
			pst.setString(5, cliente.getPassword());
			pst.setInt(6, cliente.getId());

			pst.execute();

			TelefoneDAO tel = new TelefoneDAO();
			tel.alterar(cliente);
			EnderecoDAO end = new EnderecoDAO();
			end.alterar(cliente);

		} catch (SQLException e) {
			try {
				connection.setAutoCommit(false);
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
	public void excluir(EntidadeImpl entidade) {
		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE clientes SET status='inativo'");
			sql.append("WHERE id=?");

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, cliente.getId());

			pst.execute();

		} catch (SQLException e) {
			try {
				connection.setAutoCommit(false);
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
	public void ativar(EntidadeImpl entidade) {
		// TODO Auto-generated method stub

	}

	public EntidadeImpl consultarPorId(EntidadeImpl entidade) {
		PreparedStatement pst = null;

		Cliente cliente = (Cliente) entidade;
		StringBuilder sql = new StringBuilder();

		try {
			Cliente c = new Cliente();
			sql.append("SELECT * FROM clientes WHERE id=?");
			openConnection();
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, cliente.getId());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setSobrenome(rs.getString("sobrenome"));
				c.setEmail(rs.getString("email"));
				c.setCpf(rs.getString("cpf"));
				c.setStatus(rs.getString("status"));
			}
			System.out.println("logando objeto"+ c.getNome());
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EntidadeImpl> consultar(EntidadeImpl entidade) {
		PreparedStatement pst = null;

		Cliente cliente = (Cliente) entidade;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM clientes WHERE status='ativo'");

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());

			ResultSet rs = pst.executeQuery();
			List<EntidadeImpl> clientes = new ArrayList<EntidadeImpl>();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setSobrenome(rs.getString("sobrenome"));
				c.setEmail(rs.getString("email"));
				c.setCpf(rs.getString("cpf"));
				c.setStatus(rs.getString("status"));

//	            telSql.append("SELECT * FROM telefones WHERE user_id = ? ");
//	            openConnection();
//	            pst = connection.prepareStatement(telSql.toString());
//	            pst.setInt(1, c.getId());
//	            rs = pst.executeQuery();
//	            List<String> phones = new ArrayList<String>();
//	            while (rs.next()) {
//	            	phones.add(rs.getString("numero"));
//	            }
//	            
//	         
//	            endSql.append("SELECT * FROM enderecos WHERE user_id = ? ");
//	            openConnection();
//	            pst = connection.prepareStatement(endSql.toString());
//	            pst.setInt(1, c.getId());
//	            rs = pst.executeQuery();
//	            List<Endereco> enderecos = new ArrayList<Endereco>();
//	            
//	            while (rs.next()) {
//	            	Endereco end = new Endereco();
//	            	end.setId(rs.getInt("id"));
//	            	end.setRua(rs.getString("rua"));
//	            	end.setNumero(rs.getString("numero"));
//	            	end.setBairro(rs.getString("bairro"));
//	            	end.setCep(rs.getString("cep"));
//	            	end.setComplemento(rs.getString("complemento"));
//	            	end.setCidade(rs.getString("cidade"));
//	            	end.setEstado(rs.getString("estado"));
//	            	end.setPais(rs.getString("pais"));
//	            	enderecos.add(end);
//	            }
//	            
//	            c.setTelefones(phones);
//	            c.setEnderecos(enderecos);
				clientes.add(c);
			}

			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
