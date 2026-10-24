package com.eriksena.torneio.model;

/**
 *
 * @author eriks
 */
public class Partida {
    private int idPartida;
    private int modalidadeId;
    private int rodada;
    private int equipeAId;
    private Integer equipeBId;
    private Integer placarA;
    private Integer placarB;
    private Integer vencedorId;
    
    public Partida(){}
    
    public Partida(int modalidadeId, int rodada, int equipeAId, Integer equipeBId){
        this.modalidadeId = modalidadeId;
        this.rodada = rodada;
        this.equipeAId = equipeAId;
        this.equipeBId = equipeBId;
    }
    
    public int getIdPartida(){
        return this.idPartida;
    }
    
    public int getModalidadeId(){
        return this.modalidadeId;
    }
    
    public int getRodada(){
        return this.rodada;
    }
    
    public int getEquipeAId(){
        return this.equipeAId;
    }
    
    public Integer getEquipeBId(){
        return this.equipeBId;
    }
    
    public Integer getPlacarA(){
        return this.placarA;
    }
    
    public Integer getPlacarB(){
        return this.placarB;
    }
    
    public Integer getVencedorId(){
        return this.vencedorId;
    }
    
    public void setIdPartida(int idPartida){
        this.idPartida = idPartida;
    }
    
    public void setModalidadeId(int modalidadeId){
        this.modalidadeId = modalidadeId;
    }
    
    public void setRodada(int rodada){
        this.rodada = rodada;
    }
    
    public void setEquipeAId(int equipeAId){
        this.equipeAId = equipeAId;
    }
    
    public void setEquipeBId(Integer equipeBId){
        this.equipeBId = equipeBId;
    }
    
    public void setPlacarA(Integer placarA){
        this.placarA = placarA;
    }
    
    public void setPlacarB(Integer placarB){
        this.placarB = placarB;
    }
    
    public void setVencedorId(Integer vencedorId){
        this.vencedorId = vencedorId;
    }
    
    @Override
    public String toString(){
        String lado;
        if(this.equipeBId == null){
            lado = "folga";
        }else{
            lado = "equipe " + this.equipeBId;
        }
        return "Rodada " + this.rodada + ": equipe " + this.equipeAId + " x " + lado;
    }
    
}
