package br.edu.unicid.model;

public class NotasFaltas {
	private String disciplina;
	private String semestre;
	private String nota;
	private String falta;
	private String rgmAluno;

	public NotasFaltas() {

	}

	public NotasFaltas(String disciplina, String semestre, String nota, String falta, String rgmAluno) {
		super();
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.nota = nota;
		this.falta = falta;
		this.rgmAluno = rgmAluno;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getFalta() {
		return falta;
	}

	public void setFalta(String falta) {
		this.falta = falta;
	}

	public String getRgmAluno() {
		return rgmAluno;
	}

	public void setRgmAluno(String rgmAluno) {
		this.rgmAluno = rgmAluno;
	}

}
