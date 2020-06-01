package main.java.Methodos;

import java.text.Collator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ECM
 */
public class Methodos {

    protected static Scanner scanner = new Scanner(System.in);
    protected static final Collator instance = Collator.getInstance();

    public static void bloquejarPantalla() {
        do {
            System.out.println();
            System.out.println();
            if ("C".equalsIgnoreCase(MethodosScanner.scannerString("Toca 'C' per a continuar: ", 1))) {
                break;
            }
        } while (true);

    }
    protected static boolean equalsPattern(String s, String pattern) {
        Pattern p = Pattern.compile(pattern);//
        Matcher m = p.matcher(s);
        return !(m.find() && m.group().equals(s));
    }



    public static String LocalDateisNULL(LocalDate localDate) {
        try {
            return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (NullPointerException e) {
            return "NO EXISTEIX";
        }
    }

    public static String LocalDateTimeisNULL(LocalDateTime localDate) {
        try {
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (NullPointerException e) {
            return "NO EXISTEIX";
        }
    }


}
