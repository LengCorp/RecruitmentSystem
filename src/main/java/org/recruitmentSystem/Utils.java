package org.recruitmentSystem;

/**
 * Created by Hiden on 2/22/2017.
 */
public class Utils {
    public static String regexNames( String text){
        return text.replaceAll("[^A-Za-z]","");
    }
    public static String regexUsernames(String email){
        return email.replaceAll("[^a-zA-Z0-9]","");
    }
    public static String regexPasswords(String email){
        return email.replaceAll("[^a-zA-Z0-9]","");
    }
    public static String regexSSN(String ssn){
        return ssn.replaceAll("[^0-9-]","");
    }
    public static String regexEmail(String email){
        return email.replaceAll("[^a-zA-Z0-9.@-]","");
    }
}
