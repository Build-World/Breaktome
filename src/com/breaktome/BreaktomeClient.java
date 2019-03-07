package com.breaktome;

import com.breaktome.game.network.client.PlayerClient;
import com.jme3.app.SimpleApplication;

public class BreaktomeClient extends SimpleApplication {

    private PlayerClient playerClient;

    @Override
    public void simpleInitApp() {
        playerClient = new PlayerClient("127.0.0.1", 4242);
        playerClient.start();
    }

    @Override
    public void destroy() {
        playerClient.destroy();
        super.destroy();
    }


}
