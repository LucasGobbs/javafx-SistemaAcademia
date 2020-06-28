/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaacademia.dao.AgendamentoDAO;
import sistemaacademia.dao.ClienteDAO;
import sistemaacademia.dao.TreinadorDAO;
import sistemaacademia.database.Database;
import sistemaacademia.database.DatabaseFactory;
import sistemaacademia.model.Agendamento;
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
    // Tableview agendamento
    @FXML
    private TableView<Agendamento> tableViewAgendamento;
    @FXML
    private TableColumn<Agendamento,String> tableViewAgendamentoCliente;
    @FXML
    private TableColumn<Agendamento,String> tableViewAgendamentoTreinador;
    @FXML
    private TableColumn<Agendamento, Date> tableViewAgendamentoData;
    @FXML
    private TableColumn<Agendamento, Time> tableViewAgendamentoTime;
    private List<Agendamento> listAgendamento = new ArrayList<>();
    private ObservableList<Agendamento> olistAgendamento;
    
    
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
    
    // Textfields
    @FXML
    private TextField textFieldCargaHoraria;
    @FXML
    private TextField textFieldValor;
    
    @FXML
    private DatePicker datePickerDataInicio;
    
    private Treinador treinadorSelecionado;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final TreinadorDAO treinadorDAO = new TreinadorDAO();
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    
    public void handleButtonCalcular(){
        int cargaHoraria = treinadorDAO.getCargaHoraria(treinadorSelecionado);
        calcularValor();
    }
    public void calcularCargaHoraria(Treinador selecionado){
        treinadorSelecionado = selecionado;
        int cargaHoraria = treinadorDAO.getCargaHoraria(selecionado);
        textFieldCargaHoraria.setText(String.format("%d horas",cargaHoraria));
        textFieldValor.setText("");
    }
    public void calcularValor(){
        float valor = treinadorDAO.getValorPorMes(treinadorSelecionado);
        textFieldValor.setText(String.format("%.2f reais",valor));
    }
    private void carregarComboBoxCliente(){
        listClientes = clienteDAO.listar();
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
       
        comboBoxHorario.setItems(olistHorario);
    }
    private int parseHorario(){
        String horario = comboBoxHorario.getSelectionModel().getSelectedItem();
        return Integer.parseInt(horario.split(":")[0]);
    }
    public void handleButtonInserirAgendamento() throws Exception { /// MUDAR PARA SQLException +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Cliente clienteSelecionado = comboBoxClientes.getSelectionModel().getSelectedItem();
        Date dataInicio = Date.valueOf(datePickerDataInicio.getValue());
        Time horario = new Time(parseHorario(),0,0);
        handleButtonCalcular();
        float valor = treinadorDAO.getValorPorMes(treinadorSelecionado);
        System.out.println("Alou");
        if(treinadorDAO.getCargaHoraria(treinadorSelecionado) > 0){
            if(!treinadorDAO.estaDisponivel(treinadorSelecionado, dataInicio, horario)){
                Agendamento agendamento = new Agendamento(0,dataInicio,horario,treinadorSelecionado,clienteSelecionado,valor);
                System.out.println(valor);
                System.out.println(agendamento);
                System.out.println(agendamentoDAO.inserir(agendamento));
                    
               
               
            }else{
                //botar erro de disponibilidade aki
                System.out.println("Erro de disponibilidade");
            }
        } else {
            //Botar erro de carga horaria aki
            System.out.println("Erro de carga horaria");
        }
        
        calcularCargaHoraria(treinadorSelecionado);
  
        
        this.refreshTableViewAgendamento();
    }
    private void refreshTableViewAgendamento(){
        listAgendamento = agendamentoDAO.listar();
        olistAgendamento = FXCollections.observableArrayList(listAgendamento);
        tableViewAgendamento.setItems(olistAgendamento);
        tableViewAgendamento.refresh();
    }
    private void iniciarTableViewTreinador(){
        tableColumnTreinadorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        listTreinadores = treinadorDAO.listar();
        olistTreinadores = FXCollections.observableArrayList(listTreinadores);
        
        tableViewTreinador.setItems(olistTreinadores);
        
        tableViewTreinador.getSelectionModel()  
			   .selectedItemProperty()  
			   .addListener((observable, oldValue, newValue)  
			    ->calcularCargaHoraria(newValue));  //
    }
    private void iniciarTableViewAgendamento(){
        tableViewAgendamentoCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        tableViewAgendamentoTreinador.setCellValueFactory(new PropertyValueFactory<>("treinador"));

        tableViewAgendamentoData.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));

        tableViewAgendamentoTime.setCellValueFactory(new PropertyValueFactory<>("horario"));
        
        this.refreshTableViewAgendamento();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clienteDAO.setConnection(connection);
        treinadorDAO.setConnection(connection);
        agendamentoDAO.setConnection(connection);
        
        this.iniciarTableViewTreinador();
        this.iniciarTableViewAgendamento();
        this.carregarComboBoxCliente();
        this.carregarComboBoxHorario();
        //this.parseHorario();
    }    
    
}
