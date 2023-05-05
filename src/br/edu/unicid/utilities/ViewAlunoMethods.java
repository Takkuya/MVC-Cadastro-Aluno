package br.edu.unicid.utilities;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.unicid.dao.AlunoDAO;
import br.edu.unicid.model.Aluno;

public class ViewAlunoMethods {
	private String errorMessage = "";
	private boolean hasEmptyField = false;
	private Aluno aluno;

	public Aluno verifyRgm(String rgm) throws Exception {
		AlunoDAO alunoDAO = new AlunoDAO();

		if (rgm.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha o campo de RGM");
			return null;
		}

		Aluno aluno = alunoDAO.verificarSeAlunoExiste(rgm);

		if (aluno == null) {
			JOptionPane.showMessageDialog(null, "Esse RGM não existe");
			return null;
		}

		return aluno;
	}

	public void formValidation(JTextField[] textFields, JComboBox[] comboBoxes, String selectedPeriodo) {
		hasEmptyField = false;

		for (JTextField textField : textFields) {
			if (textField.getText().isEmpty()) {
				hasEmptyField = true;
				errorMessage = "Preencha todos os campos (não esqueça da aba de cursos)";
				break;
			}
		}

		if (!hasEmptyField) {
			for (JComboBox comboBox : comboBoxes) {
				if (comboBox.getSelectedItem() == null) {
					hasEmptyField = true;
					errorMessage = "Selecione uma opção em todos os campos do curso";
					break;
				}
			}
		}

		if (!hasEmptyField && selectedPeriodo == null) {
			hasEmptyField = true;
			errorMessage = "Preencha o período em que você estuda";
		}

		if (hasEmptyField) {
			JOptionPane.showMessageDialog(null, errorMessage);
			return;
		}
	}

	public Aluno saveAluno(String rgm, String nome, Date dataDeNascimento, String cpf, String email, String end,
			String municipio, String uf, String celular, String curso, String campus, String periodo) throws Exception {
		aluno = new Aluno();
		AlunoDAO alunoDAO = new AlunoDAO();

		Aluno alunoExists = alunoDAO.verificarSeAlunoExiste(rgm);

		// se o aluno já existe
		if (alunoExists != null) {
			JOptionPane.showMessageDialog(null, "Já existe um aluno cadastrado com o mesmo RGM");
			return null;
		}

		aluno.setRgm(rgm);
		aluno.setNome(nome);
		aluno.setDataDeNascimento(dataDeNascimento);
		aluno.setCpf(cpf);
		aluno.setEmail(email);
		aluno.setEndereco(end);
		aluno.setMunicipio(municipio);
		aluno.setUf(uf);
		aluno.setCelular(celular);
		aluno.setCurso(curso);
		aluno.setCampus(campus);
		aluno.setPeriodo(periodo);

		alunoDAO.salvar(aluno);

		JOptionPane.showMessageDialog(null, "Aluno criado com sucesso!!");

		return alunoExists;
	}

	public Aluno updateAluno(String rgm, String nome, Date dataDeNascimento, String cpf, String email, String end,
			String municipio, String uf, String celular, String curso, String campus, String periodo) throws Exception {
		aluno = new Aluno();
		AlunoDAO alunoDAO = new AlunoDAO();

		aluno.setRgm(rgm);
		aluno.setNome(nome);
		aluno.setDataDeNascimento(dataDeNascimento);
		aluno.setCpf(cpf);
		aluno.setEmail(email);
		aluno.setEndereco(end);
		aluno.setMunicipio(municipio);
		aluno.setUf(uf);
		aluno.setCelular(celular);
		aluno.setCurso(curso);
		aluno.setCampus(campus);
		aluno.setPeriodo(periodo);

		alunoDAO.alterar(aluno);

		JOptionPane.showMessageDialog(null, "Informações do aluno alteradas com sucesso!!");

		return aluno;
	}

	public void deleteAluno(String rgmTextField) {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();

			String rgm = rgmTextField;

			if (rgm.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, preencha o campo de RGM");
				return;
			}

			Aluno aluno = alunoDAO.verificarSeAlunoExiste(rgm);

			if (aluno == null) {
				JOptionPane.showMessageDialog(null, "Esse RGM não existe");
				return;
			}

			alunoDAO.excluir(rgm);

			JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
		} catch (Exception err) {
			System.err.println("Ocorreu um erro ao excluir o aluno: " + err.getMessage());
		}
	}
}
