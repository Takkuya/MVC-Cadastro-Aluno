package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.unicid.model.Aluno;
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

	// Verifica se a disciplina e o semestre já existem no banco de dados para
	// determinado aluno
	public boolean verificarDisciplinaSemestreExistente(String rgm, String disciplina, String semestre)
			throws Exception {
		String sql = "SELECT * FROM Notas_e_faltas WHERE rgm_aluno = ? AND disciplina = ? AND semestre = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			System.out.println("pinto" + rgm);
			System.out.println("pinto" + disciplina);
			System.out.println("pinto" + semestre);
			
			preparedStatement.setString(1, rgm);
			preparedStatement.setString(2, disciplina);
			preparedStatement.setString(3, semestre);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

	public void salvar(NotasFaltas notasFaltas) throws Exception {
		try {
			String SQL = "INSERT INTO notas_e_faltas(disciplina, semestre, nota, falta, RGM_aluno) " + "VALUES "
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
			String sql = "UPDATE Notas_e_faltas SET nota=?, falta=? "
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

	public List<NotasFaltas> listarNota(String rgm) throws Exception {
		List<NotasFaltas> notasFaltasArr = new ArrayList<NotasFaltas>();

		try {
			String sql = "SELECT * FROM Notas_e_faltas WHERE RGM_aluno = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, rgm);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String disciplina = resultSet.getString("disciplina");
				String semestre = resultSet.getString("semestre");
				String nota = resultSet.getString("nota");
				String falta = resultSet.getString("falta");
				String rgmAluno = resultSet.getString("RGM_aluno");

				notasFaltas = new NotasFaltas(disciplina, semestre, nota, falta, rgmAluno);

				notasFaltasArr.add(notasFaltas);
			}
			return notasFaltasArr;
		} catch (Exception err) {
			throw new Exception("Erro ao listar todas as notas: " + err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}

}
