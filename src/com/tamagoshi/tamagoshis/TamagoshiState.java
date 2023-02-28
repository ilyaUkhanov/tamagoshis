package com.tamagoshi.tamagoshis;

enum TamagoshiStateTypeEnum {
    HAPPY_FOOD,
    HAPPY_FUN,
    NO_FUN,
    HUNGRY,
    KO_FOOD,
    KO_FUN,
    DEAD_OF_OLD_AGE
}

/**
 * Représente l'état d'un Tama
 */
public class TamagoshiState {
    TamagoshiStateTypeEnum stateType;
    boolean isDead;

    String message;

    public TamagoshiState(TamagoshiStateTypeEnum stateType, boolean isDead, String message) {
        this.stateType = stateType;
        this.isDead = isDead;
        this.message = message;
    }

    @Override
    public String toString() {
        return "TamagoshiState{" +
                "stateType=" + stateType +
                ", isDead=" + isDead +
                ", message='" + message + '\'' +
                '}';
    }
}
