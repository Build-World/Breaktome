package com.breaktome;

import com.breaktome.game.network.client.PlayerClient;

/**
 * Gameplay client featuring single player and multiplayer functionality
 * Singleplayer:
 *   - Incorporates the BreaktomeServer, and launches it
 *   - Client connects to the BreaktomeServer
 * Multiplayer:
 *   - Client connects to a dedicated instance of a BreaktomeServer
 */
public class BreaktomeClient extends Breaktome {

    private PlayerClient playerClient;

    @Override
    boolean isServer() {
        return false;
    }

    @Override
    public void onBoot() throws Exception {
        playerClient = new PlayerClient("127.0.0.1", 4242);
        playerClient.start();
    }

    @Override
    public void destroy() {
        playerClient.destroy();
        super.destroy();
    }
}
