/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanquin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author stanb
 */
 public class Databank {
    
    private Connection conn;
    private Statement stm;
    private ResultSet rs;
    
    
    
   public Databank(){
   }
   //In createConnection wordt er een connectie gemaakt met de database.
   private Connection createConnection(){
        Connection conn = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String strConnectionString = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(strConnectionString,"SANQUIN","Sanquin");
            System.out.println("createConnection: " + conn);
        } catch(Exception e){
            System.err.println("Error createConnection: " + e.getMessage());
        }
        return conn;
    }
   
   
   public ObservableList GeefFilialen(){
       ObservableList<String> lijst = FXCollections.observableArrayList();
       String strQuery = "SELECT * FROM Filiaal";
     
       System.out.println("Query begonnen");
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                lijst.add(rs.getString("Plaats"));
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
   }
   
   
   public String checkWachtwoord(String Plaats, String wachtwoord){
       String strQuery = "SELECT Wachtwoord FROM Login WHERE FiliaalId = ( SELECT FiliaalId FROM Filiaal WHERE Plaats = '"+Plaats+"')";
       String object = null;
       
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
            object = rs.getString("Wachtwoord").toString();
            }
       }
       catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
            object = null;
            
        }
       return object;
   }
   
   
   public int GetFiliaalId(String i){
       String strQuery = "SELECT FiliaalId FROM Filiaal WHERE Plaats = '"+ i + "'"; 
       System.out.println("Query begonnen");
       int result = -1;
        
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getInt("FiliaalId");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
   
   
   public ObservableList GeefDonoren(int i){
       ObservableList<String> lijst = FXCollections.observableArrayList();
       String strQuery = "SELECT Naam FROM Donor WHERE FiliaalId = "+ i;
     
       System.out.println("Query begonnen");
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                lijst.add(rs.getString("Naam"));
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
   }
   
   
   public String GetFiliaalNaam(int i){
       String strQuery = "SELECT Plaats FROM Filiaal WHERE FiliaalId = '"+ i + "'"; 
       System.out.println("Query begonnen");
       String result = "";
        
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getString("Plaats");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
   
   
   public int getDonorId(String naam){
       String strQuery = "Select DonorId FROM Donor WHERE Naam = '" + naam + "'";
       System.out.println("Query begonnen");
       int result = 0;
       
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getInt("DonorId");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
       
       
   }
   
   
   public String getAdres(int i){
       String strQuery = "SELECT ADRES FROM DONOR WHERE DONORID = " + i;
       System.out.println("Query begonnen");
       String result = "";
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getString("Adres");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
   
   
    public String getWoonplaats(int i){
       String strQuery = "SELECT Woonplaats FROM Donor WHERE DonorId = " + i;
       System.out.println("Query begonnen");
       String result = "";
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getString("Woonplaats");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
    
    
     public String getEmail(int i){
       String strQuery = "SELECT Emailadres FROM Donor WHERE DonorId = " + i;
       System.out.println("Query begonnen");
       String result = "";
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getString("EmailAdres");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
     
     
      public String getBloedtype(int i){
       String strQuery = "SELECT BloedtypeNaam FROM Bloedtype WHERE BloedtypeId = ( SELECT BloedtypeId FROM Donor WHERE DonorId = " + i + ")";
       System.out.println("Query begonnen");
       String result = "";
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getString("BloedtypeNaam");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
  

      
    public int getTelefoon(int i){
       String strQuery = "SELECT Telefoonnummer FROM Donor WHERE DonorId = " + i;
       System.out.println("Query begonnen");
       int result = 0;
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = 0 + rs.getInt("Telefoonnummer");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
    
     public String getDatum(int i){
       String strQuery = "SELECT LaatsteDonatie FROM Donor WHERE DonorId = " + i;
       System.out.println("Query begonnen");
       String result = "";
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getDate("LaatsteDonatie").toString();
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return result;
   }
     
     public void SetDatum(int i){
         String strQuery = "UPDATE Donor SET LaatsteDonatie = (SELECT SYSDATE FROM dual) WHERE DonorId = "+ i;
         
         try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public String getNaam(int i){
       String strQuery = "SELECT Naam FROM Donor WHERE DonorId = " + i;
       System.out.println("Query begonnen");
       String result = "";
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getString("Naam");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
     
     
     public int Check(int t){
         String Query = "SELECT TO_DATE((SELECT SYSDATE FROM dual), 'YYYY-MM-DD') -  \n" +
                        "       TO_DATE((SELECT LaatsteDonatie FROM Donor WHERE DonorId = "+t+ "), 'DD-MM-YYYY') AS DateDiff\n" +
                        "FROM   dual";
         System.out.println("Query begonnen");
         int result = 0;
         
         
         try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(Query);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getInt("DateDiff");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return result;
   }
     
     
       public int Count(int i){
           String Query = "SELECT COUNT(DonorId) AS Aantal FROM SOA_Donor WHERE DonorId = "+ i;
         System.out.println("Query begonnen");
         int result = 0;
         
         
         try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(Query);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getInt("Aantal");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return result;
       }
       
       
       public ObservableList GeefSoa(){
       ObservableList<String> lijst = FXCollections.observableArrayList();
       String strQuery = "SELECT * FROM Soa";
     
       System.out.println("Query begonnen");
       try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(strQuery);  
            System.out.println("Executing Query");
            while(rs.next()){
                lijst.add(rs.getString("Naam"));
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
   }
       
       public int getSoaId(String naam){
           String Query = "SELECT SoaId FROM Soa WHERE Naam = '" + naam + "'";
           System.out.println("Query begonnen");
           
           int result = -1;
           try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(Query);  
            System.out.println("Executing Query");
            while(rs.next()){
                result = rs.getInt("SoaId");
                
            }
     
        } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return result;
       }
       
       public void VoegSoaToe(int id, int dId){
           String Query = "INSERT INTO SOA_Donor (SoaId, DonorId) VALUES ("+ id + ", " + dId + ")";
           
           try {
            Statement stmt = createConnection().createStatement();
            System.out.println("Connectie gelukt");
            rs = stmt.executeQuery(Query);  
            System.out.println("Gelukt");
            } catch (SQLException ex) {
            System.out.println("Niet gelukt");
            Logger.getLogger(Databank.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
 }




