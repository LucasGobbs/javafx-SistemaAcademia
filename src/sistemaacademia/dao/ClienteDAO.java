/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.dao;

import java.sql.Connection;

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++ TIRAR
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import sistemaacademia.model.Cliente;
// +++++++++++++++++++
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
    //Tirar -------------------
    private List<Cliente> listCliente = new ArrayList<Cliente>(){
        {
            add(new Cliente(0,"aaaa",new Date(2000,05,28),"111.111"));
            add(new Cliente(0,"bbbb",new Date(2001,05,28),"222.222"));
            add(new Cliente(0,"cccc",new Date(2001,05,28),"333.333"));
        }
    };
    //------------------------
    public List<Cliente> listar(){
        return listCliente;
    }
    public void inserir(Cliente cliente){
        listCliente.add(cliente);
    }
    public void remover(Cliente cliente){
        listCliente.remove(cliente);  
    }
    public void alterar(Cliente cliente_antes,Cliente Cliente_depois ){
        listCliente.get(listCliente.indexOf(cliente_antes)).setNome(Cliente_depois.getNome());
        listCliente.get(listCliente.indexOf(cliente_antes)).setCpf(Cliente_depois.getCpf());
        listCliente.get(listCliente.indexOf(cliente_antes)).setDataNascimento(Cliente_depois.getDataNascimento());
    }
}
/*
→ listar 
→ pesquisar
→ inserir
→ remover
→ alterar
*/