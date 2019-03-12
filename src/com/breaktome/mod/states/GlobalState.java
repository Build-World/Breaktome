package com.breaktome.mod.states;

import com.breaktome.game.events.EventService;
import com.breaktome.game.registries.RegistryService;
import com.breaktome.game.states.BreaktomeState;
import com.breaktome.mod.blocks.BlankBlock;
import com.breaktome.mod.blocks.GrassBlock;
import com.jme3.app.Application;

public class GlobalState extends BreaktomeState {

    private RegistryService registries;
    private EventService events;

    public GlobalState() {
        registries = new RegistryService();
        events = new EventService();
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

    public RegistryService getRegistries() {
        return registries;
    }

    public EventService getEvents() {
        return events;
    }

    public void load() {
        registries.getBlockLoader().register(new BlankBlock());
        registries.getBlockLoader().register(new GrassBlock());
    }
}
