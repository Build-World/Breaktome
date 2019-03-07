package com.breaktome;

import com.breaktome.game.network.server.GameServer;
import com.jme3.app.SimpleApplication;

public class BreaktomeServer extends SimpleApplication {

    private GameServer gameServer;

    @Override
    public void simpleInitApp() {
        gameServer = new GameServer(4242);
        gameServer.start();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //System.out.println(gameServer.getServer().getConnections().size());
    }

    @Override
    public void destroy() {
        gameServer.destroy();
        super.destroy();
    }
}
