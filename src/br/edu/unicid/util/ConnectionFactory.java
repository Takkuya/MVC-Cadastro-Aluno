package br.edu.unicid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String login = "root";
			String senha = "An1m&s55085508";
			String url = "jdbc:mysql://localhost:3306/cadastro_aluno_mvc";

			return DriverManager.getConnection(url, login, senha);
		} catch (Exception err) {
			System.err.println("Ocorreu um erro ao conectar com o banco de dados: " + err.getMessage());
			throw new Exception(err.getMessage());
		}
	}

	public static void closeConnection(Connection conn, Statement stmt) throws Exception {
		close(conn, stmt, null);
	}

	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			JOptionPane.showMessageDialog(null, "Banco de dados conectado com sucesso!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
