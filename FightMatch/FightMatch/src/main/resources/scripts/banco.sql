--Criação bd
create database fight_match;


-- Criação da tabela academia
CREATE TABLE academia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    mestre VARCHAR(255) NOT NULL,
    arte_marcial VARCHAR(255) NOT NULL,
    telefone VARCHAR(50),
    email VARCHAR(255)
);

-- Criação da tabela lutador
CREATE TABLE lutador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    peso DOUBLE,
    idade INT,
    graduacao VARCHAR(255),
    disponivel_luta BOOLEAN,
    genero VARCHAR(10), 
    id_academia BIGINT,
    FOREIGN KEY (id_academia) REFERENCES academia(id) ON DELETE CASCADE
);

-- Inserindo dados ficticios academia (todas de Jiu-Jitsu)
INSERT INTO academia (nome, mestre, arte_marcial, telefone, email) VALUES
('Academia Dojo', 'Mestre Yoshi', 'Jiu-Jitsu', '1234-5678', 'dojo@academia.com'),
('Academia Fighter', 'Mestre John', 'Jiu-Jitsu', '9876-5432', 'fighter@academia.com'),
('Academia Samurai', 'Mestre Kenji', 'Jiu-Jitsu', '4567-8910', 'samurai@academia.com'),
('Academia Titan', 'Mestre Bruce', 'Jiu-Jitsu', '3456-7890', 'titan@academia.com'),
('Academia Nova Era', 'Mestre Smith', 'Jiu-Jitsu', '6789-0123', 'novaera@academia.com'),
('Academia Elite', 'Mestre Raúl', 'Jiu-Jitsu', '0123-4567', 'elite@academia.com');


--Inserindo dados ficticios lutadores
INSERT INTO lutador (nome, peso, idade, graduacao, disponivel_luta, genero, id_academia) VALUES
('Lutador A', 30.0, 10, 'Faixa Amarela', true, 'Masculino', 1),
('Lutador B', 31.0, 10, 'Faixa Amarela', true, 'Masculino', 1),
('Lutador C', 35.0, 11, 'Faixa Amarela', true, 'Feminino', 2),
('Lutador D', 45.0, 13, 'Faixa Laranja', true, 'Masculino', 2),
('Lutador E', 36.0, 12, 'Faixa Laranja', true, 'Feminino', 3),
('Lutador F', 48.0, 10, 'Faixa Verde', true, 'Masculino', 3),
('Lutador G', 60.0, 15, 'Faixa Verde', true, 'Masculino', 4),
('Lutador H', 65.0, 20, 'Faixa Azul', true, 'Feminino', 4),
('Lutador I', 70.0, 30, 'Cinturão Preto', false, 'Masculino', 5),
('Lutador J', 72.0, 22, 'Faixa Azul', true, 'Feminino', 5),
('Lutador K', 75.0, 28, 'Faixa Roxa', false, 'Masculino', 6),
('Lutador L', 80.0, 29, 'Faixa Roxa', true, 'Masculino', 6),
('Lutador M', 55.0, 14, 'Faixa Laranja', true, 'Masculino', 1),
('Lutador N', 53.0, 12, 'Faixa Amarela', true, 'Feminino', 2),
('Lutador O', 58.0, 17, 'Faixa Verde', true, 'Masculino', 3);