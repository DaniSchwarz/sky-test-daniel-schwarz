/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.junit.SimpleScenarioTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith( DataProviderRunner.class)
public class ParentalControlServiceImplIT  extends SimpleScenarioTest<ParentalControlServiceImplIT.ContolServiceSteps>{
    
 
  
    @Test
    @DataProvider( {
            "U, 5",
            "PG, 5",
            "P18, 5",
            "P18, 3"
    } )
    public void testWatchMovieAllowed(String controlLevel, String movieId) throws Exception {
        
       given().a_movie_service_instance()
        .and().a_parental_control_service_instance();
       
       when().i_request_a_movie_$_with_control_level_$(controlLevel, movieId);
       then().watch_movie_allowed();
       
    }
    
    
     @Test
     @DataProvider( {
            "U, 4",
            "PG, 2",
            "U, 3"
    } )
    public void testWatchMovieNotAllowed(String controlLevel, String movieId) throws Exception {
        
       given().a_movie_service_instance()
        .and().a_parental_control_service_instance();
       
       when().i_request_a_movie_$_with_control_level_$(controlLevel, movieId);
       then().watch_movie_not_allowed();
       
    }
    
    
    @Test
    public void testWatchMovieDoesntExist() throws Exception {
        
       given().a_movie_service_instance()
        .and().a_parental_control_service_instance();
       
       when().i_request_a_movie_$_with_control_level_$("Robocop", "15");
       then().i_see_error_message_title_not_found();
       
    }

     static class ContolServiceSteps extends Stage<ContolServiceSteps> {

         MovieService movieService;
         ParentalControlService parentalControlService;
         Boolean isAllowed;
         
         @ProvidedScenarioState
         Exception exception;
         
         
        public ContolServiceSteps  a_movie_service_instance() {
           movieService = new MovieServiceImpl();
           return this;
        }
        
    
        public ContolServiceSteps a_parental_control_service_instance() {
            parentalControlService = new ParentalControlServiceImpl(movieService);
            return this;
        }

        public ContolServiceSteps i_request_a_movie_$_with_control_level_$(String controlLevel, String movieId ) {
          try {
              isAllowed = parentalControlService.canWatchMovie(controlLevel, movieId);
          } catch (Exception e) {
                exception = e;
          }
            return self();
        }

        public void watch_movie_allowed() {
            assertThat(isAllowed).isTrue();
        }

        public void watch_movie_not_allowed() {
            assertThat(isAllowed).isFalse();        
        } 

        public void i_see_error_message_title_not_found() {
             assertThat( exception ).hasMessageContaining("The movie service could not find the given movie");
        }
        
        
     
    }
    
}
