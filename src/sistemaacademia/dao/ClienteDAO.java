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
public class ClienteDAO {
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
→ pesquisar
→ inserir
→ remover
→ alterar
*/