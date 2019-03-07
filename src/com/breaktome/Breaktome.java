package com.breaktome;

import com.breaktome.game.mod.BreaktomeModLoader;
import com.breaktome.game.mod.BreaktomeModService;
import com.breaktome.game.mod.IMod;
import com.jme3.app.SimpleApplication;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void simpleInitApp() {

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
