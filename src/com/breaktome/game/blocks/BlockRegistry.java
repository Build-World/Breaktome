package com.breaktome.game.blocks;

import com.breaktome.engine.interfaces.IRepresentation;
import com.google.common.collect.HashBiMap;

public class BlockRegistry implements IRepresentation {

    private HashBiMap<String, Integer> identifiers;
    private int size;

    public BlockRegistry() {
        identifiers = HashBiMap.create();
        size = 0;
    }

    public HashBiMap<String, Integer> getIdentifiers() {
        return identifiers;
    }

    public int getSize() {
        return size;
    }

    public String lookup(int id) {
        return identifiers.inverse().get(id);
    }

    public int lookup(String id) {
        return identifiers.get(id);
    }

    public int register(Block block) {
        identifiers.put(block.getFQN(), size);
        size++;
        return size - 1;
    }

    public String[] exportOrderedNames()
    {
        String[] names = new String[size];
        for(int i = 0; i < size; i++)
        {
            names[i] = identifiers.inverse().get(i);
        }
        return names;
    }

    public void importOrderedNames(String[] names)
    {
        for(String name : names)
        {
            identifiers.put(name, size);
            size++;
        }
    }

    @Override
    public String __repr__() {
        StringBuilder registry = new StringBuilder();
        for(int i = 0; i < size; i++)
        {
            registry.append(i).append(": ").append(identifiers.inverse().get(i)).append('\n');
        }
        return registry.toString();
    }
}