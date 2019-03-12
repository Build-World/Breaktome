package com.breaktome.game.modding;

import com.breaktome.game.network.client.IClientMessageListener;
import com.jme3.network.ClientStateListener;

import java.util.Set;

public interface IClientMod {

    void onClientLoad() throws Exception;
    void onClientReady() throws Exception;
    void onClientPlay() throws Exception;
    void onClientUpdate(float tpf) throws Exception;

    void registerAssetLocator(String path) throws Exception;

    Set<IClientMessageListener> registerClientMessageListeners();

    Set<ClientStateListener> registerClientStateListeners();

}
