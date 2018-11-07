package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

public class Converters {
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
    String myDate = "Tue Nov 18 00:00:00 GMT 2014";
    String strMnth    = myDate.substring(4,7);
    String day        = myDate.substring(8,10);
    String year       = myDate.substring(24,28);
    String strMonth ;
/*if(strMnth=="Jan"){
        strMonth ='1';
    }
else if (strMnth =="Feb" )
    strMonth ='2';
else if (strMnth == "" )
    strMonth ='3';
else if (strMnth == 'Nov' )
    strMonth ='4';
else if (strMnth == 'Nov' )
    strMonth ='5';
else if (strMnth == 'Nov' )
    strMonth ='6';
else if (strMnth == 'Nov' )
    strMonth ='7';
else if (strMnth == 'Nov' )
    strMonth ='8';
else if (strMnth == 'Nov' )
    strMonth ='9';
else if (strMnth == 'Nov' )
    strMonth ='10';
else if (strMnth == 'Nov' )
    strMonth ='11';
else if (strMnth == 'Nov' )
    strMonth ='12';*/



    String strDate = strMonth +'/'+day+'/'+year;
}
