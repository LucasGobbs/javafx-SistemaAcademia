/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sistemaacademia.dao.AgendamentoDAO;
import sistemaacademia.database.Database;
import sistemaacademia.database.DatabaseFactory;
import sistemaacademia.model.Agendamento;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLRelatoriosController implements Initializable {
    //id, data_inicio, horario, treinador, cliente, valor
    @FXML
    private TableView<Agendamento> tableViewAgendamento;
    @FXML
    private TableColumn<Agendamento,Integer> tableViewAgendamentoId;
    @FXML
    private TableColumn<Agendamento, Date> tableViewAgendamentoData;
    @FXML
    private TableColumn<Agendamento, Time> tableViewAgendamentoHorario;
    @FXML
    private TableColumn<Agendamento,String> tableViewAgendamentoTreinador;
    @FXML
    private TableColumn<Agendamento,String> tableViewAgendamentoCliente;
    @FXML
    private TableColumn<Agendamento, Float> tableViewAgendamentoValor;
    
    private List<Agendamento> listAgendamento = new ArrayList<>();
    private ObservableList<Agendamento> olistAgendamento;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    public void initTableViewAgendamento(){
        tableViewAgendamentoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableViewAgendamentoData.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        tableViewAgendamentoHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        tableViewAgendamentoTreinador.setCellValueFactory(new PropertyValueFactory<>("treinador"));
        tableViewAgendamentoCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableViewAgendamentoValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        listAgendamento = agendamentoDAO.listar();
        olistAgendamento = FXCollections.observableArrayList(listAgendamento);
        tableViewAgendamento.setItems(olistAgendamento);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agendamentoDAO.setConnection(connection);
        this.initTableViewAgendamento();
    }    
    public void handleImprimir()throws JRException{
        URL url = getClass().getResource("/sistemaacademia/relatorios/sistemaAcademiaRelatorioAgendamentos.jasper");
        JasperReport report = (JasperReport) JRLoader.loadObject(url);
        JasperPrint print = JasperFillManager.fillReport(report, null, connection);
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
    }
    
}
