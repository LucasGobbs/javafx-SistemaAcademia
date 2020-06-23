/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.model;

import java.sql.Date;

/**
 *
 * @author llcos_000
 */
import java.io.Serializable;
public class Treinador implements Serializable{
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private int cargaHoraria;
    private float valorPorHora;

    /**
     * @return the nome
     */
    Treinador(){}
    Treinador(String nome,Date dataNascimento,String cpf, int cargaHoraria, float valorPorHora){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.cargaHoraria = cargaHoraria;
        this.valorPorHora = valorPorHora;
    }
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the cargaHoraria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * @return the valorPorHora
     */
    public float getValorPorHora() {
        return valorPorHora;
    }

    /**
     * @param valorPorHora the valorPorHora to set
     */
    public void setValorPorHora(float valorPorHora) {
        this.valorPorHora = valorPorHora;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}
