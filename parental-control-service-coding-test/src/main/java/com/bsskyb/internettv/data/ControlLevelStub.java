
package com.bsskyb.internettv.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;



/**
 * This class is intended for demonstrating purpose
 * 
 * @TODO: Move to a database or other external source
 */
@RequiredArgsConstructor
@Getter
public enum ControlLevelStub {
     U("0"), PG("1") , P12("2"), P15("3"), P18("4");
     
     private final String controlLevel;
}
