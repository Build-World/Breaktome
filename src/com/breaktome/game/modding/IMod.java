package com.breaktome.game.modding;

import com.breaktome.engine.interfaces.IKeyNameDescibe;
import com.breaktome.engine.interfaces.IVersion;
import com.breaktome.game.events.IEventListener;
import com.breaktome.game.registries.IRegistry;

import java.util.HashSet;
import java.util.Set;

/**
 * IMod Interface which is not tied to any particular game library or framework
 */
public interface IMod extends IKeyNameDescibe, IVersion, IServerMod, IClientMod {

    // Fired for both server and client
    void onBoot() throws Exception;
    void onEnable() throws Exception;
    void onDisable() throws Exception;
    boolean isRunningOnServer();
    boolean isRunningOnClient();

    // Fired separately for server and client
    void onLoad() throws Exception;
    void onReady() throws Exception;
    void onPlay() throws Exception;
    void onUpdate(float tpf) throws Exception;

    Set<IRegistry> registerRegistries();
    Set<IEventListener> registerEvents();

}
