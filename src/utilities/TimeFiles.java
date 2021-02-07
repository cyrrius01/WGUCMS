/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Keith A Graham
 */
public class TimeFiles {
    
    
    // method requires a timestamp as input
    public static String toLocalTime(Timestamp apptTime) {
            
        ZoneId zidApptTime = ZoneId.systemDefault();
        ZonedDateTime newZDTApptTime = apptTime.toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime convertedApptTime = newZDTApptTime.withZoneSameInstant(zidApptTime);
        
        String finalTime = convertedApptTime.toInstant().atZone(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("h:mm a"));

        return finalTime;
    }
    
    public static String toLocalDate(Timestamp apptDate) {
        
        LocalDateTime newApptDate = apptDate.toLocalDateTime();
        String finalDate = newApptDate.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));
        
        return finalDate;
    }
}
