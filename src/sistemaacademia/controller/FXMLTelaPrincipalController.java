/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.awt.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private MenuItem menuItemCadastroCliente;
    
    @FXML
    private AnchorPane anchorPane;
    
    public void handleMenuItemCadastroCliente() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/sistemaacademia/view/FXMLCadastroCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemAgendamento() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/sistemaacademia/view/FXMLAgendamento.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemGraficos() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/sistemaacademia/view/FXMLGraficos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemRelatorios() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/sistemaacademia/view/FXMLRelatorios.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleNoticias() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/sistemaacademia/view/FXMLNoticias.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleWebServices() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/sistemaacademia/view/FXMLWebServices.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
