/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;
import com.bsskyb.internettv.data.ControlLevelStub;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParentalControlServiceImpl implements ParentalControlService {
    
    
    private MovieService movieService;

    @Override
    public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception {
        String controlLevel = null;
        ControlLevelStub customerControlLevel = null;
       
        
        try {
           controlLevel =  movieService.getParentalControlLevel(movieId);
           customerControlLevel = ControlLevelStub.valueOf(customerParentalControlLevel);
          
       
        } catch (TechnicalFailureException ex) {
             throw new Exception("System error. The movie cant be viewed."); 
        } catch (IllegalArgumentException  ex) {
             throw new Exception("System error. The movie cant be viewed."); 

        } catch (TitleNotFoundException ex) {
           throw new Exception("The movie service could not find the given movie"); 
           
        }   
      
       return  customerControlLevel.getControlLevel().compareTo(controlLevel) >= 0;
      
    }
    
}
