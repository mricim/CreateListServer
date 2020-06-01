package main.java.Methodos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.java.Methodos.Methodos.scanner;


public class MethodosScanner {

    public static String scannerString(String textoParaElUsuario) {
        return scannerString(textoParaElUsuario, null);
    }
    public static String scannerNumberPhone(String textoParaElUsuario) {
        String resultado;
        do {
            resultado=scannerString(textoParaElUsuario);
        }while (Methodos.equalsPattern(resultado,"^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$"));
        return resultado;
    }
    public static String scannerString(String textoParaElUsuario, Integer charsMaximos) {
        boolean resultado = true;
        String insetUser;
        do {
            System.out.print(textoParaElUsuario);
            insetUser = scanner.nextLine().trim();
            int insertUserCount = insetUser.length();
            if (insertUserCount <= 50 && insertUserCount > 0 || charsMaximos == null) {
                resultado = false;
            } else {
                System.out.println("\nEl valor introducido debe ser mayor a 0 caracteres y menor a " + charsMaximos
                        + ".\nUsted introdujo: " + insetUser + " | con un tamaño:" + insertUserCount + " caracteres");
            }
        } while (resultado);
        return insetUser;
    }

    public static Integer scannerIntegerObtions(String textoParaElUsuario, Integer[] opciones, boolean volverPreguntar) {
        Integer numer;
        do {
            numer = scannerInteger(textoParaElUsuario, opciones, null);
        } while (numer == null && volverPreguntar);
        return numer;
    }

    public static int scannerIntegerMaxCharacters(String textoParaElUsuario, Integer charsMaximos) {
        return scannerInteger(textoParaElUsuario, null, charsMaximos);
    }

    public static Integer scannerInteger(String textoParaElUsuario, Integer[] opciones, Integer charsMaximos) {
        List<Integer> posibles = null;
        if (opciones != null) {
            posibles = new ArrayList<>(Arrays.asList(opciones));
        }
        boolean resultado = true;
        Integer insertUser = null;
        do {
            System.out.print(textoParaElUsuario + " ");
            try {
                String temporal = scanner.nextLine().trim();
                insertUser = Integer.parseInt(temporal);
                if (posibles != null) {
                    if (posibles.contains(insertUser)) {
                        resultado = false;
                    } else {
                        System.out.println("\nEl valor " + insertUser + " no es un valor correcto.");
                        return null;
                    }
                } else {
                    if (charsMaximos == null) {
                        charsMaximos = String.valueOf(Integer.MAX_VALUE).length();
                    }
                    int insertUserCount = temporal.length();
                    if (insertUserCount <= charsMaximos && insertUserCount > 0) {
                        resultado = false;
                    } else {
                        System.out.println("\nEl valor introducido debe ser mayor a 0 y menor a " + new String(new char[charsMaximos]).replace('\0', '9') + "!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Solo se admiten numeros!\n");
                resultado = true;
            }
        } while (resultado);
        return insertUser;
    }
}
