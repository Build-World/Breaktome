package com.breaktome.game.mod;

public interface IServerMod {

    void onServerLoad() throws Exception;
    void onServerReady() throws Exception;
    void onServerPlay() throws Exception;
    void onServerUpdate(float tpf) throws Exception;

}
