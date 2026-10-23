package com.eriksena.torneio.model;

/**
 *
 * @author eriks
 */
public class Modalidade {
    private int idMod;
    private String nome;
    private int jogadoresPorEquipe;
    
    public Modalidade(){
    }
    
    public Modalidade(String nome, int jogadoresPorEquipe){
        this.nome = nome;
        this.jogadoresPorEquipe = jogadoresPorEquipe;
    }
    
    public int getIdMod(){
        return this.idMod;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getJogadoresPorEquipe(){
        return this.jogadoresPorEquipe;
    }
    
    public void setIdMod(int idMod){
        this.idMod = idMod;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setJogadoresPorEquipe(int jogadoresPorEquipe){
        this.jogadoresPorEquipe = jogadoresPorEquipe;
    }
    
    @Override
    public String toString(){
        return this.idMod + " - " + this.nome + " [" + this.jogadoresPorEquipe + " jogadores]";
    }
}
