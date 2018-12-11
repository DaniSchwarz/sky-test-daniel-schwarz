
package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;
import com.bsskyb.internettv.data.ControlLevelMappingDAO;
import com.bsskyb.internettv.data.ControlLevelStub;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class MovieServiceImpl implements MovieService  {

    
    
    @Override
    public String getParentalControlLevel(String titleId) throws TitleNotFoundException, TechnicalFailureException {

        String controlLevel = ControlLevelMappingDAO.getInstance().getControlLevels().get(titleId);

        if(controlLevel == null) {
            throw new TitleNotFoundException();
        } else {
            return controlLevel;
        }
             
    }
    
  
}
