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

/*
INSERT INTO aluno (rgm, nome, dataDeNascimento, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo)
VALUES 
("12345678", 'João Silva', '1990-02-01', '12345678910', 'joao.silva@email.com',
 'Rua das Flores, 123', 'São Paulo', 'SP', '11987654321', 'Ciência da Computação', 'São Paulo', 'Noturno'),
("23456789", 'Maria Santos', '1992-05-15', '98765432100', 'maria.santos@email.com',
 'Avenida Brasil, 456', 'Rio de Janeiro', 'RJ', '21976543210', 'Direito', 'Rio de Janeiro', 'Matutino'),
("34567890", 'Pedro Oliveira', '1991-11-20', '11122233344', 'pedro.oliveira@email.com',
 'Rua das Palmeiras, 789', 'Belo Horizonte', 'MG', '31987654321', 'Administração', 'Belo Horizonte', 'Vespertino');
 */
 
SELECT * FROM aluno;

DELETE FROM aluno WHERE 1=1;


-- DROP TABLE aluno;

CREATE TABLE Notas_e_faltas (
  id INT(11) NOT NULL AUTO_INCREMENT,
  disciplina VARCHAR(50) NOT NULL,
  semestre VARCHAR(6) NOT NULL,
  nota DECIMAL(3,1) NOT NULL,
  falta INT(11) NOT NULL,
  RGM_aluno CHAR(8) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (RGM_aluno) REFERENCES aluno(RGM)
);

DROP TABLE Notas_e_faltas;

INSERT INTO Notas_e_faltas (disciplina, semestre, nota, falta, RGM_aluno)
VALUES 
('Matemática', '2022/1', 8.5, 3, 12312213
),
('Física', '2022/1', 7.8, 1, 23543454
),
('Química', '2022/1', 6.9, 2, 33123122
),
('História', '2021/2', 9.1, 0, 34545633),
('Geografia', '2021/2', 7.2, 1, 76456454);

SELECT * FROM Notas_e_faltas;

