package com.eriksena.torneio.service;

import com.eriksena.torneio.dao.EquipeDAO;
import com.eriksena.torneio.dao.PartidaDAO;
import com.eriksena.torneio.model.Equipe;
import com.eriksena.torneio.model.Partida;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chaveamento {

    private final EquipeDAO equipeDao = new EquipeDAO();
    private final PartidaDAO partidaDao = new PartidaDAO();

    public List<Partida> gerarPrimeiraRodada(int modalidadeId) throws SQLException {
        List<Equipe> equipes = equipeDao.listarPorModalidade(modalidadeId);

        List<Integer> ids = new ArrayList<>();
        for (Equipe e : equipes) {
            ids.add(e.getIdEquipe());
        }
        Collections.shuffle(ids);

        return montarRodada(modalidadeId, 1, ids);
    }

    private List<Partida> montarRodada(int modalidadeId, int rodada, List<Integer> ids) throws SQLException {
        List<Partida> partidas = new ArrayList<>();

        for (int i = 0; i < ids.size(); i += 2) {
            int equipeA = ids.get(i);
            Integer equipeB = null;

            if (i + 1 < ids.size()) {
                equipeB = ids.get(i + 1);
            }

            Partida p1 = new Partida(modalidadeId, rodada, equipeA, equipeB);

            if (equipeB == null) {
                p1.setVencedorId(equipeA);
            }

            partidaDao.inserir(p1);
            partidas.add(p1);
        }
        return partidas;
    }
}
