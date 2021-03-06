﻿create table categorias(

id serial primary key,
descricao character varying (30),
ultima_modificacao timestamp default now()
);

create table dados_matricula(

id serial primary key,
matricula integer,
senha character varying(100),
id_categoria integer not null,
ativo boolean default true,
ultima_modificacao timestamp default now(),
foreign key(id_categoria) references categorias(id)
);

create table alunos(

id serial primary key,
nome character varying(100),
data_nascimento date,
cpf character varying(14),
telefone character varying(15),
email character varying(100),
cep character varying(10),
endereco character varying(200),
cidade character varying(100),
bairro character varying(100),
uf character varying(2),
numero character varying(10),
complemento character varying(200),
id_matricula integer,
ultima_modificacao timestamp default now(),
foreign key(id_matricula) references dados_matricula(id)
);

create table professores(

id serial primary key,
nome character varying(100),
data_nascimento date,
cpf character varying(14),
telefone character varying(15),
email character varying(100),
cep character varying(10),
endereco character varying(200),
cidade character varying(100),
bairro character varying(100),
uf character varying(2),
numero character varying(10),
complemento character varying(200),
id_matricula integer,
ultima_modificacao timestamp default now(),
foreign key(id_matricula) references dados_matricula(id)
);

create table funcionarios(

id serial primary key,
nome character varying(100),
data_nascimento date,
cpf character varying(14),
telefone character varying(15),
email character varying(100),
cep character varying(10),
endereco character varying(200),
cidade character varying(100),
bairro character varying(100),
uf character varying(2),
numero character varying(10),
complemento character varying(200),
id_matricula integer,
ultima_modificacao timestamp default now(),
foreign key(id_matricula) references dados_matricula(id)
);

create table materias(

id serial primary key,
nome character varying (100),
ultima_modificacao timestamp default now()
);

create table cursos(

id serial primary key,
id_materia integer not null,
id_professor integer not null,
ultima_modificacao timestamp default now(),
foreign key(id_materia) references materias(id),
foreign key(id_professor) references professores(id)
);

create table turmas(

id serial primary key,
id_curso integer not null,
id_aluno integer not null,
ultima_modificacao timestamp default now(),
foreign key(id_curso) references cursos(id),
foreign key(id_aluno) references alunos(id)
);

INSERT INTO categorias(descricao)
    VALUES ('Aluno'), ('Professor'), ('Funcionario');

INSERT INTO dados_matricula(matricula, senha, id_categoria)
    VALUES (101010, '123', 3), (202020, '321', 3);

INSERT INTO funcionarios(nome, data_nascimento, cpf, telefone, email, cep, endereco, complemento, bairro, cidade, uf, numero, id_matricula)
    VALUES ('Adm', '1985-12-25', '123.456.789-09', '(82) 99999-9999', 'adm@adm.com', '77.017-280', 'Quadra 903 Sul Alameda 15', 'complemento', 'bairro', 'Palmas', 'MG', 'A1' 1),
	   ('Caio', '2014-02-03', '568.886.004-91', '(82) 88888-8888', 'caio@caio.com', '29.171-741', 'Rua Meca', 'complemento 2', 'Parque Residencial de Tubarão', 'Serra', 'ES', '12', 2);




