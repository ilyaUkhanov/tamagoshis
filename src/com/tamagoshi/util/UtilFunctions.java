package com.tamagoshi.util;

public class UtilFunctions {
    /**
     * Renvoie un nombre aléatoire entre les bornes min et max inclus.
     * @param min: borne minimale du nombre aléatoire
     * @param max: borne maximale du nombre aléatoire
     * @return nombre aléatoire compris entre min et max
     */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Vérifie si un string est un nombre
     * @param strNum: le string à vérifier
     * @return true si string est un nombre
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
