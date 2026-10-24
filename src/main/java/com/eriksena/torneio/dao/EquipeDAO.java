package com.eriksena.torneio.dao;

import com.eriksena.torneio.db.Conexao;
import com.eriksena.torneio.model.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {

    public void inserir(Equipe e) throws SQLException {
        String sql = "INSERT INTO equipe (nome, modalidade_id) VALUES (?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNome());
            ps.setInt(2, e.getModalidadeId());
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    e.setIdEquipe(chaves.getInt(1));
                }
            }
        }
    }

    public List<Equipe> listar() throws SQLException {
        String sql = "SELECT id, nome, modalidade_id FROM equipe ORDER BY id";
        List<Equipe> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public List<Equipe> listarPorModalidade(int modalidadeId) throws SQLException {
        String sql = "SELECT id, nome, modalidade_id FROM equipe WHERE modalidade_id = ? ORDER BY id";
        List<Equipe> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, modalidadeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(montar(rs));
                }
            }
        }
        return lista;
    }

    public Equipe buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, modalidade_id FROM equipe WHERE id = ?";

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

    public void atualizar(Equipe e) throws SQLException {
        String sql = "UPDATE equipe SET nome = ?, modalidade_id = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, e.getNome());
            ps.setInt(2, e.getModalidadeId());
            ps.setInt(3, e.getIdEquipe());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM equipe WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Equipe montar(ResultSet rs) throws SQLException {
        Equipe e = new Equipe();
        e.setIdEquipe(rs.getInt("id"));
        e.setNome(rs.getString("nome"));
        e.setModalidadeId(rs.getInt("modalidade_id"));
        return e;
    }
}