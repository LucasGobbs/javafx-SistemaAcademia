/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.sql.Connection;
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
    
    public List<Treinador> listar(){
        return new ArrayList<Treinador>();
    }
    public boolean checarCargaHoraria(Treinador treinador){
        return true;
    }
    public float valorPorMes(Treinador treinador){
        return (float) 1.0;
    }
}
/*

→ listar 
→ checar carga horaria (carga max - agendamentos
→ valor por mes ( valor por hora * 25 + quantidade de agendamentos * 10)

*/
