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
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    @FXML
    private TableColumn<Agendamento, Float> tableViewAgendamentoValor;
    
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
    
    public void handleButtonInserirAgendamento() throws Exception { /// MUDAR PARA SQLException +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if(!this.avaliarInput()){
            return;
        }
        Cliente clienteSelecionado = comboBoxClientes.getSelectionModel().getSelectedItem();
       
        Date dataMarcada = Date.valueOf(datePickerDataInicio.getValue());
        
        int cargaHorariaDisponivel = this.calcularCargaHorariaNoMes(dataMarcada);
        float valor = this.calcularValorNoMes(dataMarcada);
        Time horario = new Time(parseHorario(),0,0);
        
        
        if(cargaHorariaDisponivel > 0){
            boolean treinadorEstaDisponivel = treinadorDAO.estaDisponivel(treinadorSelecionado, dataMarcada, horario);
            System.out.println("Esta disponivel" + treinadorEstaDisponivel);
            
            if(!treinadorEstaDisponivel){
                Agendamento agendamento = new Agendamento(0,
                                                          dataMarcada,
                                                          horario,
                                                          treinadorSelecionado,
                                                          clienteSelecionado,
                                                          valor);
                if(!agendamentoDAO.inserir(agendamento)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro na inserção");
                    alert.setHeaderText("Houve um erro na inserção do agendamento");
                    alert.setContentText("por favor tente novamente");
                    alert.show();
                } 
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de disponibilidade");
                alert.setHeaderText("Treinador não está disponivel neste dia e hora");
                alert.setContentText("Selecione outro dia ou hora ou mude de treinador");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de carga horaria");
            alert.setHeaderText("Treinador não tem carga horaria para este mes");
            alert.setContentText("Selecione outro dia ou hora ou mude de treinador");
            alert.show();
        }
        
        //calcularCargaHoraria(treinadorSelecionado);
  
        this.handleButtonCalcular();
        this.refreshTableViewAgendamento();
    }
    public boolean avaliarInput(){
        String errorMessage = "";

        if (comboBoxClientes.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cliente inválido!\n";
        }
        if (comboBoxHorario.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Horario inválido!\n";
        }
        if (datePickerDataInicio.getValue() == null) {
            errorMessage += "Data inválida!\n";
        }
        if (treinadorSelecionado == null){
            errorMessage += "treinador inválido!\n";
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
    public void handleButtonCalcular(){
        if(!this.avaliarInput()){
            return;
        }
        Date dataMarcada = Date.valueOf(datePickerDataInicio.getValue());
                
        int cargaHoraria = this.calcularCargaHorariaNoMes(dataMarcada);
        textFieldCargaHoraria.setText(String.format("%d horas",cargaHoraria));
        
        float valor = calcularValorNoMes(dataMarcada);
        textFieldValor.setText(String.format("%.2f reais",valor));
    }
    public float calcularValorNoMes(Date dataMarcada){
        LocalDate dm = dataMarcada.toLocalDate();
        Month mes = dm.getMonth();
        int ano = dm.getYear();
        int inicioMes = 1;
        int finalMes = mes.length(this.isLeapYear(ano)); // Depende do tamanho do mes e se o ano é bissexto
        LocalDate inicioMesDate = LocalDate.of(ano, mes.getValue(), inicioMes);
        LocalDate finalMesDate = LocalDate.of(ano, mes.getValue(), finalMes);
        
        float valor = treinadorDAO.getValorPorMes(treinadorSelecionado,
                                                  Date.valueOf(inicioMesDate),
                                                  Date.valueOf(finalMesDate));
        return valor;
    }
    public int calcularCargaHorariaNoMes(Date dataMarcada){
        LocalDate dm = dataMarcada.toLocalDate();
        Month mes = dm.getMonth();
        int ano = dm.getYear();
        int inicioMes = 1;
        int finalMes = mes.length(this.isLeapYear(ano)); // Depende do tamanho do mes e se o ano é bissexto
        LocalDate inicioMesDate = LocalDate.of(ano, mes.getValue(), inicioMes);
        LocalDate finalMesDate = LocalDate.of(ano, mes.getValue(), finalMes);
        
        int cargaHoraria = treinadorDAO.getCargaHoraria(treinadorSelecionado, 
                                                        Date.valueOf(inicioMesDate),
                                                        Date.valueOf(finalMesDate));
        return cargaHoraria;
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
    public boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
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
			    ->{treinadorSelecionado = newValue;});  //
    }
    private void iniciarTableViewAgendamento(){
        tableViewAgendamentoCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        tableViewAgendamentoTreinador.setCellValueFactory(new PropertyValueFactory<>("treinador"));

        tableViewAgendamentoData.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));

        tableViewAgendamentoTime.setCellValueFactory(new PropertyValueFactory<>("horario"));
        
        tableViewAgendamentoValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
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
    }    
    
}
