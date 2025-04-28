package util;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputMatcher {


    public static String getInst(String line){
        return switch (line.toUpperCase()){
            case "ADD"->"ADD";
            case "UPDATE"->"UPDATE";
            case "DELETE"->"DELETE";
            case "SHOWALL"->"SHOWALL";
            case "SHOW"->"SHOW";
            case "HELP" -> "HELP";
            default -> "ERROR";
        };
    }
}
