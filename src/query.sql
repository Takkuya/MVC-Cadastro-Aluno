SHOW DATABASES;

USE cadastro_aluno_mvc;

CREATE TABLE aluno (
  rgm CHAR(8) NOT NULL,
  nome VARCHAR(50) NOT NULL,
  dataDeNascimento DATE NOT NULL,
  cpf CHAR(11) NOT NULL,
  email VARCHAR(40) NOT NULL,
  endereco VARCHAR(60) NOT NULL,
  municipio VARCHAR(40) NOT NULL,
  uf CHAR(2) NOT NULL,
  celular CHAR(11) NOT NULL,
  curso VARCHAR(50) NOT NULL,
  campus VARCHAR(20) NOT NULL,
  periodo VARCHAR(10) NOT NULL,
  PRIMARY KEY (RGM)
);
 
SELECT * FROM aluno;

-- Deletar tabela
-- DROP TABLE aluno;

CREATE TABLE Notas_e_faltas (
  id INT(11) NOT NULL AUTO_INCREMENT,
  disciplina VARCHAR(50) NOT NULL,
  semestre VARCHAR(6) NOT NULL,
  nota DECIMAL(3,1) NOT NULL,
  falta INT(11) NOT NULL,
  RGM_aluno CHAR(8) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (RGM_aluno) REFERENCES aluno(RGM) ON DELETE CASCADE
);

-- Deletar tabela
-- DROP TABLE Notas_e_faltas;

SELECT * FROM Notas_e_faltas;

