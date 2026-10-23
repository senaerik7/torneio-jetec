package com.eriksena.torneio.model;
/**
 *
 * @author eriks
 */
public class Equipe {
    private int idEquipe;
    private String nome;
    private int modalidadeId;
    
    public Equipe(){}
    
    public Equipe(String nome, int modalidadeId){
        this.nome = nome;
        this.modalidadeId = modalidadeId;
    }
    
    public int getIdEquipe(){
        return this.idEquipe;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getModalidadeId(){
        return this.modalidadeId;
    }
    
    public void setIdEquipe(int idEquipe){
        this.idEquipe = idEquipe;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setModalidadeId(int modalidadeId){
        this.modalidadeId = modalidadeId;
    }
    
    @Override
    public String toString(){
        return this.idEquipe + " - " + this.nome + " [" + "modalidade " + this.modalidadeId + "]";
    }
}
