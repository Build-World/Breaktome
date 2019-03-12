package com.breaktome.game.states;

import com.breaktome.game.registries.Registries;
import com.breaktome.mod.blocks.BlankBlock;
import com.breaktome.mod.blocks.GrassBlock;
import com.jme3.app.Application;

public class GlobalState extends BreaktomeState {

    private Registries registries;

    public GlobalState() {
        registries = new Registries();
    }

    @Override
    protected void initialize(Application application) {
        super.initialize(application);
    }

    @Override
    protected void cleanup(Application application) {
        super.cleanup(application);
    }

    @Override
    protected void onEnable() {
        super.onEnable();
    }

    @Override
    protected void onDisable() {
        super.onDisable();
    }

    public Registries getRegistries() {
        return registries;
    }

    public void load() {
        registries.getBlockLoader().register(new BlankBlock());
        registries.getBlockLoader().register(new GrassBlock());
    }
}
