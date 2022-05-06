/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Model;

import java.util.Date;

//import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 *
 * @author PC-Yassine
 */
public class DateConverter {
    
    
    public static java.sql.Date dateConverter(java.util.Date d) throws ParseException{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String currentTime = sdf.format(d);
            Date myDate = formatter.parse(currentTime);
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        return sqlDate;    
    }
}
