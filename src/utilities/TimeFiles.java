/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Keith A Graham
 */
public class TimeFiles {
    
    
    // method requires a timestamp as input
    public static String toLocal(Timestamp apptTime) {
            
        ZoneId zidApptTime = ZoneId.systemDefault();
        ZonedDateTime newZDTApptTime = apptTime.toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime convertedApptTime = newZDTApptTime.withZoneSameInstant(zidApptTime);
        
        String finalTime = convertedApptTime.toInstant().atZone(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("h:mm a"));

        return finalTime;
    }
}
