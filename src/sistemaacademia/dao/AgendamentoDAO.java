/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import sistemaacademia.model.Agendamento;

/**
 *
 * @author llcos_000
 */
public class AgendamentoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    private List<Agendamento> listAgendamento = new ArrayList<>();
    public List<Agendamento> listar(){
        return listAgendamento;
    }
    public void inserir(Agendamento agendamento){
        listAgendamento.add(agendamento);
    }
}
/*
→ listar
→ inserir agendamento
→ Checar se está disponivel (no mes x e hora y, para o treinador z)
→ quantidade de agendamentos de um treinador especifico
*/
