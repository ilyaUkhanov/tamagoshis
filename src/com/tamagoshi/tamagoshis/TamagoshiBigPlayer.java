package com.tamagoshi.tamagoshis;

public class TamagoshiBigPlayer extends Tamagoshi {

    public TamagoshiBigPlayer(String name) {
        super(name);
    }

    @Override
    protected String getPrefix() {
        return "Tamagoshi Gros Joueur - " + super.name;
    }

    @Override
    public void consumeFun() {
        super.changeFun(-2);
    }
}
