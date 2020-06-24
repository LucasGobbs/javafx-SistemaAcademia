/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import sistemaacademia.model.Treinador;

/**
 *
 * @author llcos_000
 */
public class TreinadorDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    // TIRAR ------------------------
    private List<Treinador> listTreinador = new ArrayList<Treinador>(){
        {
            add(new Treinador(0,"Lucas",new Date(2000,3,20),"111.111",10,100));
            add(new Treinador(0,"Elian",new Date(1000,3,20),"111.222",8,300));
            add(new Treinador(0,"Douglas",new Date(5000,3,20),"333.222",5,400));
        }
    };
    public List<Treinador> listar(){
        return listTreinador;
    }
    public boolean estaDisponivel(Treinador treinador, Date data, Time hora){
        // Checa se o treinador está disponivel no mes e hora tal
        return true;
    }
    public int getCargaHoraria(Treinador treinador){
       return listTreinador.get(listTreinador.indexOf(treinador)).getCargaHoraria() - 3;
    }
    public float getvalorPorMes(Treinador treinador){
        return listTreinador.get(listTreinador.indexOf(treinador)).getValorPorHora() * 20 + 10;
    }
    public void diminuirCargaHoraria(Treinador treinador,int valor){
        int cargaHoraria = listTreinador.get(listTreinador.indexOf(treinador)).getCargaHoraria();
        listTreinador.get(listTreinador.indexOf(treinador)).setCargaHoraria(cargaHoraria - valor);
    }
}
/*

→ listar 
→ checar carga horaria (carga max - agendamentos
→ valor por mes ( valor por hora * 25 + quantidade de agendamentos * 10)

*/
