package br.edu.unicid.model;

public class NotasFaltas {
	private int id;
	private String disciplina;
	private String semestre;
	private	float nota;
	private int falta;
	private String rgmAluno;
	
	public NotasFaltas() {
		
	}

	public NotasFaltas(int id, String disciplina, String semestre, float nota, int falta, String rgmAluno) {
		super();
		this.id = id;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.nota = nota;
		this.falta = falta;
		this.rgmAluno = rgmAluno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public int getFalta() {
		return falta;
	}

	public void setFalta(int falta) {
		this.falta = falta;
	}

	public String getRgmAluno() {
		return rgmAluno;
	}

	public void setRgmAluno(String rgmAluno) {
		this.rgmAluno = rgmAluno;
	}
	
	
}
