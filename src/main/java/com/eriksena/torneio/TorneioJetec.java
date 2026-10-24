package com.eriksena.torneio;

import com.eriksena.torneio.db.Conexao;
import java.sql.Connection;
import com.eriksena.torneio.model.Modalidade;
import com.eriksena.torneio.model.Equipe;
import com.eriksena.torneio.model.Jogador;
import com.eriksena.torneio.model.Partida;

public class TorneioJetec {

    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar()) {
            System.out.println("Conectado ao banco com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        
        Modalidade m1 = new Modalidade("PARADOXO", 5);
        System.out.println(m1);
        
        Equipe e1 = new Equipe("Horos", 2);
        System.out.println(e1);
        
        Jogador j1 = new Jogador("Erik Senhas", "2025938325", 1);
        System.out.println(j1);
        
        Partida p1 = new Partida(1, 1, 3, 5);
        Partida p2 = new Partida(1, 1, 3, null);
        System.out.println(p1);
        System.out.println(p2);
    }
}