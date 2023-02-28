package com.tamagoshi.tamagoshis;

public class TamahoshiBigMuncher extends Tamagoshi {
    public TamahoshiBigMuncher(String name) {
        super(name);
    }

    @Override
    protected String getPrefix() {
        return "Tamagoshi Gros Mangeur - " + super.name;
    }

    @Override
    public void consumeFun() {
        super.changeEnergy(-2);
    }
}
