CREATE TABLE modalidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    jogadores_por_equipe INT NOT NULL
);

CREATE TABLE equipe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    modalidade_id INT NOT NULL,
    FOREIGN KEY (modalidade_id) REFERENCES modalidade(id)
);

CREATE TABLE jogador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    equipe_id INT NOT NULL,
    FOREIGN KEY (equipe_id) REFERENCES equipe(id)
);

CREATE TABLE partida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modalidade_id INT NOT NULL,
    rodada INT NOT NULL,
    equipe_a_id INT NOT NULL,
    equipe_b_id INT,
    placar_a INT,
    placar_b INT,
    vencedor_id INT,
    FOREIGN KEY (modalidade_id) REFERENCES modalidade(id),
    FOREIGN KEY (equipe_a_id) REFERENCES equipe(id),
    FOREIGN KEY (equipe_b_id) REFERENCES equipe(id),
    FOREIGN KEY (vencedor_id) REFERENCES equipe(id)
);