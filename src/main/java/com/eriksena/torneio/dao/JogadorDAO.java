package com.eriksena.torneio.dao;

import com.eriksena.torneio.db.Conexao;
import com.eriksena.torneio.model.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {

    public void inserir(Jogador j) throws SQLException {
        String sql = "INSERT INTO jogador (nome, matricula, equipe_id) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, j.getNome());
            ps.setString(2, j.getMatricula());
            ps.setInt(3, j.getEquipeId());
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    j.setIdJogador(chaves.getInt(1));
                }
            }
        }
    }

    public List<Jogador> listar() throws SQLException {
        String sql = "SELECT id, nome, matricula, equipe_id FROM jogador ORDER BY id";
        List<Jogador> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public List<Jogador> listarPorEquipe(int equipeId) throws SQLException {
        String sql = "SELECT id, nome, matricula, equipe_id FROM jogador WHERE equipe_id = ? ORDER BY id";
        List<Jogador> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, equipeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(montar(rs));
                }
            }
        }
        return lista;
    }

    public Jogador buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, matricula, equipe_id FROM jogador WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return montar(rs);
                }
            }
        }
        return null;
    }

    public void atualizar(Jogador j) throws SQLException {
        String sql = "UPDATE jogador SET nome = ?, matricula = ?, equipe_id = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, j.getNome());
            ps.setString(2, j.getMatricula());
            ps.setInt(3, j.getEquipeId());
            ps.setInt(4, j.getIdJogador());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM jogador WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Jogador montar(ResultSet rs) throws SQLException {
        Jogador j = new Jogador();
        j.setIdJogador(rs.getInt("id"));
        j.setNome(rs.getString("nome"));
        j.setMatricula(rs.getString("matricula"));
        j.setEquipeId(rs.getInt("equipe_id"));
        return j;
    }
}