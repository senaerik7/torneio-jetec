package com.eriksena.torneio;

import com.eriksena.torneio.db.Conexao;
import java.sql.Connection;
import com.eriksena.torneio.model.Modalidade;

public class TorneioJetec {

    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar()) {
            System.out.println("Conectado ao banco com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        
        Modalidade m1 = new Modalidade("PARADOXO", 5);
        System.out.println(m1);
    }
}