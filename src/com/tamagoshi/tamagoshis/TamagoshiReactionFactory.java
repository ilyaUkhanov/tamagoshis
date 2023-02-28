package com.tamagoshi.tamagoshis;

/**
 * Construit TamagoshiReaction
 */
public class TamagoshiReactionFactory {
    public static TamagoshiReaction create(TamagoshiReactionTypeEnum reactionType) throws EnumConstantNotPresentException {
        switch (reactionType) {
            case WELL_FED:
                return new TamagoshiReaction(reactionType, true, "Miam!");
            case WELL_PLAYED:
                return new TamagoshiReaction(reactionType, true, "Je me suis beaucoup amusé!");

            case NOT_HUNGRY:
                return new TamagoshiReaction(reactionType, false, "J'ai pas faim!");
            case NOT_PLAYABLE:
                return new TamagoshiReaction(reactionType, false, "J'ai déjà trop joué!");

        }

        throw new EnumConstantNotPresentException(TamagoshiReactionTypeEnum.class, reactionType.toString());
    }
}
