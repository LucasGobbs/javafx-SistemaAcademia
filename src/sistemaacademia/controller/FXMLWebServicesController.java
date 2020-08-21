/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.codehaus.jackson.map.ObjectMapper;
import sistemaacademia.NoticiasRunnable;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLWebServicesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label textNoticia;
    
    private List<String> listNoticia;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String noticiasJson = getNoticiasJson();
        
        try {
            listNoticia = parseNoticiasJson(noticiasJson);
        } catch (IOException ex) {
            Logger.getLogger(FXMLWebServicesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(String noticia:listNoticia ){
            if(textNoticia.getText() == null){
                textNoticia.setText(noticia);
            } else{
                textNoticia.setText(textNoticia.getText() +"\n"+ noticia);
            }

        }

    }    
    private String getNoticiasJson() {
        Client client = ClientBuilder.newClient();
     
        String path = "https://ifes-poo2-trabalho5.herokuapp.com/noticias";

        WebTarget target = client.target(path);
        return target.request().get(String.class);
    }
    private List<String> parseNoticiasJson(String noticiasJson) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        List<String> noticias = mapper.readValue(noticiasJson, List.class);
        return noticias;
    }
    
}
