/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanquin;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author stanb
 */
public class Donor {
    private Databank db;
    private ImageView iView;
    private Image image;
    
    private int DonorId;
    private String DonorNaam;
    
    private String Adres = "";
    private String Woonplaats = "";
    private String Email = "";
    private int Telefoon = 1;
    private String bloeddonatie;
    private String Bloedtype = "";
    
    
    
    public Donor(int i, String j){
        db = new Databank();
        this.DonorId = i;
        this.DonorNaam = j;
        
        //Waardes voor de attributen van de klassen instantiëren door middel van DonorId(i)
        setAdres(i);
        setWoonplaats(i);
        setEmail(i);
        setTelefoon(i);
        setBloedtype(i);
        
        //layout instantiëren
        BorderPane bPane = new BorderPane();
        image = new Image("/Other Sources/Sanquin-logo-overview.jpg");
        iView = new ImageView(image);
        bPane.setTop(iView);
        bPane.setAlignment(iView, Pos.CENTER);
        
        //sublayout 1 instantiëren met NAW + mail, telefoon en bloedtype gegevens van de Donor.
        VBox pane2 = new VBox();
        Label naam = new Label();
        Label adres = new Label();
        Label woonplaats = new Label();
        Label email = new Label();
        Label telefoon = new Label();
        Label bloedtype = new Label();
        
        //attributen meegeven aan de layout
        pane2.getChildren().add(naam);
        pane2.getChildren().add(adres);
        pane2.getChildren().add(woonplaats);
        pane2.getChildren().add(email);
        pane2.getChildren().add(telefoon);
        pane2.getChildren().add(bloedtype);
        //sublayout toevoegen aan hoofdlayout
        bPane.setLeft(pane2);
        
        //waardes toevoegen aan de attributen
        naam.setText(this.DonorNaam);
        adres.setText(this.Adres);
        woonplaats.setText(this.Woonplaats);
        email.setText(this.Email);
        telefoon.setText(String.valueOf(this.Telefoon));
        bloedtype.setText(this.Bloedtype);
        
        //sublayout 2
        VBox pane3 = new VBox();
        Label text = new Label("Laatste bloeddonatie:");
        Label text2 = new Label(String.valueOf(getBloeddonatie()));
        Button updateDatum = new Button("Update Donatie Datum");
        Label text3 = new Label();
        
        //Controleren of het DonorId in de geregistreerde tabel van SOA_Donor voorkomt
        int h = db.Count(this.DonorId);
        updateDatum.setOnAction(event ->{
            //controleren of het data-verschil 73 dagen is
            int g = db.Check(i);
            
           //als data-verschil meer dan 73 is en het donorId niet voorkomt in SOA_Donor, dan de nieuwe datum in de tabel zetten
            if(g >= 73 && h == 0){
                db.SetDatum(i);
            }
            else{
                text3.setText("Om de 73 dagen kan er worden gedoneerd, wacht nog "+ (73-g) + "dagen, of deze persoon heeft een SOA.");
            }
        });
        
        //attributen toevoegen aan sublayout
        pane3.getChildren().add(text);
        pane3.getChildren().add(text2);
        pane3.getChildren().add(updateDatum);
        pane3.setAlignment(Pos.CENTER);
        
        //sublayout aan hoofdlayout toevoegen
        bPane.setCenter(pane3);
        
        //sublayout 3
        VBox fpane = new VBox();
        Label label = new Label();
       
        //Als donorId in SOA_Donor voorkomt, deze waarde aan label meegeven
        if(h >= 1){
            label.setText("Deze persoon heeft één of meerdere soa's. Wegens privacy redenen wordt er niet getoond welke.");
        }
        else{
            label.setText("Deze persoon is vrij van SOA's");
        }
        
        ComboBox cb = new ComboBox();
        Button hi = new Button("Voeg Soa Toe");
        //Lijst van Soa's toevoegen aan combobox
        cb.setItems(db.GeefSoa());
        
        hi.setOnAction(event -> {
            //waarde van combobox in string m zetten
        String m = (cb.getValue().toString());
        //soa id opvragen aan de hand van de naam vanuit string m
        int k = db.getSoaId(m);
        //Soa id en donorid gebruiken om deze in een gezamelijke tabel te zetten.
        db.VoegSoaToe(k, i);
        
        });
        
        //attribuut toevoegen aan sublayout
        fpane.getChildren().add(label);
        fpane.getChildren().add(cb);
        fpane.getChildren().add(hi);
        //sublayout toevoegen aan hoofdlayout
        bPane.setRight(fpane);
        bPane.setAlignment(fpane, Pos.CENTER);
        
        
        
        
        
        Stage bStage = new Stage();
        
        Scene bScene = new Scene(bPane, 800, 600);
        bStage.setScene(bScene);
        bStage.setTitle(this.DonorNaam);
        bStage.show();
    }
    
    
    
    

    public String getAdres() {
        return this.Adres;
    }

    public void setAdres(int donorid) {
        String adres = db.getAdres(donorid);
        this.Adres = adres;
    }

    public String getWoonplaats() {
        return this.Woonplaats;
    }

    public void setWoonplaats(int donorid) {
        String woonplaats = db.getWoonplaats(donorid);
        this.Woonplaats = woonplaats;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(int donorid) {
        String email = db.getEmail(donorid);
        this.Email = email;
    }

    public int getTelefoon() {
        return Telefoon;
    }

    public void setTelefoon(int donorid) {
        int telefoon = db.getTelefoon(donorid);
        this.Telefoon = telefoon;
    }

    public String getBloeddonatie() {
        return bloeddonatie;
    }

    public void setBloeddonatie(int donorid) {
        this.bloeddonatie = db.getDatum(donorid);
    }

    public String getBloedtype() {
        return Bloedtype;
    }

    public void setBloedtype(int donorid) {
        String bloedtype = db.getBloedtype(donorid);
        this.Bloedtype = bloedtype;
    }

    public String getDonorNaam() {
        return DonorNaam;
    }

    public void setDonorNaam(int donorid) {
        String naam = db.getNaam(donorid);
        this.DonorNaam = naam;
    }
    
}
