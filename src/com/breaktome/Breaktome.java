package com.breaktome;

import com.breaktome.game.mod.BreaktomeModLoader;
import com.breaktome.game.mod.BreaktomeModService;
import com.breaktome.game.mod.IMod;
import com.breaktome.game.network.messages.BlockRegistryMessage;
import com.breaktome.game.network.messages.ChunkMessage;
import com.breaktome.game.network.messages.PlayersMessage;
import com.breaktome.game.registries.Registries;
import com.breaktome.mod.blocks.BlankBlock;
import com.breaktome.mod.blocks.GrassBlock;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.network.serializing.Serializer;


/**
 *
 * TODO: Implement event system with a global event listener registry
 *   - add an interface for each event group: block, network, etc.
 *   - ensure that event listeners can have a priority set to them
 *   - everytime an event listener is registered by the mod loader, resort listeners into separate sorted lists by event group type
 *   - have one array list for network listeners, one for block listeners etc.
 *   - trigger events on playermessage, blockregistrymessage, onConnection, onDisconnection
 *   - an event listener can be multiple types, just implement multiple event group type interfaces
 *   - Create an interface for all event types
 *
 */
abstract public class Breaktome extends SimpleApplication implements IMod {
    /** Changeable parameters */
    public static String basePath = "D:\\Programming\\Projects\\Build-World";
    public static String version = "0.0.1";

    /** Dont change these unless you know what you're doing */
    public static String modsPath = basePath + "/mods";
    public static String assetsPath = basePath + "/assets";

    /** Application Variables */
    private String name = "Breaktome";
    private String key = "breaktome";
    private String description = "Breaktome is a game framework";

    private BreaktomeModLoader modLoader;
    private Registries registries;

    abstract boolean isServer();

    public static void setBasePath(String basePath) {
        Breaktome.basePath = basePath;
    }

    public static void setVersion(String version) {
        Breaktome.version = version;
    }

    public static void setModsPath(String modsPath) {
        Breaktome.modsPath = modsPath;
    }

    public static void setAssetsPath(String assetsPath) {
        Breaktome.assetsPath = assetsPath;
    }

    public static String getBasePath() {
        return basePath;
    }

    public static String getModsPath() {
        return modsPath;
    }

    public static String getAssetsPath() {
        return assetsPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Registries getRegistries() {
        return registries;
    }

    public void registerSerializers() {
        Serializer.registerClass(PlayersMessage.class);
        Serializer.registerClass(BlockRegistryMessage.class);
        Serializer.registerClass(ChunkMessage.class);
    }

    @Override
    public void simpleInitApp() {

        registerSerializers();

        registries = new Registries();

        new BreaktomeModService();

        modLoader = new BreaktomeModLoader(this, Breaktome.modsPath);

        try {
            onBoot();
            modLoader.onBoot();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Caught at Breaktome.java");
        }

        try {
            onLoad();
            modLoader.onLoad();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Caught at Breaktome.java");
        }

        try {
            onReady();
            modLoader.onReady();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Caught at Breaktome.java");
        }

        try {
            onPlay();
            modLoader.onPlay();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Caught at Breaktome.java");
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        try {
            onUpdate(tpf);
            modLoader.onUpdate(tpf);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Caught at Breaktome.java");
        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void onLoad() throws Exception {

        registries.getBlockLoader().register(new BlankBlock());
        registries.getBlockLoader().register(new GrassBlock());

        if(isRunningOnClient())
        {
            onClientLoad();
        } else {
            onServerLoad();
        }
    }

    @Override
    public void onReady() throws Exception {
        if(isRunningOnClient())
        {
            onClientReady();
        } else {
            onServerReady();
        }
    }

    @Override
    public void onPlay() throws Exception {
        if(isRunningOnClient())
        {
            onClientPlay();
        } else {
            onServerPlay();
        }
    }

    @Override
    public void onUpdate(float tpf) throws Exception {
        if(isRunningOnClient())
        {
            onClientUpdate(tpf);
        } else {
            onServerUpdate(tpf);
        }
    }

    @Override
    public void registerAssetLocator(String path) throws Exception {
        assetManager.registerLocator(path, FileLocator.class);
    }

    @Override
    public void onEnable() throws Exception {

    }

    @Override
    public void onDisable() throws Exception {

    }

    @Override
    public boolean isRunningOnServer() {
        return isServer();
    }

    @Override
    public boolean isRunningOnClient() {
        return !isServer();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void onClientLoad() throws Exception {

    }

    @Override
    public void onClientReady() throws Exception {

    }

    @Override
    public void onClientPlay() throws Exception {

    }

    @Override
    public void onClientUpdate(float tpf) throws Exception {

    }

    @Override
    public void onServerLoad() throws Exception {

    }

    @Override
    public void onServerReady() throws Exception {

    }

    @Override
    public void onServerPlay() throws Exception {

    }

    @Override
    public void onServerUpdate(float tpf) throws Exception {

    }
}
