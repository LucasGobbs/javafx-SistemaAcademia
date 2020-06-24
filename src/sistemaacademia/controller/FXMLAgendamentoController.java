/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaacademia.model.Cliente;
import sistemaacademia.model.Treinador;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLAgendamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    // Tableview Treinador ------------------
    @FXML
    private TableView<Treinador> tableViewTreinador;
    @FXML
    private TableColumn<Treinador,String> tableColumnTreinadorNome;
    
    private List<Treinador> listTreinadores = new ArrayList<>();
    private ObservableList<Treinador> olistTreinadores;
    // TablewView Agendamento
    //@FXML
    //private TableView<Agendament
    
    // ComboBox Cliente --------------------------
    @FXML
    private ComboBox<Cliente> comboBoxClientes;
    private List<Cliente> listClientes = new ArrayList<>();
    private ObservableList<Cliente> olistClientes;
    
    //ComboBox Horario
    @FXML
    private ComboBox<String> comboBoxHorario;
    private List<String> listHorario = new ArrayList<>();
    private ObservableList<String> olistHorario;
    
    public void calcularCargaHoraria(Treinador selecionado){
        System.out.println("Quero a carga horaria do "+selecionado.toString());
    }
    public void calcularValor(){
        
    }
    private void carregarComboBoxCliente(){
        listClientes.add(new Cliente(0,"aaaa",new Date(2000,05,28),"111.111"));
        listClientes.add(new Cliente(0,"bbbb",new Date(2001,05,28),"222.222"));
        olistClientes = FXCollections.observableArrayList(listClientes);
      
        comboBoxClientes.setItems(olistClientes);
    }
    private void carregarComboBoxHorario(){
        
        listHorario.add("6:00");
        listHorario.add("7:00");
        listHorario.add("8:00");
        listHorario.add("9:00");
        listHorario.add("10:00");
        listHorario.add("11:00");
        
        listHorario.add("14:00");
        listHorario.add("15:00");
        listHorario.add("16:00");
        listHorario.add("17:00");
        listHorario.add("18:00");
        listHorario.add("19:00");
        listHorario.add("20:00");
        listHorario.add("21:00");
        listHorario.add("22:00");
        
        olistHorario = FXCollections.observableArrayList(listHorario);
        if(olistHorario == null){
            System.out.println("A");
        }
        if(comboBoxHorario == null){
            System.out.println("B");
        }
        comboBoxHorario.setItems(olistHorario);
    }
    private int parseHorario(){
        String teste = "5:30";
        
        System.out.println(teste.split(":")[0]);
        return 1;
    }
    private void iniciarTableViewTreinador(){
        tableColumnTreinadorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        listTreinadores.add(new Treinador(0,"Lucas",new Date(2000,3,20),"111.111",10,100));
        listTreinadores.add(new Treinador(0,"Elian",new Date(1000,3,20),"111.222",10,100));
        olistTreinadores = FXCollections.observableArrayList(listTreinadores);
        
        tableViewTreinador.setItems(olistTreinadores);
        
        tableViewTreinador.getSelectionModel()  
			   .selectedItemProperty()  
			   .addListener((observable, oldValue, newValue)  
			    ->calcularCargaHoraria(newValue));  //
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.iniciarTableViewTreinador();
        this.carregarComboBoxCliente();
        this.carregarComboBoxHorario();
        this.parseHorario();
    }    
    
}
