package com.tamagoshi.jeu;

import com.tamagoshi.tamagoshis.Tamagoshi;
import com.tamagoshi.tamagoshis.TamagoshiBigPlayer;
import com.tamagoshi.tamagoshis.TamagoshiReaction;
import com.tamagoshi.tamagoshis.TamahoshiBigMuncher;
import com.tamagoshi.util.UtilFunctions;
import com.tamagoshi.util.Utilisateur;

import java.util.*;
import java.util.stream.Collectors;

public class TamaGame {
    enum PlayerActionsTypeEnum {
        PLAY,
        FEED
    }

    List<Tamagoshi> initialTamagoshis;
    List<Tamagoshi> aliveTamagoshis;

    /**
     * Constructeur
     */
    public TamaGame() {
        initialTamagoshis = new ArrayList<Tamagoshi>();
        aliveTamagoshis = new ArrayList<Tamagoshi>();

        // Saisie du nombre de Tamas
        Integer tamagoshisAmount = Utilisateur.safeIntegerKeyboardInput("Veuillez saisiz le nombre de Tamagoshis dans le jeu (q pour quitter):");
        if (tamagoshisAmount == null) {
            System.exit(0);
        }

        // Saisie des noms des Tamas
        for (int i = 0; i < tamagoshisAmount; ++i) {
            String name = Utilisateur.safeStringKeyboardInput("Veuillez saisir le nom du Tamagoshi #" + (i + 1) + " (q pour quitter) : ");
            if (name == null) {
                System.exit(0);
            }

            // Génération aléatoire des différents types de Tamas
            Tamagoshi tamagoshi;
            int randomNumber = UtilFunctions.getRandomNumber(1, 100);
            if (randomNumber <= 50) {
                tamagoshi = new Tamagoshi(name);
            }
            else if (randomNumber <= 75){
                tamagoshi = new TamagoshiBigPlayer(name);
            }
            else {
                tamagoshi = new TamahoshiBigMuncher(name);
            }


            initialTamagoshis.add(tamagoshi);
            aliveTamagoshis.add(tamagoshi);
        }
    }

    /**
     * Lance la boucle du jeu
     */
    public void play() {
        int turn = 1;
        while (true) {
            System.out.println("--- --- --- --- TOUR #" + turn + " --- --- --- ---");

            for (Tamagoshi tamagoshi : initialTamagoshis) {
                tamagoshi.say();
            }

            decideActions();

            for (Tamagoshi tamagoshi: initialTamagoshis) {
                tamagoshi.becomeOlder();
                tamagoshi.consumeEnergie();
                tamagoshi.consumeFun();

                System.out.println(tamagoshi);

                if (tamagoshi.isDead()) {
                    aliveTamagoshis.remove(tamagoshi);
                }
            }

            if (aliveTamagoshis.isEmpty()) {
                endGame();
                break;
            }

            ++turn;
        }
    }

    /**
     * Les actions du joueur
     */
    public void decideActions() {
        playAction("Veuillez saisir le nom du Tamagoshi à nourrir (q pour ne nourrir personne) : ", PlayerActionsTypeEnum.FEED);
        playAction("Veuillez saisir le nom du Tamagoshi avec qui jouer (q pour ne jouer avec personne) : ", PlayerActionsTypeEnum.PLAY);
    }

    /**
     * Saisir et appliquer une action du joueur sur un tama
     * @param defaultMessage: le message à afficher avant la saisie
     * @param actionType: l'action à saisir
     */
    public void playAction(String defaultMessage, PlayerActionsTypeEnum actionType) {
        while(true) {
            String name = Utilisateur.safeStringKeyboardInput(defaultMessage);

            if (name == null) {
                break;
            }

            Optional<Tamagoshi> foundTama = aliveTamagoshis
                    .stream()
                    .filter((Tamagoshi tama) -> Objects.equals(tama.getName().toLowerCase(), name.toLowerCase()))
                    .findFirst();

            if (foundTama.isPresent()) {
                if (actionType == PlayerActionsTypeEnum.PLAY) {
                    TamagoshiReaction reaction = foundTama.get().play();
                    foundTama.get().sayReaction(reaction);
                }
                if (actionType == PlayerActionsTypeEnum.FEED) {
                    TamagoshiReaction reaction = foundTama.get().eat();
                    foundTama.get().sayReaction(reaction);
                }
                break;
            }
            else {
                System.out.println("Le nom que tu as saisi n'existe pas");

                String aliveTamasNames = aliveTamagoshis.stream()
                        .map(Tamagoshi::getName)
                        .collect(Collectors.joining(", "));
                System.out.println("Voici la liste des tamagoshis en vie: " + aliveTamasNames);
            }
        }
    }

    /**
     * @return le score actuel du joueur
     */
    private int getScorePercentage() {
        double tamaAges = initialTamagoshis.stream().mapToInt(Tamagoshi::getAge).sum();
        double tamaMaxAges = Tamagoshi.LIFETIME * initialTamagoshis.toArray().length;
        double score = tamaAges / tamaMaxAges;

        return (int) Math.floor(score * 100);
    }

    /**
     * Affiche le score actuel du joueur
     */
    private void printScore() {
        System.out.println("Votre score: " + getScorePercentage() + "%");
    }

    /**
     * Affiche le bilan du jeu
     */
    private void endGame() {
        System.out.println("Fin du jeu! Voici le bilan:");
        for (Tamagoshi tamagoshi : initialTamagoshis) {
            tamagoshi.say();
        }
        for (Tamagoshi tamagoshi : initialTamagoshis) {
            tamagoshi.sayFinalState();
        }
        printScore();
    }
}
