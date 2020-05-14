package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class TesteBanco {
	public static void main(String[] args) {
		
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ");
		sql.append("	(nomes, login, senha, datanascimento, email, tipousuario) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ? , ? , ? ) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "Fredson");
			stat.setString(2, "fred");
			stat.setString(3, "123");
			stat.setDate(4, java.sql.Date.valueOf(LocalDate.of(1990, 2, 10)));
			stat.setString(5, "fred@gmail.com");
			stat.setInt(6, 1);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");
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
		
	}

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

}
