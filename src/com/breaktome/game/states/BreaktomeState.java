package com.breaktome.game.states;

import com.breaktome.Breaktome;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;

abstract public class BreaktomeState extends BaseAppState {

    private Node rootNode;
    private Node guiNode;

    private AssetManager assetManager;
    private InputManager inputManager;
    private ViewPort viewPort;
    private BulletAppState physics;

    private Camera camera;
    private FlyByCamera flyCam;

    private GlobalState globalState;

    /**
     * Disable an app state on creation
     */
    @Override
    protected void initialize(Application application) {
        SimpleApplication simpleApplication = ((SimpleApplication) application);
        AppStateManager stateManager = simpleApplication.getStateManager();

        rootNode = simpleApplication.getRootNode();
        guiNode = simpleApplication.getGuiNode();
        assetManager = simpleApplication.getAssetManager();
        inputManager = simpleApplication.getInputManager();
        viewPort = simpleApplication.getViewPort();
        physics = stateManager.getState(BulletAppState.class);
        camera = simpleApplication.getCamera();
        flyCam = simpleApplication.getFlyByCamera();
        globalState = stateManager.getState(GlobalState.class);

        // Add more of these shortcut variables if need be
    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public BreaktomeState() {
        setEnabled(false);
    }

    public Node getRootNode() {
        return rootNode;
    }

    public Node getGuiNode() {
        return guiNode;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public BulletAppState getPhysics() {
        return physics;
    }

    public Camera getCamera() {
        return camera;
    }

    public FlyByCamera getFlyCam() {
        return flyCam;
    }

    public Breaktome getBreaktomeApplication() {
        return (Breaktome)getApplication();
    }

    public GlobalState getGlobalState() {
        return globalState;
    }
}
