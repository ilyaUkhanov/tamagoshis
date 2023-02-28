package com.tamagoshi.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Utilisateur {
    static final List<String> stringsToQuit = Arrays.asList("q", "quit");

    /**
     * Gère la saisie
     * @return la saisie
     */
    public static String keyboardInput() {
        try{
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            return clavier.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    /**
     * Gère la saisie et Vérifie que l'utilisateur a correctement saisi un nombre
     * @param defaultMessage: un message à afficher avant la saisie
     * @return la saisie ou null si l'utilisateur abandonne
     */
    public static Integer safeIntegerKeyboardInput(String defaultMessage) {
        String input;

        while (true) {
            System.out.println(defaultMessage);
            input = Utilisateur.keyboardInput();

            if (UtilFunctions.isNumeric(input)) {
                return parseInt(input);
            }
            else if (stringsToQuit.contains(input)) {
                return null;
            }
            else {
                System.out.println("Vous devez saisir un nombre!");
            }
        }
    }

    /**
     * Gère la saisie et Vérifie que l'utilisateur a correctement saisi un string
     * @param defaultMessage: un message à afficher avant la saisie
     * @return la saisie ou null si l'utilisateur abandonne
     */
    public static String safeStringKeyboardInput(String defaultMessage) {
        String input;

        while (true) {
            System.out.println(defaultMessage);
            input = Utilisateur.keyboardInput();

            if (stringsToQuit.contains(input)) {
                return null;
            }
            else if (Objects.equals(input, "")){
                System.out.println("Vous devez saisir une valeur!");
            }
            else {
                return input;
            }
        }
    }
}