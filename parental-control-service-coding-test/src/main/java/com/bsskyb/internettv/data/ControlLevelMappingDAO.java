/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsskyb.internettv.data;

import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * This class is intended for demonstrating purpose
 * 
 * @TODO: Move to a database or other external source
 */
public class ControlLevelMappingDAO {
   
   private static ControlLevelMappingDAO instance; 
    
    private ControlLevelMappingDAO(){
    }

    
    public static ControlLevelMappingDAO getInstance(){
        if(instance == null){
            instance = new ControlLevelMappingDAO();
        }
        return instance;
    }
    

    public Map<String, String> getControlLevels() throws TechnicalFailureException{
        
        Map<String, String> moviecontrolLevels = null;
        
        
            if(connectionStatus() != HttpURLConnection.HTTP_OK) {
                
              throw new  TechnicalFailureException();
            }
       

            moviecontrolLevels = new HashMap<>();

            moviecontrolLevels.put("1", ControlLevelStub.P12.getControlLevel());
            moviecontrolLevels.put("2", ControlLevelStub.P15.getControlLevel());
            moviecontrolLevels.put("3", ControlLevelStub.P18.getControlLevel());
            moviecontrolLevels.put("4", ControlLevelStub.PG.getControlLevel());
            moviecontrolLevels.put("5", ControlLevelStub.U.getControlLevel());
        
      
        return moviecontrolLevels;
    }
    
    
    
   private  int connectionStatus() {
    
     return HttpURLConnection.HTTP_OK;
   }
 
}
