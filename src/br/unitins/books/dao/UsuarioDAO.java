package br.unitins.books.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.books.model.TipoUsuario;
import br.unitins.books.model.Usuario;

public class UsuarioDAO {
	
	private static Connection getConnection() {
		Connection conn = null;
		try {
			// registrando o driver do postgres
			Class.forName("org.postgresql.Driver");
			// estabelecendo uma conexao com o banco
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/booksdb", "topicos1", "123456");
			// obrigando a trabalhar com commit e rollback
			conn.setAutoCommit(false);

		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao registrar o driver do postgres.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erro ao conectar no banco de dados.");
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean create (Usuario usuario ) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ");
		sql.append("	(nome, login, senha, datanascimento, email, tipousuario) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ? , ? , ? ) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getLogin());
			stat.setString(3, usuario.getSenha());
			stat.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stat.setString(5, usuario.getEmail());
			stat.setInt(6, usuario.getTipoUsuario().getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public boolean update(Usuario usuario) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario ");
		sql.append("	SET nome=?, login=?, senha=?, datanascimento=?, email=?, tipousuario=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getLogin());
			stat.setString(3, usuario.getSenha());
			stat.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stat.setString(5, usuario.getEmail());
			stat.setInt(6, usuario.getTipoUsuario().getId());
			stat.setInt(7, usuario.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Alteração realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retorno;		
		
	}

	public boolean delete(int id) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM usuario ");
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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public List<Usuario> findAll() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, datanascimento, email, tipousuario ");
		sql.append("FROM ");
		sql.append("	usuario ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getInt("tipousuario")));
				
				listaUsuario.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaUsuario;
	}
	
	public Usuario findById(int id) {
		Usuario usuario = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, datanascimento, email, tipousuario ");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getInt("tipousuario")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}

}
