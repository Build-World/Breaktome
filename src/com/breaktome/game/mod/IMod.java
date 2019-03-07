package com.breaktome.game.mod;

import com.breaktome.engine.interfaces.IKeyNameDescibe;
import com.breaktome.engine.interfaces.IVersion;

/**
 * IMod Interface which is not tied to any particular game library or framework
 */
public interface IMod extends IKeyNameDescibe, IVersion, IServerMod, IClientMod {

    // Fired for both server and client
    void onBoot() throws Exception;
    void onEnable() throws Exception;
    void onDisable() throws Exception;
    boolean isRunningOnServer();
    boolean isRunningOnClient();

    // Fired separately for server and client
    void onLoad() throws Exception;
    void onReady() throws Exception;
    void onPlay() throws Exception;
    void onUpdate(float tpf) throws Exception;

//    default ArrayList<DatabaseModel> getDatabaseModels()
//    {
//        return new ArrayList<>();
//    }
//    default ArrayList<Command> getCommands()
//    {
//        return new ArrayList<>();
//    }
//    default ArrayList<PostDatabaseLoad> getPostDatabaseLoads()
//    {
//        return new ArrayList<>();
//    }
//    default ArrayList<PlayerData> getPlayerDatas()
//    {
//        return new ArrayList<>();
//    }
//    default ArrayList<Recipe> getRecipes()
//    {
//        return new ArrayList<>();
//    }
//    default ArrayList<ScoreboardSection> getScoreboardSections()
//    {
//        return new ArrayList<>();
//    }
//    default ArrayList<PermissionNode> getPermissionNodes()
//    {
//        return new ArrayList<>();
//    }
//    default LinkedList<ConfigurationProperty> getConfigurationProperties()
//    {
//        return new LinkedList<>();
//    }
}
