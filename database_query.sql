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
	valor NUMERIC(4, 2) NOT NULL,
	FOREIGN KEY (treinador_id)
		REFERENCES treinadores (id),
	FOREIGN KEY (cliente_id)
		REFERENCES clientes (id)
);
