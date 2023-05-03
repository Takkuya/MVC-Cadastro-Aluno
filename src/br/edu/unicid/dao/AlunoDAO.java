package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public List listarTodos() throws Exception {
		List<Aluno> alunoArr = new ArrayList<Aluno>();

		try {
			String sql = "SELECT * FROM aluno";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int rgm = resultSet.getInt("rgm");
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

				aluno = new Aluno(rgm, nome, dataNascimento, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo);

				alunoArr.add(aluno);
			}
			
			System.out.println(alunoArr);
			return alunoArr;
		} catch (Exception err) {
			throw new Exception("Erro ao listar todos os alunos: " + err.getMessage());
		}
	}

}
