package com.tamagoshi.tamagoshis;

enum TamagoshiReactionTypeEnum {
    WELL_PLAYED,
    NOT_HUNGRY,

    NOT_PLAYABLE,
    WELL_FED
}

/**
 * La réaction d'un Tama à une action du joueur
 */
public class TamagoshiReaction {
    TamagoshiReactionTypeEnum reactionType;
    boolean isPositive;

    String message;

    public TamagoshiReaction(TamagoshiReactionTypeEnum reactionType, boolean isPositive, String message) {
        this.reactionType = reactionType;
        this.isPositive = isPositive;
        this.message = message;
    }

    @Override
    public String toString() {
        return "TamagoshiReaction{" +
                "reactionType=" + reactionType +
                ", isPositive=" + isPositive +
                ", message='" + message + '\'' +
                '}';
    }
}
