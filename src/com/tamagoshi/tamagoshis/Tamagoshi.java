package com.tamagoshi.tamagoshis;

import com.tamagoshi.util.UtilFunctions;

import java.util.ArrayList;
import java.util.List;


public class Tamagoshi {
    private Integer age;
    private final Integer maxEnergy;
    private Integer energy;

    private Integer fun;

    private final Integer maxFun;

    protected final String name;

    public static final int LIFETIME = 5;

    /**
     * Constructeur
     * @param name: nom du Tama
     */
    public Tamagoshi(String name) {
        this.name = name;
        this.age = 0;
        this.maxEnergy = UtilFunctions.getRandomNumber(5, 9);
        this.energy    = UtilFunctions.getRandomNumber(3, 7);
        this.maxFun    = UtilFunctions.getRandomNumber(5, 9);
        this.fun       = UtilFunctions.getRandomNumber(3, 7);
    }

    /**
     * @return si tama est mort
     */
    public boolean isDead() {
        return getCurrentStates()
                .stream()
                .reduce(false, (partialIsDead, state) -> partialIsDead || state.isDead, Boolean::logicalOr);
    }

    /**
     * @return si tama est mort de vieillesse
     */
    public boolean isDeadOfOldAge() {
        return getCurrentStates()
                .stream()
                .reduce(false, (partialIsDead, state) -> partialIsDead || state.stateType == TamagoshiStateTypeEnum.DEAD_OF_OLD_AGE, Boolean::logicalOr);
    }

    /**
     * Print dans la console l'état du Tama
     */
    public void say() {
        String state = getCurrentStateString();
        System.out.println(state);
    }

    /**
     * Print dans la console l'état du Tama à la fin du jeu
     */
    public void sayFinalState() {
        String state = getFinalStateString();
        System.out.println(state);
    }

    /**
     * Affiche un TamagoshiReaction en l'annotant spécifiquement pour le Tama
     * @param reaction: la réaction à afficher
     */
    public void sayReaction(TamagoshiReaction reaction) {
        String reactionString =
                getPrefix()
                + ": Je réagis à ton action -->"
                + reaction.message + ". "
                + (reaction.isPositive ? "Je suis content" : "Je ne suis pas content");
        System.out.println(reactionString);
    }

    /**
     * Fonction qui simule l'action de nourrir le Tama
     * @return TamagoshiReactionTypeEnum: la réaction du Tama à cette action
     */
    public TamagoshiReaction eat() {
        if(this.energy >= this.maxEnergy) {
            return TamagoshiReactionFactory.create(TamagoshiReactionTypeEnum.NOT_HUNGRY);
        }
        else {
            changeEnergy(UtilFunctions.getRandomNumber(1, 3));
            return TamagoshiReactionFactory.create(TamagoshiReactionTypeEnum.WELL_FED);
        }
    }

    /**
     * Fonction qui simule l'action de jouer avec le Tama
     * @return TamagoshiReactionTypeEnum: la réaction du Tama à cette action
     */
    public TamagoshiReaction play() {
        if(this.fun >= this.maxFun) {
            return TamagoshiReactionFactory.create(TamagoshiReactionTypeEnum.NOT_PLAYABLE);
        }
        else {
            changeFun(UtilFunctions.getRandomNumber(1, 3));
            return TamagoshiReactionFactory.create(TamagoshiReactionTypeEnum.WELL_PLAYED);
        }
    }

    /**
     * Avance l'age du tama
     */
    public void becomeOlder() {
        this.age += 1;
    }

    /**
     * Consomme l'énergie du Tama
     */
    public void consumeEnergie() {
        changeEnergy(-1);
    }

    /**
     * Consomme le fun du Tama
     */
    public void consumeFun() {
        changeFun(-1);
    }

    /**
     * Renvoie les états actuels de Tama (s'il a faim, s'il veut jouer, etc...)
     * @return Liste des TamagoshiState
     */
    public List<TamagoshiState> getCurrentStates() {
        List<TamagoshiState> currentStates = new ArrayList<>();

        if (this.energy <= 0) {
            currentStates.add(TamagoshiStateFactory.create(TamagoshiStateTypeEnum.KO_FOOD));
            return currentStates;
        }

        if (this.fun <= 0) {
            currentStates.add(TamagoshiStateFactory.create(TamagoshiStateTypeEnum.KO_FUN));
            return currentStates;
        }

        if (this.age >= Tamagoshi.LIFETIME) {
            currentStates.add(TamagoshiStateFactory.create(TamagoshiStateTypeEnum.DEAD_OF_OLD_AGE));
            return currentStates;
        }

        if (this.energy >= 3) {
            currentStates.add(TamagoshiStateFactory.create(TamagoshiStateTypeEnum.HAPPY_FOOD));
        }
        else {
            currentStates.add(TamagoshiStateFactory.create(TamagoshiStateTypeEnum.HUNGRY));
        }

        if (this.fun >= 3) {
            currentStates.add(TamagoshiStateFactory.create(TamagoshiStateTypeEnum.HAPPY_FUN));
        }
        else {
            currentStates.add(TamagoshiStateFactory.create(TamagoshiStateTypeEnum.NO_FUN));
        }

        return currentStates;
    }

    /**
     * @return Le préfixe avec le prénom du Tama
     */
    protected String getPrefix() {
        return "Tamagoshi - " + this.name;
    }

    /**
     * Renvoie la liste des états du Tama sous forme affichable dans la console
     * @return string affichable
     */
    protected String getCurrentStateString() {
        StringBuilder stateString = new StringBuilder(getPrefix() + ": ");
        List<TamagoshiState> states = getCurrentStates();

        for (int i = 0; i < states.toArray().length; ++i) {
            TamagoshiState state = states.get(i);
            stateString.append(state.message);
            if (state.isDead) {
                stateString.append(" --> ").append("Je suis hors du jeu");
            }

            if (i != states.toArray().length) {
                stateString.append(", ");
            }
        }

        return stateString.toString();
    }

    /**
     * @return Etat final du tama, affichable
     */
    private String getFinalStateString() {
        return getPrefix() + ": " + (isDeadOfOldAge() ? "est mort de vieillesse" : "est mort par votre faute");
    }

    /**
     * Change l'énergie du Tama
     * @param amount Montant de l'energie à changer (négatif ou positif)
     */
    protected void changeEnergy(Integer amount) {
        this.energy += amount;

        if (this.energy >= maxEnergy) {
            this.energy = maxEnergy;
        }
    }

    /**
     * Change le fun du Tama
     * @param amount Montant du fun à changer (négatif ou positif)
     */
    protected void changeFun(Integer amount) {
        this.fun += amount;

        if (this.fun >= maxFun) {
            this.fun = maxFun;
        }
    }

    public Integer getAge() {
        return age;
    }

    public Integer getMaxEnergy() {
        return maxEnergy;
    }

    public Integer getEnergy() {
        return energy;
    }

    public Integer getFun() {
        return fun;
    }

    public Integer getMaxFun() {
        return maxFun;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tamagoshi{" +
                "age=" + age +
                ", maxEnergy=" + maxEnergy +
                ", energy=" + energy +
                ", fun=" + fun +
                ", maxFun=" + maxFun +
                ", name='" + name + '\'' +
                '}';
    }
}
