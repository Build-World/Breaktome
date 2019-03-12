package com.breaktome.game.modding;

import com.breaktome.Breaktome;
import com.breaktome.game.events.IEventListener;
import com.breaktome.game.network.client.IClientMessageListener;
import com.breaktome.game.network.server.IServerMessageListener;
import com.breaktome.game.registries.IRegistry;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.network.*;

import java.util.HashSet;
import java.util.Set;

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
    public void registerAssetLocator(String path) throws Exception {
        app.getAssetManager().registerLocator(path, FileLocator.class);
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

    @Override
    public Set<IClientMessageListener> registerClientMessageListeners()
    {
        return new HashSet<>();
    }

    @Override
    public Set<ClientStateListener> registerClientStateListeners()
    {
        return new HashSet<>();
    }

    @Override
    public Set<ConnectionListener> registerConnectionListeners()
    {
        return new HashSet<>();
    }

    @Override
    public Set<IServerMessageListener> registerServerMessageListeners()
    {
        return new HashSet<>();
    }

    @Override
    public Set<IRegistry> registerRegistries()
    {
        return new HashSet<>();
    }

    @Override
    public Set<IEventListener> registerEvents() {
        return new HashSet<>();
    }
}
