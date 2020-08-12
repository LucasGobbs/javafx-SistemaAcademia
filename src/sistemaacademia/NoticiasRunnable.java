/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaacademia;

/**
 *
 * @author llcos_000
 */
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class NoticiasRunnable implements Runnable {

    Text text;
    List<String> noticias;
    
    
    // Fazer as noticias ficarem repetindo 3 vezes na tela
    int repetir_x = 3;
    int repeticoes = 0;
    
    
    public NoticiasRunnable(Text j, List<String> noticias) {
        text = j;
        this.noticias = noticias;
    }

    @Override
    public void run() {
        while(repeticoes < repetir_x){
           
            for(String noticia: this.noticias){
                Platform.runLater(() -> text.setText(noticia));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NoticiasRunnable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            repeticoes++;
        }
        
    }

}


