/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author llcos_000
 */
public class Agendamento implements Serializable {
    private int id;
    private Date dataInicio;
    private Time horario;
    private Treinador treinador;
    private Cliente cliente;
    private float valor;

    /**
     * @return the dataInicio
     */
    public Agendamento(){}
    public Agendamento(int id, Date dataInicio, Time horario, Treinador treinador, Cliente cliente, float valor){
        this.id = id;
        this.dataInicio = dataInicio;
        this.horario = horario;
        this.treinador = treinador;
        this.cliente = cliente;
        this.valor = valor;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the horario
     */
    public Time getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Time horario) {
        this.horario = horario;
    }

    /**
     * @return the treinador
     */
    public Treinador getTreinador() {
        return treinador;
    }

    /**
     * @param treinador the treinador to set
     */
    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return String.format("Agendamento: Treinador:%s | Cliente: %s", this.treinador.toString(), this.cliente.toString());
    }
}
/*
→ Data de inicio
→ Horario
→ Treinador
→ Cliente
→ Valor ( treinador->valor_porhora * 30 )


*/
