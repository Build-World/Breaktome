package com.breaktome.game.registries;

import com.breaktome.game.blocks.BlockLoader;

import java.util.HashMap;

public class RegistryService {

    private HashMap<Class<? extends IRegistry>, IRegistry> registries;

    private BlockLoader blockLoader;

    public RegistryService() {

        blockLoader = new BlockLoader();
        registries = new HashMap<>();

    }

    public BlockLoader getBlockLoader() {
        return blockLoader;
    }

    public HashMap<Class<? extends IRegistry>, IRegistry> getRegistries() {
        return registries;
    }

    public IRegistry getRegistry(Class<? extends IRegistry> registry)
    {
        return registries.get(registry);
    }

    public void registerRegistry(IRegistry registry)
    {
        registries.put(registry.getClass(), registry);
    }

}
