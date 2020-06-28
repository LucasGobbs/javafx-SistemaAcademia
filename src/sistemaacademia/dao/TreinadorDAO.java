/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemaacademia.model.Agendamento;
import sistemaacademia.model.Treinador;

public class TreinadorDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Treinador> listar(){
        //retorna lista com todos os treinadores
        String sql = "SELECT * FROM treinadores";
        List<Treinador> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Treinador treinador = new Treinador();
                treinador.setId(resultado.getInt("id"));
                treinador.setNome(resultado.getString("nome"));
                treinador.setDataNascimento(resultado.getDate("nascimento"));
                treinador.setCpf(resultado.getString("cpf"));
                treinador.setCargaHoraria(resultado.getInt("carga_horaria"));
                treinador.setValorPorHora(resultado.getFloat("valor_por_hora"));
                retorno.add(treinador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public boolean estaDisponivel(Treinador treinador, Date data, Time hora){
        // Checa se o treinador está disponivel no mes e hora tal
        String sql = "SELECT * FROM agendamentos WHERE treinador_id=? AND data_inicio = ? AND horario = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, treinador.getId());
            stmt.setDate(2, data);
            stmt.setTime(3, hora);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean checarCargaHoraria(Treinador treinador, Date dataInicio, Date dataFim){
        //checa se o treinador tem carga horária disponível ou não
        String sql = "SELECT * FROM agendamentos WHERE treinador_id=? AND data_inicio BETWEEN ? AND ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, treinador.getId());
            stmt.setDate(2, dataInicio);
            stmt.setDate(3, dataFim);
            ResultSet resultado = stmt.executeQuery();
            int cont = 0;
            while (resultado.next()) {
                cont++;
            }
            if(cont < treinador.getCargaHoraria()){
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getCargaHoraria(Treinador treinador){
       //retorna a carga horaria do treinador já subtraida da contidade de agendamento
        String sql = "SELECT * FROM agendamentos WHERE treinador_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, treinador.getId());
            ResultSet resultado = stmt.executeQuery();
            int cont = 0;
            while (resultado.next()) {
                cont++;
            }
            return treinador.getCargaHoraria() - cont;
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public float getValorPorMes(Treinador treinador){
        //retorna o valor cobrado por hora do treinador em questão
        String sql = "SELECT * FROM agendamentos WHERE treinador_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, treinador.getId());
            ResultSet resultado = stmt.executeQuery();
            int cont = 0;
            while (resultado.next()) {
                cont++;
            }
            return treinador.getValorPorHora() * 25 + cont * 10;
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
