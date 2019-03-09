package com.breaktome.game.mod;

public interface IClientMod {

    void onClientLoad() throws Exception;
    void onClientReady() throws Exception;
    void onClientPlay() throws Exception;
    void onClientUpdate(float tpf) throws Exception;

    void registerAssetLocator(String path) throws Exception;

}
