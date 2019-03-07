package com.breaktome.game.mod;

import com.breaktome.Breaktome;

/**
 * Mod class which is used with JMonkey Game Engine
 */
abstract public class BreaktomeMod implements IMod {

    private Breaktome app;

    public BreaktomeMod(Breaktome app) {
        this.app = app;
    }

    public Breaktome getApp() {
        return app;
    }

    @Override
    public boolean isRunningOnServer() {
        return app.isRunningOnServer();
    }

    @Override
    public boolean isRunningOnClient() {
        return app.isRunningOnClient();
    }

    @Override
    public void onLoad() throws Exception {
        if(isRunningOnClient())
        {
            onClientLoad();
        } else {
            onServerLoad();
        }
    }

    @Override
    public void onReady() throws Exception {
        if(isRunningOnClient())
        {
            onClientReady();
        } else {
            onServerReady();
        }
    }

    @Override
    public void onPlay() throws Exception {
        if(isRunningOnClient())
        {
            onClientPlay();
        } else {
            onServerPlay();
        }
    }

    @Override
    public void onUpdate(float tpf) throws Exception {
        if(isRunningOnClient())
        {
            onClientUpdate(tpf);
        } else {
            onServerUpdate(tpf);
        }
    }

    // Default implementations below

    @Override
    public void onClientLoad() throws Exception {

    }

    @Override
    public void onClientReady() throws Exception {

    }

    @Override
    public void onClientPlay() throws Exception {

    }

    @Override
    public void onClientUpdate(float tpf) throws Exception {

    }

    @Override
    public void onServerLoad() throws Exception {

    }

    @Override
    public void onServerReady() throws Exception {

    }

    @Override
    public void onServerPlay() throws Exception {

    }

    @Override
    public void onServerUpdate(float tpf) throws Exception {

    }
}
