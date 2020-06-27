/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemaacademia.model.Agendamento;
import sistemaacademia.model.Cliente;
import sistemaacademia.model.Treinador;

public class AgendamentoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Agendamento> listar(){
        //retorna lista com todos os agendamentos
        String sql = "SELECT * FROM agendamentos";
        List<Agendamento> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(resultado.getInt("id"));
                agendamento.setDataInicio(resultado.getDate("data_inicio"));
                agendamento.setHorario(resultado.getTime("horario"));
                
                //busca do treinador
                Treinador treinadorAgendamento = new Treinador();
                String sql2 = "SELECT * FROM treinadores WHERE id = ?";
                PreparedStatement stmt2 = connection.prepareStatement(sql2);
                stmt2.setInt(1, resultado.getInt("treinador_id"));
                ResultSet resultado2 = stmt2.executeQuery();
                if (resultado2.next()) {
                    treinadorAgendamento.setId(resultado2.getInt("id"));
                    treinadorAgendamento.setNome(resultado2.getString("nome"));
                    treinadorAgendamento.setDataNascimento(resultado2.getDate("nascimento"));
                    treinadorAgendamento.setCpf(resultado2.getString("cpf"));
                    treinadorAgendamento.setCargaHoraria(resultado2.getInt("carga_horaria"));
                    treinadorAgendamento.setValorPorHora(resultado2.getFloat("valor_por_hora"));
                }
                agendamento.setTreinador(treinadorAgendamento);
                
                //busca do cliente
                Cliente clienteAgendamento = new Cliente();
                String sql3 = "SELECT * FROM clientes WHERE id = ?";
                PreparedStatement stmt3 = connection.prepareStatement(sql3);
                stmt3.setInt(1, resultado.getInt("cliente_id"));
                ResultSet resultado3 = stmt3.executeQuery();
                if (resultado3.next()){
                    clienteAgendamento.setId(resultado3.getInt("id"));
                    clienteAgendamento.setNome(resultado3.getString("nome"));
                    clienteAgendamento.setDataNascimento(resultado3.getDate("nascimento"));
                    clienteAgendamento.setCpf(resultado3.getString("cpf"));
                }
                agendamento.setCliente(clienteAgendamento);
                agendamento.setValor(resultado.getFloat("valor"));
                retorno.add(agendamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
