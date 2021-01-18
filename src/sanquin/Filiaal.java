/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanquin;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author stanb
 */
public class Filiaal {
    private final Databank db;
    private final ImageView iView;
    private final Image image;
    public int filiaalId;
    public String filiaalNaam;
    private Donor donor;
    
    public Filiaal(int id, String naam){
        this.filiaalId = id;
        this.filiaalNaam = naam;
        VBox fpane = new VBox();
        Stage deStage = new Stage();
        db = new Databank();
        image = new Image("/Other Sources/Sanquin-logo-overview.jpg");
        iView = new ImageView(image);
        Label label = new Label("Selecteer Patiënt");
        ComboBox cb = new ComboBox();
        Button checkDonor = new Button("Bekijk gegevens Donor");
        //Lijst invullen met patienten van het filiaal waar je op bent ingelogd.
        cb.setItems(db.GeefDonoren(filiaalId));
        
        checkDonor.setOnAction(event ->{
            //Waarde van de gekozen optie in String opslaan
            String i = cb.getValue().toString();
            //Donor Id van String i opvragen
            int d = db.getDonorId(i);
            //Naam van donor aan de hand van d opvragen
            String e = db.getNaam(d);
            //waardes meesturen naar het donor informatiescherm
            donor = new Donor(d, e);
            
        });
        
        
        
        fpane.getChildren().add(iView);
        fpane.getChildren().add(cb);
        fpane.getChildren().add(checkDonor);
        fpane.setAlignment(Pos.CENTER);
        
        
        
        Scene deScene = new Scene(fpane, 800, 600);
        
        deStage.setScene(deScene);
        deStage.setTitle(this.filiaalNaam);
        deStage.show();
       
    }

    public int getFiliaalId() {
        return filiaalId;
    }

    public void setFiliaalId(int filiaalID) {
        this.filiaalId = filiaalID;
    }

    public String getFiliaalNaam() {
        return filiaalNaam;
    }

    public void setFiliaalNaam(String Plaats) {
        this.filiaalNaam = Plaats;
    }
    
    
    
    
    
    
    
}
