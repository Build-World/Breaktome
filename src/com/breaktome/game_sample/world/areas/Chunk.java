package com.breaktome.game_sample.world.areas;

import com.breaktome.game_sample.blocks.Block;
import com.breaktome.game_sample.world.generators.Biome;
import com.breaktome.game_sample.world.maps.types.HeightMap;
import com.breaktome.game_sample.world.interfaces.IArea;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import java.util.concurrent.ConcurrentHashMap;

public class Chunk implements IArea {

    public static final int size = 33; // must be a number divisble by renderChunksPerSide.

    public static final int renderChunksPerSide = 3;

    private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Block>>> map;

    private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, RenderChunk>> renderChunks;

    private Biome biome;

    private HeightMap heightMap;

    private Vector2f location;

    private Region region;

    public Chunk() {
        map = new ConcurrentHashMap<>();
        renderChunks = new ConcurrentHashMap<>();
        for (int i = 0; i < renderChunksPerSide; i++) {
            for (int j = 0; j < renderChunksPerSide; j++) {
                renderChunks.computeIfAbsent(i, k -> new ConcurrentHashMap<>()).put(j, new RenderChunk(this, new Vector2f(i, j)));
            }
        }
    }

    public Chunk(Region region) {
        this();
        this.region = region;
    }

    @Override
    public Vector2f getLocation2D() {
        return location;
    }

    public Region getRegion() {
        return region;
    }

    public void setLocation2D(Vector2f location) {
        this.location = location;
    }

    public HeightMap getHeightMap() {
        return heightMap;
    }

    public void setHeightMap(HeightMap heightMap) {
        this.heightMap = heightMap;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public void build() throws Exception {
        for (int i = 0; i < renderChunksPerSide; i++) {
            for (int j = 0; j < renderChunksPerSide; j++) {
                renderChunks.get(i).get(j).build();
            }
        }
    }

    public RenderChunk getRenderChunkContainingBlock(int x, int z) {
        int xOffset = x / RenderChunk.size;
        int zOffset = z / RenderChunk.size;

        return renderChunks.get(xOffset).get(zOffset);
    }

    public RenderChunk getRenderChunkContainingBlock(Block block) {
        return getRenderChunkContainingBlock((int) block.getChunkPosition().x, (int) block.getChunkPosition().z);
    }

    public void alertRenderChunk(int x, int z) {
        getRenderChunkContainingBlock(x, z).setRequiresRebuild(true);
    }

    public void alertRenderChunk(Block block) {
        alertRenderChunk((int) block.getChunkPosition().x, (int) block.getChunkPosition().z);
    }

    public void updateBlock(int x, int y, int z) throws Exception {
        // Alerts the applicable render chunk so that it knows it needs to rebuild itself.
        alertRenderChunk(x, z);
    }

    public void setBlock(int x, int y, int z, Block block) throws Exception {
        // Sets the block coordinate relative to this chunk
        block.setChunkPosition(x, y, z);

        // Sets the absolute position of this block for rendering purposes
        Vector2f offset = getAbsBlockOffset();
        block.setWorldPosition((int) offset.x + x, y, (int) offset.y + z);

        block.setChunk(this);

        // Puts a block into the map but if the ConcurrentHashMaps dont exist it will create it
        map.computeIfAbsent(x, k -> new ConcurrentHashMap<>()).computeIfAbsent(z, l -> new ConcurrentHashMap<>()).put(y, block);

        // Alerts the applicable render chunk so that it knows it needs to rebuild itself.
        alertRenderChunk(x, z);
    }

    public void setBlock(Vector3f coordinate, Block block) throws Exception {
        setBlock((int) coordinate.x, (int) coordinate.y, (int) coordinate.z, block);
    }

    public Block getBlock(int x, int y, int z) throws Exception {
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Block>> zMap = map.get(x);
        if(zMap == null)
        {
            return null;
        }
        ConcurrentHashMap<Integer, Block> yMap = zMap.get(z);
        if(yMap == null)
        {
            return null;
        }
        return yMap.get(y);
    }

    public Block getBlock(Vector3f coordinate) throws Exception {
        return getBlock((int) coordinate.x, (int) coordinate.y, (int) coordinate.z);
    }

    public void removeBlock(int x, int y, int z) throws Exception {
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Block>> zMap = map.get(x);
        if(zMap == null)
        {
            return;
        }
        ConcurrentHashMap<Integer, Block> yMap = zMap.get(z);
        if(yMap == null)
        {
            return;
        }
        yMap.remove(y);

        // Alerts the applicable render chunk so that it knows it needs to rebuild itself.
        alertRenderChunk(x, z);
    }

    public void removeBlock(Vector3f coordinate) throws Exception {
        removeBlock((int) coordinate.x, (int) coordinate.y, (int) coordinate.z);
    }

    public boolean isAir(int x, int y, int z) throws Exception {
        return getBlock(x, y, z) == null;
    }

    public boolean isAir(Vector3f coordinate) throws Exception {
        return isAir((int) coordinate.x, (int) coordinate.y, (int) coordinate.z);
    }
}
