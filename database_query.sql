CREATE TABLE clientes (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	nascimento DATE NOT NULL,
	cpf VARCHAR(15) NOT NULL
);

CREATE TABLE treinadores(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	nascimento DATE NOT NULL,
	cpf VARCHAR(15) NOT NULL,
	carga_horaria INTEGER NOT NULL,
	valor_por_hora NUMERIC(4, 2) NOT NULL
);

CREATE TABLE agendamentos(
	id SERIAL PRIMARY KEY,
	data_inicio DATE NOT NULL,
	horario TIME NOT NULL,
	treinador_id INTEGER NOT NULL,
	cliente_id INTEGER NOT NULL,
	valor NUMERIC(6, 2) NOT NULL,
	FOREIGN KEY (treinador_id)
		REFERENCES treinadores (id),
	FOREIGN KEY (cliente_id)
		REFERENCES clientes (id)
);
INSERT INTO clientes(nome,
	             nascimento,
		     cpf) values('Lucas Costa Gobbi', '20000427 00:00:00 AM', '145.982.456-37');
INSERT INTO treinadores(nome, 
			nascimento, 
			cpf, 
			carga_horaria, 
			valor_por_hora) VALUES('Igor Nascimento','19950518 00:00:00 AM','145.267.126-31',5,30);
INSERT INTO treinadores(nome, 
			nascimento, 
			cpf, 
			carga_horaria, 
			valor_por_hora) VALUES('João Guilherme','19850203 00:00:00 AM','915.028.512-21',4,60);
