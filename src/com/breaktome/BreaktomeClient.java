package com.breaktome;

import com.breaktome.game.network.client.PlayerClient;
import com.breaktome.game.states.client.MainMenu;
import com.breaktome.game.states.client.PauseMenu;
import com.breaktome.game.states.client.SandboxClient;
import com.jme3.system.AppSettings;

/**
 * Gameplay client featuring single player and multiplayer functionality
 * Singleplayer:
 *   - Incorporates the BreaktomeServer, and launches it
 *   - Client connects to the BreaktomeServer
 * Multiplayer:
 *   - Client connects to a dedicated instance of a BreaktomeServer
 */
public class BreaktomeClient extends Breaktome {

    // Networking
    private PlayerClient playerClient;

    // Game modes, game states, and menus
    private SandboxClient sandboxClient;
    private MainMenu mainMenu;
    private PauseMenu pauseMenu;


    @Override
    boolean isServer() {
        return false;
    }

    public void initialSettings()
    {
        // All the possible settings are described here: https://wiki.jmonkeyengine.org/jme3/intermediate/appsettings.html

        AppSettings settings = new AppSettings(true);

        // Max Framerate
        settings.setFrameRate(144);

        // Anti Aliasing
        settings.setSamples(4);

        // VSync
        settings.setVSync(false);

        // Does some gamma correction stuff and this should ALWAYS be true no matter what
        settings.setGammaCorrection(true);

        // Resolution
        settings.setResolution(1280, 720);

        // Debug Statistics
        setDisplayFps(true);
        setDisplayStatView(true);

        // Print out settings
        System.out.println(settings);

        setSettings(settings);
        restart();
    }

    @Override
    public void onBoot() throws Exception {
        // Setting up app settings
//        initialSettings();

        // Setting up asset loaders
        registerAssetLocator(assetsPath);

        // Adding our game states and game modes
        sandboxClient = new SandboxClient();
        registerAppState(sandboxClient);

        mainMenu = new MainMenu();
        registerAppState(mainMenu);

        pauseMenu = new PauseMenu();
        registerAppState(pauseMenu);

        playerClient = new PlayerClient(this,"127.0.0.1", 4242);
        playerClient.start();
    }

    @Override
    public void onClientReady() throws Exception {

        // Turn on the initial game mode
        sandboxClient.setEnabled(true);
    }

    @Override
    public void destroy() {
        playerClient.destroy();
        super.destroy();
    }
}
