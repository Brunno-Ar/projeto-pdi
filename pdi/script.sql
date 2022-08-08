CREATE TABLE funcionario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cargo VARCHAR(50),
    email VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE pdi (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao DATE NOT NULL,
    avaliacao TEXT NOT NULL,
    fk_funcionario BIGINT(20) NOT NULL,
    FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create database pdi;


select * from funcionario;
select * from pdi;

use pdi