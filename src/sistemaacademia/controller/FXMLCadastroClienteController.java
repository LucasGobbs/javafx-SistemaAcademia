/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaacademia.model.Cliente;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLCadastroClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewCliente;

    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;

    @FXML 
    private TableColumn<Cliente, String> tableColumnClienteCpf;
    
    @FXML
    private TableColumn<Cliente, Date> tableColumnClienteDataNascimento;
    
    @FXML
    private TextField textFieldNome;
    
    @FXML
    private TextField textFieldCpf;
    
    @FXML
    private Button buttonInserir;
    
    @FXML
    private Button buttonAlterar;
    
    @FXML
    private Button buttonBuscar;
    
    @FXML
    private Button buttonRemover;
    
    @FXML
    private DatePicker datePickerDataNascimento;
    
    List<Cliente> listClientes = new ArrayList<>();
    ObservableList<Cliente> olistClientes;
    
    
    public void handleButtonLimpar(){
        textFieldNome.setText("");
        textFieldCpf.setText("");
        datePickerDataNascimento.setValue(LocalDate.now());
    }
    public void handleButtonInserir(){
        Cliente novo = new Cliente(textFieldNome.getText(),Date.valueOf(datePickerDataNascimento.getValue()),textFieldCpf.getText());
        olistClientes.add(novo);
    }
    public void handleButtonAlterar(){
        Cliente selecionado = this.getClienteSelecionado();
        if(selecionado != null){
            olistClientes.get(olistClientes.indexOf(selecionado)).setNome(textFieldNome.getText());
            olistClientes.get(olistClientes.indexOf(selecionado)).setCpf(textFieldCpf.getText());
            olistClientes.get(olistClientes.indexOf(selecionado)).setDataNascimento(Date.valueOf(datePickerDataNascimento.getValue()));
            tableViewCliente.refresh();
        }
    }
    public void handleButtonBuscar(){
        
    }
    private Cliente getClienteSelecionado(){
        return tableViewCliente.getSelectionModel().getSelectedItem();

    }
    public void handleButtonRemover(){
        Cliente selecionado = this.getClienteSelecionado();
        if(selecionado != null){
            olistClientes.remove(selecionado);  
        }
                   
    }
    
    private void handleTableViewSelection(Cliente selecionado){
        if(selecionado != null){
            System.out.println(selecionado.toString());
            this.textFieldNome.setText(selecionado.getNome());
            this.textFieldCpf.setText(selecionado.getCpf());
            this.datePickerDataNascimento.setValue(selecionado.getDataNascimento().toLocalDate());
        }

        
    }
    private void iniciarTableView(){
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnClienteDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        
        listClientes.add(new Cliente("aaaa",new Date(2000,05,28),"111.111"));
        listClientes.add(new Cliente("bbbb",new Date(2001,05,28),"222.222"));
        olistClientes = FXCollections.observableArrayList(listClientes);
        tableViewCliente.setItems(olistClientes);
        tableViewCliente.getSelectionModel()  
			.selectedItemProperty()  
			.addListener((observable, oldValue, newValue)  
			->handleTableViewSelection(newValue));  //
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.iniciarTableView();
    }    
    
}
