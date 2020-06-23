/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.sql.Connection;

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
}
/*
→ listar
→ inserir agendamento
→ Checar se está disponivel (no mes x e hora y, para o treinador z)
→ quantidade de agendamentos de um treinador especifico
*/
