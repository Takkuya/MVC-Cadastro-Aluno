package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.edu.unicid.model.NotasFaltas;
import br.edu.unicid.util.ConnectionFactory;

public class NotasFaltasDAO {
	private NotasFaltas notasFaltas;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public NotasFaltasDAO() throws Exception {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (Exception err) {
			throw new Exception("Erro: " + err.getMessage());
		}
	}

	public void salvar(NotasFaltas notasFaltas) throws Exception {
		if (notasFaltas == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO Notas_e_faltas(disciplina, semestre, nota, falta, RGM_aluno) " + "VALUES "
					+ "(?, ?, ?, ?, ?);";

			preparedStatement = connection.prepareStatement(SQL);

			preparedStatement.setString(1, notasFaltas.getDisciplina());
			preparedStatement.setString(2, notasFaltas.getSemestre());
			preparedStatement.setString(3, notasFaltas.getNota());
			preparedStatement.setString(4, notasFaltas.getFalta());
			preparedStatement.setString(5, notasFaltas.getRgmAluno());

			System.out.println("disciplina..." + notasFaltas.getDisciplina());
			System.out.println("semestre..." + notasFaltas.getSemestre());
			System.out.println("nota..." + notasFaltas.getNota());
			System.out.println("falta..." + notasFaltas.getFalta());
			System.out.println("rgm..." + notasFaltas.getRgmAluno());

			preparedStatement.executeUpdate();
		} catch (Exception err) {
			System.err.println("Ocorreu um erro ao salvar a nota: " + err.getMessage());
			throw new Exception(err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}

	public void excluir(String rgm, String disciplina, String semestre) throws Exception {
		try {
			String sql = "DELETE FROM Notas_e_faltas " + "WHERE RGM_aluno=? AND disciplina=? AND semestre=?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, rgm);
			preparedStatement.setString(2, disciplina);
			preparedStatement.setString(3, semestre);

			preparedStatement.executeUpdate();
		} catch (Exception err) {
			System.err.println("Ocorreu um erro ao deletar a nota: " + err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}

	public void alterar(NotasFaltas notasFaltas) throws Exception {
		try {
			String sql = "UPDATE notas_e_faltas SET nota=?, falta=? "
					+ "WHERE RGM_aluno=? AND disciplina=? AND semestre=?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, notasFaltas.getNota());
			preparedStatement.setString(2, notasFaltas.getFalta());
			preparedStatement.setString(3, notasFaltas.getRgmAluno());
			preparedStatement.setString(4, notasFaltas.getDisciplina());
			preparedStatement.setString(5, notasFaltas.getSemestre());

			preparedStatement.executeUpdate();
		} catch (Exception err) {
			System.err.println("Ocorreu um erro ao alterar a nota: " + err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}

}
