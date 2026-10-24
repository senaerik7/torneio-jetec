package com.eriksena.torneio.dao;

import com.eriksena.torneio.db.Conexao;
import com.eriksena.torneio.model.Modalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModalidadeDAO {

    public void inserir(Modalidade m) throws SQLException {
        String sql = "INSERT INTO modalidade (nome, jogadores_por_equipe) VALUES (?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getNome());
            ps.setInt(2, m.getJogadoresPorEquipe());
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    m.setIdMod(chaves.getInt(1));
                }
            }
        }
    }

    public List<Modalidade> listar() throws SQLException {
        String sql = "SELECT id, nome, jogadores_por_equipe FROM modalidade ORDER BY id";
        List<Modalidade> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public Modalidade buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, jogadores_por_equipe FROM modalidade WHERE id = ?";

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

    public void atualizar(Modalidade m) throws SQLException {
        String sql = "UPDATE modalidade SET nome = ?, jogadores_por_equipe = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, m.getNome());
            ps.setInt(2, m.getJogadoresPorEquipe());
            ps.setInt(3, m.getIdMod());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM modalidade WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Modalidade montar(ResultSet rs) throws SQLException {
        Modalidade m = new Modalidade();
        m.setIdMod(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setJogadoresPorEquipe(rs.getInt("jogadores_por_equipe"));
        return m;
    }
}