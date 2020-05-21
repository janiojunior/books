package br.unitins.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.books.model.Livro;

public class LivroDAO extends DAO<Livro> {
	
	public boolean create (Livro livro) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO livro ");
		sql.append("	(descricao, isbn, preco, estoque) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ? ) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, livro.getDescricao());
			stat.setString(2, livro.getIsbn());
			stat.setFloat(3, livro.getPreco());
			stat.setInt(4, livro.getEstoque());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public boolean update(Livro livro) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE livro ");
		sql.append("	SET descricao=?, isbn=?, preco=?, estoque=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, livro.getDescricao());
			stat.setString(2, livro.getIsbn());
			stat.setFloat(3, livro.getPreco());
			stat.setInt(4, livro.getEstoque());
			stat.setInt(5, livro.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Alteração realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;		
		
	}

	public boolean delete(int id) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM livro ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Remoção realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public List<Livro> findAll() {
		List<Livro> listaLivro = new ArrayList<Livro>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, isbn, preco, estoque ");
		sql.append("FROM ");
		sql.append("	livro ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Livro livro = null;
			
			while(rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setIsbn(rs.getString("isbn"));
				livro.setPreco(rs.getFloat("preco"));
				livro.setEstoque(rs.getInt("estoque"));
				
				listaLivro.add(livro);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaLivro;
	}
	
	public Livro findById(int id) {
		Livro livro = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, isbn, preco, estoque ");
		sql.append("FROM ");
		sql.append("	livro ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setIsbn(rs.getString("isbn"));
				livro.setPreco(rs.getFloat("preco"));
				livro.setEstoque(rs.getInt("estoque"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return livro;
	}

}
