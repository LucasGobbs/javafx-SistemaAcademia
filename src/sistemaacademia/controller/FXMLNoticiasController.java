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
    private Text textNoticia;
    
    private NoticiasRunnable threadNoticias;
    private Socket socket;
    private Thread thread1;
    private List<String> listNoticia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            this.connect("35.199.121.110",12345);
           
            this.get_noticias();

            this.start_runnable();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLNoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLNoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    private void connect(String ip, int port) throws IOException{
        this.socket = new Socket(ip,port);
    }
    
    private void get_noticias() throws IOException, ClassNotFoundException{
        ObjectInputStream objeto = new ObjectInputStream(socket.getInputStream());
        listNoticia = (List<String>) objeto.readObject();
    }
    
    private void start_runnable(){
        threadNoticias = new NoticiasRunnable(textNoticia,listNoticia);
        thread1 = new Thread(threadNoticias, "Thread de Noticias");
        thread1.start();
    }
}
