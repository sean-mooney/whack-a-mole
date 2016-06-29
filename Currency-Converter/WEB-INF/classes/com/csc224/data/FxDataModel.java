/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc224.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sean
 */
public class FxDataModel {
   
   public static final String prpFileName = "fx.prp.file.name";
   public static final String ratesFileName = "fx.rates.file.name";
   public static final String delimiterKey = "fx.rates.file.dlmtr";
   public static final String fxDataKey = "fxDataModel";
   public static final String returnJSP = "login.jsp";
   
   private Properties fxProps;
   private Connection fxConnection;
   private Hashtable< String, Double > fxRates = new Hashtable< String, Double >();
   private String[] fxCurrencies;
   private HashSet< String > dbFxCurrencies = new HashSet< String >();
   private static final String dbQuery = "db.query";

   
   public double getFxRate(final String inCurr, final String outCurr){
        return fxRates.get(inCurr+outCurr);
    }
   
   
   public String[] getCurrencies(){
       return fxCurrencies;
   }
   
   public Properties getFxProps(){      
       return fxProps;
   }

  public FxDataModel (Properties fxProps, Connection connection){
      BufferedReader br = null;
      this.fxProps = fxProps;
      this.fxConnection = connection;
      
      try {
           PreparedStatement preparedStatement = fxConnection.prepareStatement(fxProps.getProperty(dbQuery));
           ResultSet resultSet = preparedStatement.executeQuery();
           
           while(resultSet.next()) //Handles rows
           {
               dbFxCurrencies.add(resultSet.getString(1)); 
               fxRates.put(resultSet.getString(1)+resultSet.getString(2), Double.parseDouble(resultSet.getString(3)));
            }  
           fxCurrencies = dbFxCurrencies.toArray(new String[dbFxCurrencies.size()]);
           
          
       } catch (SQLException ex) {
           Logger.getLogger(FxDataModel.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               if(br != null){
               br.close();
               }
          } catch (IOException ex) {
               Logger.getLogger(FxDataModel.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }
}
