package com.eriksena.torneio;

import com.eriksena.torneio.db.Conexao;
import java.sql.Connection;
import com.eriksena.torneio.model.Modalidade;
import com.eriksena.torneio.model.Equipe;
import com.eriksena.torneio.model.Jogador;
import com.eriksena.torneio.model.Partida;
import com.eriksena.torneio.dao.ModalidadeDAO;
import com.eriksena.torneio.dao.EquipeDAO;
import com.eriksena.torneio.dao.JogadorDAO;
import com.eriksena.torneio.dao.PartidaDAO;

public class TorneioJetec {

    public static void main(String[] args) throws Exception {
        try (Connection conexao = Conexao.conectar()) {
            System.out.println("Conectado ao banco com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
