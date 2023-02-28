package com.tamagoshi.tamagoshis;

/**
 * Construt TamagoshiState
 */
public class TamagoshiStateFactory {
    public static TamagoshiState create(TamagoshiStateTypeEnum stateType) {
        switch (stateType) {
            case KO_FOOD:
                return new TamagoshiState(stateType, true, "Je suis KO de faim! Tu t'es mal occupé de moi");
            case KO_FUN:
                return new TamagoshiState(stateType, true, "Je suis KO par manque de fun! Tu t'es mal occupé de moi");
            case DEAD_OF_OLD_AGE:
                return new TamagoshiState(stateType, true, "Je suis mort de vieillesse et j'étais heureux =)");
            case HUNGRY:
                return new TamagoshiState(stateType, false, "J'ai faim!");
            case HAPPY_FUN:
                return new TamagoshiState(stateType, false, "J'ai bien joué!");
            case HAPPY_FOOD:
                return new TamagoshiState(stateType, false, "J'ai bien mangé!");
            case NO_FUN:
                return new TamagoshiState(stateType, false, "J'ai besoin de jouer!");
        }

        throw new EnumConstantNotPresentException(TamagoshiStateTypeEnum.class, stateType.toString());
    }
}
