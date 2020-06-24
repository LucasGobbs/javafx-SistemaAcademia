package sistemaacademia.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemaacademia.model.Cliente;

public class ClienteDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Cliente> listar(){
        String sql = "SELECT * FROM clientes";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setDataNascimento(resultado.getDate("nascimento"));
                cliente.setCpf(resultado.getString("cpf"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public boolean inserir(Cliente cliente){
        String sql = "INSERT INTO clientes(nome, nascimento, cpf) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setDate(2, cliente.getDataNascimento());
            stmt.setString(3, cliente.getCpf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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