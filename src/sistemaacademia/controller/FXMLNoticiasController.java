/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sistemaacademia.NoticiasRunnable;

/**
 * FXML Controller class
 *
 * @author llcos_000
 */
public class FXMLNoticiasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Text textNoticia;
    
    NoticiasRunnable minhaThread1;

    Thread t1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textNoticia.setText("Teste TESte");
        try {
            Socket socket = new Socket("35.199.121.110",12345);
            //DataInputStream mensagem = new DataInputStream(socket.getInputStream());
            ObjectInputStream objeto = new ObjectInputStream(socket.getInputStream());
            List<String> noticias = (List<String>) objeto.readObject();
            for(String nt: noticias){
                System.out.println(nt);
            }
            
            minhaThread1 = new NoticiasRunnable(textNoticia,noticias);
            t1 = new Thread(minhaThread1, "Thread 1");
            t1.start();
        } catch (IOException ex) {
            Logger.getLogger(FXMLNoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLNoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
