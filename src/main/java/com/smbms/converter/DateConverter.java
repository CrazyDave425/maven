package com.smbms.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter <String, Date>{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public Date convert(String s) {
        Date date=null;
        if (s==null||"".equals(s.trim())){
            return null;
        }
        try {
            if (s.length()==10){
                date=sdf.parse(s);
            }else if (s.length()==19){
                date=sdf2.parse(s);
            }else if (s.length()==17){
                date=sdf3.parse(s);
            }
    }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
}
