package com.breaktome;

import com.breaktome.game.network.server.GameServer;

public class BreaktomeServer extends Breaktome {

    private GameServer gameServer;

    public BreaktomeServer() {
        setName("Breaktome Server");
    }

    @Override
    boolean isServer() {
        return true;
    }

    @Override
    public void onBoot() throws Exception {
        gameServer = new GameServer(this,4242);
        gameServer.start();
    }

    @Override
    public void onUpdate(float tpf) throws Exception {
        //System.out.println(gameServer.getServer().getConnections().size());
    }

    @Override
    public void destroy() {
        gameServer.destroy();
        super.destroy();
    }
}
