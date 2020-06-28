/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    
    public boolean inserir(Agendamento agendamento){
        String sql = "INSERT INTO agendamentos(data_inicio, horario, treinador_id, cliente_id, valor) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, agendamento.getDataInicio());
            stmt.setTime(2, agendamento.getHorario());
            stmt.setInt(3, agendamento.getTreinador().getId());
            stmt.setInt(4, agendamento.getCliente().getId());
            //stmt.setFloat(5, agendamento.getValor());
            stmt.setBigDecimal(5,BigDecimal.valueOf(agendamento.getValor()));
            
            stmt.execute();
            return true;
        } catch(SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public int agendamentosTreinador(Treinador treinador){
        //retorna a quantidade de agendamentos de um treinador especifico
        String sql = "SELECT * FROM agendamentos WHERE treinador_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, treinador.getId());
            ResultSet resultado = stmt.executeQuery();
            int cont = 0;
            while (resultado.next()) {
                cont++;
            }
            return cont;
        } catch(SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public Map<String, ArrayList> listarQuantidadeDeAgendamentosPorTreinador(String ano){
        //retorna lista com todos os treinadores
        String sql = String.format("SELECT t.nome, extract(month from a.data_inicio)as mes, count(a.id) as quantidade "+
	"from treinadores as t,agendamentos as a "+
	"WHERE t.id = a.treinador_id "+
        "AND extract(year from a.data_inicio) = '%s' "+
	"GROUP BY t.nome, mes; ", ano);
        System.out.println(sql);
        Map<String, ArrayList> retorno = new HashMap();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //stmt.setString(1, ano);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                // Se n tem a linha
                if(!retorno.containsKey(resultado.getString("nome"))){
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("quantidade"));
                    retorno.put(resultado.getString("nome"), linha);        
                } else {
                    ArrayList linhaNova = retorno.get(resultado.getString("nome"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("quantidade"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
   
    
}
