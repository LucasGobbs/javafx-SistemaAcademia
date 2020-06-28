/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import sistemaacademia.dao.AgendamentoDAO;
import sistemaacademia.database.Database;
import sistemaacademia.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLGraficosController implements Initializable {
        
    
    
    private           List<String> listAnos = new ArrayList<>();
    private ObservableList<String> olistAnos;
    @FXML
    private ComboBox<String> comboBoxAnos;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private NumberAxis numberAxis;

    private ObservableList<String> observableListMeses = FXCollections.observableArrayList();
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    public void initComboBoxAnos(){
        listAnos.add("2020");
        listAnos.add("2021");
        listAnos.add("2022");
        
        olistAnos = FXCollections.observableArrayList(listAnos);
        comboBoxAnos.setValue("2020");
        comboBoxAnos.setItems(olistAnos);
        comboBoxAnos.getSelectionModel()  
			   .selectedItemProperty()  
			   .addListener((observable, oldValue, newValue)  
			    ->{barChart.getData().clear();refreshChart();});  //
    }
    public void initChart(){
        String[] arrayMeses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul","Ago", "Set", "Out", "Nov", "Dez"};
         // Converte o array em uma lista e adiciona em nossa ObservableList de meses.
         observableListMeses.addAll(Arrays.asList(arrayMeses));
         // Associa os nomes de mês como categorias para o eixo horizontal.
         categoryAxis.setCategories(observableListMeses);
         
         this.refreshChart();
    }
    public void refreshChart(){
        
        String ano = comboBoxAnos.getSelectionModel().getSelectedItem();
        if(ano == null){
            ano = "2020";
        }
        Map<String, ArrayList> dados = agendamentoDAO.listarQuantidadeDeAgendamentosPorTreinador(ano);

        for (Map.Entry<String, ArrayList> dadosItem : dados.entrySet()) {
           XYChart.Series<String, Integer> series = new XYChart.Series<>();
           series.setName(dadosItem.getKey());
           for (int i = 0; i < dadosItem.getValue().size(); i = i + 2) {
               String mes;
               Integer quantidade;
               mes = retornaNomeMes((int) dadosItem.getValue().get(i));
               quantidade = (Integer) dadosItem.getValue().get(i + 1);
               series.getData().add(new XYChart.Data<>(mes, quantidade));
           }

           barChart.getData().add(series);
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agendamentoDAO.setConnection(connection);
        this.initComboBoxAnos();
        this.initChart();
        

    }    
    public String retornaNomeMes(int mes) {
            switch (mes) {
                case 1:
                    return "Jan";
                case 2:
                    return "Fev";
                case 3:
                    return "Mar";
                case 4:
                    return "Abr";
                case 5:
                    return "Mai";
                case 6:
                    return "Jun";
                case 7:
                    return "Jul";
                case 8:
                    return "Ago";
                case 9:
                    return "Set";
                case 10:
                    return "Out";
                case 11:
                    return "Nov";
                case 12:
                    return "Dez";
                default:
                    return "";
        }
 }

}
