package com.breaktome;

import com.breaktome.game.network.client.IClientMessageListener;
import com.breaktome.game.network.client.PlayerClient;
import com.breaktome.mod.network.client.listeners.messages.BlockRegistryListener;
import com.breaktome.mod.network.client.listeners.messages.ChunkListener;
import com.breaktome.mod.network.client.listeners.messages.PlayersListener;
import com.breaktome.mod.network.messages.BlockRegistryMessage;
import com.breaktome.mod.network.messages.ChunkMessage;
import com.breaktome.mod.network.messages.PlayersMessage;
import com.breaktome.mod.states.client.MainMenu;
import com.breaktome.mod.states.client.PauseMenu;
import com.breaktome.mod.states.client.SandboxClient;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.MessageListener;
import com.jme3.system.AppSettings;

import java.util.HashSet;
import java.util.Set;

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

        playerClient = new PlayerClient("127.0.0.1", 4242);

        // Adds core client state listeners
        for(ClientStateListener clientStateListener : registerClientStateListeners())
        {
            playerClient.getClient().addClientStateListener(clientStateListener);
        }

        // Adds mod client state listeners
        for(ClientStateListener clientStateListener : modLoader.registerClientStateListeners())
        {
            playerClient.getClient().addClientStateListener(clientStateListener);
        }

        // Adds core message listeners
        for(IClientMessageListener messageListener : registerClientMessageListeners())
        {
            playerClient.getClient().addMessageListener(messageListener, messageListener.getMessageType());
        }

        // Adds mod message listeners
        for(IClientMessageListener messageListener :  modLoader.registerClientMessageListeners())
        {
            playerClient.getClient().addMessageListener(messageListener, messageListener.getMessageType());
        }
    }

    @Override
    public void onClientLoad() throws Exception {
        super.onClientLoad();
    }

    @Override
    public void onClientReady() throws Exception {
        // Turn on the initial game mode
        sandboxClient.setEnabled(true);
    }

    @Override
    public void onClientPlay() throws Exception {
        playerClient.start();
    }

    @Override
    public void onClientUpdate(float tpf) throws Exception {
        super.onClientUpdate(tpf);
    }

    @Override
    public void destroy() {
        playerClient.destroy();
        super.destroy();
    }

    @Override
    public Set<IClientMessageListener> registerClientMessageListeners() {
        Set<IClientMessageListener> messageListeners = new HashSet<>();
        messageListeners.add(new PlayersListener());
        messageListeners.add(new BlockRegistryListener(this));
        messageListeners.add(new ChunkListener(this));
        return messageListeners;
    }

    @Override
    public Set<ClientStateListener> registerClientStateListeners() {
        Set<ClientStateListener> clientStateListeners = new HashSet<>();
        clientStateListeners.add(new com.breaktome.mod.network.client.listeners.ClientStateListener());
        return clientStateListeners;
    }
}
