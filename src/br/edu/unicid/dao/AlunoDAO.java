package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.unicid.model.Aluno;
import br.edu.unicid.util.ConnectionFactory;

public class AlunoDAO {
	private Aluno aluno;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public AlunoDAO() throws Exception {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (Exception err) {
			throw new Exception("Erro: " + err.getMessage());
		}
	}

	public void salvar(Aluno aluno) throws Exception {
		try {
			String sql = "INSERT INTO aluno(rgm, nome, dataDeNascimento, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			String dataDeNascimentoString = dateFormater.format(aluno.getDataDeNascimento());
			Date dataDeNascimentoWithoutMask = dateFormater.parse(dataDeNascimentoString.replaceAll("/", "-"));

			java.sql.Date dataDeNascimentoFormatted = new java.sql.Date(dataDeNascimentoWithoutMask.getTime());
			String cpfWithoutMask = aluno.getCpf().replaceAll("[.-]", "");
			String celularWithoutMask = aluno.getCelular().replaceAll("[()\\s-]", "");

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, aluno.getRgm());
			preparedStatement.setString(2, aluno.getNome());
			preparedStatement.setDate(3, dataDeNascimentoFormatted);
			preparedStatement.setString(4, cpfWithoutMask);
			preparedStatement.setString(5, aluno.getEmail());
			preparedStatement.setString(6, aluno.getEndereco());
			preparedStatement.setString(7, aluno.getMunicipio());
			preparedStatement.setString(8, aluno.getUf());
			preparedStatement.setString(9, celularWithoutMask);
			preparedStatement.setString(10, aluno.getCurso());
			preparedStatement.setString(11, aluno.getCampus());
			preparedStatement.setString(12, aluno.getPeriodo());

			preparedStatement.executeUpdate();
		} catch (Exception err) {
			System.err.println("DAO | Ocorreu um erro ao salvar o aluno:  " + err.getMessage());
			throw new Exception(err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}

	public Aluno verificarSeAlunoExiste(String rgm) throws Exception {
		String sql = "SELECT * FROM aluno WHERE rgm = ?";
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, rgm);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Aluno aluno = new Aluno();
					aluno.setRgm(rs.getString("rgm"));
					aluno.setNome(rs.getString("nome"));
					aluno.setDataDeNascimento(rs.getDate("dataDeNascimento"));
					aluno.setCpf(rs.getString("cpf"));
					aluno.setEmail(rs.getString("email"));
					aluno.setEndereco(rs.getString("endereco"));
					aluno.setMunicipio(rs.getString("municipio"));
					aluno.setUf(rs.getString("uf"));
					aluno.setCelular(rs.getString("celular"));
					aluno.setCurso(rs.getString("curso"));
					aluno.setCampus(rs.getString("campus"));
					aluno.setPeriodo(rs.getString("periodo"));

					return aluno;
				} else {
					return null;
				}
			}
		}
	}

	public Aluno consultar(String rgm) throws Exception {
		try {
			String sql = "SELECT * FROM aluno WHERE rgm=?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, rgm);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String nome = resultSet.getString("nome");
				Date dataNascimento = resultSet.getDate("dataDeNascimento");
				String cpf = resultSet.getString("cpf");
				String email = resultSet.getString("email");
				String endereco = resultSet.getString("endereco");
				String municipio = resultSet.getString("municipio");
				String uf = resultSet.getString("uf");
				String celular = resultSet.getString("celular");
				String curso = resultSet.getString("curso");
				String campus = resultSet.getString("campus");
				String periodo = resultSet.getString("periodo");

				aluno = new Aluno(rgm, nome, dataNascimento, cpf, email, endereco, municipio, uf, celular, curso,
						campus, periodo);
			}
		} catch (Exception err) {
			System.err.println("DAO | Ocorreu um erro ao consultar o aluno: " + err.getMessage());
			throw new Exception(err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
		return aluno;
	}

	public List listarAluno(String rgmProcurado) throws Exception {
		List<Aluno> alunoArr = new ArrayList<Aluno>();

		try {
			String sql = "SELECT * FROM aluno WHERE RGM = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, rgmProcurado);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String rgm = resultSet.getString("rgm");
				String nome = resultSet.getString("nome");
				Date dataNascimento = resultSet.getDate("dataDeNascimento");
				String cpf = resultSet.getString("cpf");
				String email = resultSet.getString("email");
				String endereco = resultSet.getString("endereco");
				String municipio = resultSet.getString("municipio");
				String uf = resultSet.getString("uf");
				String celular = resultSet.getString("celular");
				String curso = resultSet.getString("curso");
				String campus = resultSet.getString("campus");
				String periodo = resultSet.getString("periodo");

				aluno = new Aluno(rgm, nome, dataNascimento, cpf, email, endereco, municipio, uf, celular, curso,
						campus, periodo);

				alunoArr.add(aluno);
			}
			return alunoArr;
		} catch (Exception err) {
			throw new Exception("Erro ao listar aluno: " + err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}
	
	

	public void alterar(Aluno aluno) throws Exception {
		try {

			String sql = "UPDATE aluno SET nome=?, dataDeNascimento=?, cpf=?, email=?, endereco=?, municipio=?, uf=?, celular=?, curso=?, campus=?, periodo=? "
					+ "WHERE rgm=?";

			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			Date dataDeNascimento = dateFormater.parse("2023-05-03");
			java.sql.Date dataDeNascimentoFormatted = new java.sql.Date(dataDeNascimento.getTime());

			String cpfWithoutMask = aluno.getCpf().replaceAll("[.-]", "");
			String celularWithoutMask = aluno.getCelular().replaceAll("[()\\s-]", "");

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, aluno.getNome());
			preparedStatement.setDate(2, dataDeNascimentoFormatted);
			preparedStatement.setString(3, cpfWithoutMask);
			preparedStatement.setString(4, aluno.getEmail());
			preparedStatement.setString(5, aluno.getEndereco());
			preparedStatement.setString(6, aluno.getMunicipio());
			preparedStatement.setString(7, aluno.getUf());
			preparedStatement.setString(8, celularWithoutMask);
			preparedStatement.setString(9, aluno.getCurso());
			preparedStatement.setString(10, aluno.getCampus());
			preparedStatement.setString(11, aluno.getPeriodo());

			preparedStatement.setString(12, aluno.getRgm());

			preparedStatement.executeUpdate();

		} catch (Exception err) {
			System.err.println("Ocorreu um erro ao alterar as informações do aluno: " + err.getMessage());
			throw new Exception(err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}

	public void excluir(String rgm) throws Exception {
		try {

			String sql = "DELETE FROM aluno " + "WHERE rgm=?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, rgm);

			preparedStatement.executeUpdate();
		} catch (Exception err) {
			System.err.println("Ocorreu um erro ao excluir aluno: " + err.getMessage());
			throw new Exception(err.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
	}

}
