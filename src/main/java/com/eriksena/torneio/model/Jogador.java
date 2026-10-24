package com.eriksena.torneio.model;

public class Jogador {
    private int idJogador;
    private String nome;
    private String matricula;
    private int equipeId;
    
    public Jogador(){}
    
    public Jogador(String nome, String matricula, int equipeId){
        this.nome = nome;
        this.matricula = matricula;
        this.equipeId = equipeId;
    }
    
    public int getIdJogador(){
        return this.idJogador;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public int getEquipeId(){
        return this.equipeId;
    }
    
    public void setIdJogador(int idJogador){
        this.idJogador = idJogador;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public void setEquipeId(int equipeId){
        this.equipeId = equipeId;
    }
    
    @Override
    public String toString(){
        return this.idJogador + " - " + this.nome + " - " + " matricula " + this.matricula + " - " + this.equipeId;
    }
    
}
