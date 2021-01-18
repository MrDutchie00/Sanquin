/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanquin;


import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author stanb
 */
public class LoginUI extends GridPane{
    private final Databank db;
    private final ImageView iView;
    private final Image image;
    private Filiaal filiaal;
  
    
    public LoginUI(){
    
     db = new Databank();
    
     //Box met de filialen
     ComboBox cb = new ComboBox();
     //ophalen filialen
     cb.setItems(db.GeefFilialen());
     TextField wachtwoord = new TextField("Wachtwoord");
     Button Login = new Button("Login");
     image = new Image("/Other Sources/Sanquin-logo-overview.jpg");
     iView = new ImageView(image);
     Login.setOnAction((ActionEvent event) -> {
         
         //De naam van filiaal wordt opgeslagen in string i
         String i = (cb.getValue().toString());
         //Het geschreven wachtwoord wordt opgeslagen in string k
         String k = wachtwoord.getText();
         //Hier wordt string i en k meegestuurd om het wachtwoord uit de database op te vragen dat bij filiaal uit string i hoort.
         //Dit wachtwoord wordt opgeslagen in String j
         String j = db.checkWachtwoord(i, k);
         System.out.println(i);
         
        
         //als String j overeenkomstig is met string k, ga dan door
         if(j.equals(k)){
             System.out.println("U bent ingelogd");
             //Haal filiaalId op aan de hand van de naam van het Filiaal
             int d = db.GetFiliaalId(i);
             //voor de zekerheid aan de hand van het filiaalid de naam van het filiaal opvragen en meesturen naar het Filiaal scherm.
             String e = db.GetFiliaalNaam(d);
             filiaal = new Filiaal(d, e);
            
             
             
             
         }
         else{
             //Wachtwoord verkeerd?
          wachtwoord.setText("Verkeerd wachtwoord");   
         }
         
         
         
        
    });
     
     
     this.setVgap(5);
     this.setHgap(5);
     this.add(iView, 0, 0);
     this.add(cb, 0, 1);
     this.add(wachtwoord, 0, 2);
     this.add(Login, 0, 3);
     this.setAlignment(Pos.CENTER);
     
     
     
     
    }

    
 
}
