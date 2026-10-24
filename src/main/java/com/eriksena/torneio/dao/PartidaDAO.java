package com.eriksena.torneio.dao;

import com.eriksena.torneio.db.Conexao;
import com.eriksena.torneio.model.Partida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

    private static final String COLUNAS =
        "id, modalidade_id, rodada, equipe_a_id, equipe_b_id, placar_a, placar_b, vencedor_id";

    public void inserir(Partida p) throws SQLException {
        String sql = "INSERT INTO partida "
                   + "(modalidade_id, rodada, equipe_a_id, equipe_b_id, placar_a, placar_b, vencedor_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getModalidadeId());
            ps.setInt(2, p.getRodada());
            ps.setInt(3, p.getEquipeAId());
            definirInteiro(ps, 4, p.getEquipeBId());
            definirInteiro(ps, 5, p.getPlacarA());
            definirInteiro(ps, 6, p.getPlacarB());
            definirInteiro(ps, 7, p.getVencedorId());
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    p.setIdPartida(chaves.getInt(1));
                }
            }
        }
    }

    public List<Partida> listar() throws SQLException {
        String sql = "SELECT " + COLUNAS + " FROM partida ORDER BY rodada, id";
        List<Partida> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public List<Partida> listarPorRodada(int modalidadeId, int rodada) throws SQLException {
        String sql = "SELECT " + COLUNAS + " FROM partida WHERE modalidade_id = ? AND rodada = ? ORDER BY id";
        List<Partida> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, modalidadeId);
            ps.setInt(2, rodada);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(montar(rs));
                }
            }
        }
        return lista;
    }

    public Partida buscarPorId(int id) throws SQLException {
        String sql = "SELECT " + COLUNAS + " FROM partida WHERE id = ?";

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

    public void atualizar(Partida p) throws SQLException {
        String sql = "UPDATE partida SET modalidade_id = ?, rodada = ?, equipe_a_id = ?, "
                   + "equipe_b_id = ?, placar_a = ?, placar_b = ?, vencedor_id = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, p.getModalidadeId());
            ps.setInt(2, p.getRodada());
            ps.setInt(3, p.getEquipeAId());
            definirInteiro(ps, 4, p.getEquipeBId());
            definirInteiro(ps, 5, p.getPlacarA());
            definirInteiro(ps, 6, p.getPlacarB());
            definirInteiro(ps, 7, p.getVencedorId());
            ps.setInt(8, p.getIdPartida());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM partida WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private void definirInteiro(PreparedStatement ps, int posicao, Integer valor) throws SQLException {
        if (valor == null) {
            ps.setNull(posicao, Types.INTEGER);
        } else {
            ps.setInt(posicao, valor);
        }
    }

    private Partida montar(ResultSet rs) throws SQLException {
        Partida p = new Partida();
        p.setIdPartida(rs.getInt("id"));
        p.setModalidadeId(rs.getInt("modalidade_id"));
        p.setRodada(rs.getInt("rodada"));
        p.setEquipeAId(rs.getInt("equipe_a_id"));
        p.setEquipeBId(rs.getObject("equipe_b_id", Integer.class));
        p.setPlacarA(rs.getObject("placar_a", Integer.class));
        p.setPlacarB(rs.getObject("placar_b", Integer.class));
        p.setVencedorId(rs.getObject("vencedor_id", Integer.class));
        return p;
    }
}