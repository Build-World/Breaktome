package com.breaktome.game.modding;

import com.breaktome.game.network.server.IServerMessageListener;
import com.jme3.network.ConnectionListener;

import java.util.Set;

public interface IServerMod {

    void onServerLoad() throws Exception;
    void onServerReady() throws Exception;
    void onServerPlay() throws Exception;
    void onServerUpdate(float tpf) throws Exception;

    Set<ConnectionListener> registerConnectionListeners();

    Set<IServerMessageListener> registerServerMessageListeners();

}
