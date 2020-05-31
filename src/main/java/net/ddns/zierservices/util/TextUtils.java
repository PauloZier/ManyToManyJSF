package net.ddns.zierservices.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.MaskFormatter;

public class TextUtils {
    
    private TextUtils() {
    
    }
    
    public static String maskText(String text, String mask) {

        try {
            
            MaskFormatter formatter = new MaskFormatter(mask);
            
            formatter.setValueContainsLiteralCharacters(false);
            
            return formatter.valueToString(text);
            
        } catch (Exception ex) {
            
            return text;
            
        }
        
    }
    
    public static String maskFone(String text) {
        
        text = getOnlyNumbersAndLetters(text);
        
        switch (text.length()) {
        
            case 10:
                return maskText(text, "(##) ####-####");
            
            case 11:
                return maskText(text, "(##) #####-####");
                
            default:
                return text;
        }
            
    }
    
    public static String getOnlyNumbers(String text) {
    
        return text.replaceAll("[^0-9]", "");
        
    }
    
    public static String getOnlyNumbersAndLetters(String text) {
    
        return text.replaceAll("[^0-9a-zA-Z]", "");
        
    }
    
    public static String dateToString(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    
    public static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
    
}
