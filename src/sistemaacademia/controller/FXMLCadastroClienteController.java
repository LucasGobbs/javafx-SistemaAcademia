/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaacademia.dao.ClienteDAO;
import sistemaacademia.database.Database;
import sistemaacademia.database.DatabaseFactory;
import sistemaacademia.model.Cliente;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLCadastroClienteController implements Initializable {
    
    // TableView ------------------
    @FXML
    private TableView<Cliente> tableViewCliente;

    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;

    @FXML 
    private TableColumn<Cliente, String> tableColumnClienteCpf;
    
    @FXML
    private TableColumn<Cliente, Date> tableColumnClienteDataNascimento;
    // ---------------------------
    
    @FXML
    private TextField textFieldNome;
    
    @FXML
    private TextField textFieldCpf;
    
    
    @FXML
    private DatePicker datePickerDataNascimento;
    
    // Buttons -------------------------
    @FXML
    private Button buttonInserir;
    
    @FXML
    private Button buttonAlterar;
    
    @FXML
    private Button buttonBuscar;
    
    @FXML
    private Button buttonRemover;
    // --------------------------------
    
    
    
    List<Cliente> listClientes = new ArrayList<>();
    ObservableList<Cliente> olistClientes;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    

    public void handleButtonInserir(){
        if(!this.avaliarInput()){
            return;
        }
        clienteDAO.inserir(createCliente());
        refreshTableViewCliente();
    }
    public void handleButtonAlterar(){
        if(!this.avaliarInput()){
            return;
        }
        Cliente selecionado = this.getClienteSelecionado();
        if(selecionado != null){
            Cliente alterado = createCliente();
            alterado.setId(selecionado.getId());
            clienteDAO.alterar(alterado);
            refreshTableViewCliente();
        }
    }
    public void handleButtonRemover(){
      
        Cliente selecionado = this.getClienteSelecionado();
        clienteDAO.remover(selecionado);
        refreshTableViewCliente();
                   
    }
    public void handleButtonLimpar(){
        textFieldNome.setText("");
        textFieldCpf.setText("");
        datePickerDataNascimento.setValue(LocalDate.now());
    }
    public Cliente createCliente(){
        return new Cliente(0,textFieldNome.getText(),Date.valueOf(datePickerDataNascimento.getValue()),textFieldCpf.getText());
    }
    private void handleTableViewSelection(Cliente selecionado){
        if(selecionado != null){
            System.out.println(selecionado.toString());
            this.textFieldNome.setText(selecionado.getNome());
            this.textFieldCpf.setText(selecionado.getCpf());
            this.datePickerDataNascimento.setValue(selecionado.getDataNascimento().toLocalDate());
        }
    }
    private Cliente getClienteSelecionado(){
        return tableViewCliente.getSelectionModel().getSelectedItem();
    }
    private void refreshTableViewCliente(){
        listClientes = clienteDAO.listar();
        olistClientes = FXCollections.observableArrayList(listClientes);
        tableViewCliente.setItems(olistClientes);
        tableViewCliente.refresh();
    }
    private void iniciarTableView(){
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnClienteDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        
        refreshTableViewCliente();
        tableViewCliente.getSelectionModel()  
			.selectedItemProperty()  
			.addListener((observable, oldValue, newValue)  
			->handleTableViewSelection(newValue));  //
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
        datePickerDataNascimento.setValue(LocalDate.of(2000,1,1));
        clienteDAO.setConnection(connection);
        this.iniciarTableView();
    }    
    public boolean avaliarInput(){
        String errorMessage = "";

        if (textFieldNome.getText().isEmpty()) {
            errorMessage += "Digite um nome!\n";
        }
        if (textFieldNome.getText().isEmpty()) {
            errorMessage += "Digite um cpf!\n";
        }
        if (datePickerDataNascimento.getValue() == null) {
            errorMessage += "Data inválida!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
