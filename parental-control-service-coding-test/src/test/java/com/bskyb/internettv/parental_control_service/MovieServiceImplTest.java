/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.TitleNotFoundException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class MovieServiceImplTest {
    
 
    @Test
    public void testGetValidParentalControlLevel() throws Exception {
       String controlLevel = new MovieServiceImpl().getParentalControlLevel("1");
       
      assertThat(controlLevel).isEqualTo("2");
    }
    

    
    
    
    @Test
    public void testGetParentalControlLevelException() throws Exception {
       
        
       assertThatExceptionOfType(TitleNotFoundException.class).isThrownBy(() -> { 
           new MovieServiceImpl().getParentalControlLevel("random");
       
       });
    }
    
      @Test
    public void testGetParentalControlLevelCallingWithNull() throws Exception {
       
        
       assertThatExceptionOfType(TitleNotFoundException.class).isThrownBy(() -> { 
           new MovieServiceImpl().getParentalControlLevel(null);
       
       });
    }
}
