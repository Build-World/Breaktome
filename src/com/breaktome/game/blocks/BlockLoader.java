package com.breaktome.game.blocks;

import com.google.common.collect.HashBiMap;

import java.util.HashMap;

public class BlockLoader {

    private HashMap<String, Block> loadedBlocks;
    private HashBiMap<Integer, Block> registeredBlocks;
    private BlockRegistry blockRegistry;

    public BlockLoader() {
        loadedBlocks = new HashMap<>();
        registeredBlocks = HashBiMap.create();
        blockRegistry = new BlockRegistry();
    }

    public HashMap<String, Block> getLoadedBlocks() {
        return loadedBlocks;
    }

    public HashBiMap<Integer, Block> getRegisteredBlocks() {
        return registeredBlocks;
    }

    public BlockRegistry getBlockRegistry() {
        return blockRegistry;
    }

    public void setBlockRegistry(BlockRegistry blockRegistry) {
        this.blockRegistry = blockRegistry;
        try {
            sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBlockRegistry(String[] blockRegistry) {
        this.blockRegistry = new BlockRegistry();
        this.blockRegistry.importOrderedNames(blockRegistry);
        try {
            sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int register(Block block) {
        int size = blockRegistry.register(block);
        loadedBlocks.put(block.getFQN(), block);
        registeredBlocks.put(size, block);
        return size;
    }

    public void sync() throws Exception {
        registeredBlocks.clear();
        for (int i = 0; i < blockRegistry.getSize(); i++) {
            String registeredFQN = blockRegistry.lookup(i);

            Block block = loadedBlocks.get(registeredFQN);
            if(block == null)
            {
                throw new Exception("No locally loaded block matching block registry");
            }

            registeredBlocks.put(i, block);
        }
    }

    public int getSize() {
        return blockRegistry.getSize();
    }

    public Block lookup(int id) {
        return registeredBlocks.get(id);
    }

    public Block lookup(String id) {
        return registeredBlocks.get(blockRegistry.lookup(id));
    }
}
