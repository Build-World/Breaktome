package com.breaktome.game_sample.world.areas;

import com.breaktome.engine.interfaces.IPersist;
import com.breaktome.game_sample.world.RegionState;
import com.breaktome.game_sample.world.interfaces.IArea;
import com.breaktome.game_sample.world.maps.types.AirQualityMap;
import com.breaktome.game_sample.world.maps.types.FertilityMap;
import com.breaktome.game_sample.world.maps.types.PercipitationMap;
import com.breaktome.game_sample.world.maps.types.TemperatureMap;
import com.jme3.math.Vector2f;

import java.util.concurrent.ConcurrentHashMap;

public class Region implements IArea, IPersist {

    public static final int size = 9;

    private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Chunk>> map;

    private RegionState state;

    // Location is the region offset
    private Vector2f location;

    private World world;

    private TemperatureMap temperatureMap;
    private PercipitationMap percipitationMap;
    private FertilityMap fertilityMap;
    private AirQualityMap airQualityMap;

    public static int sGetRegionLengthInBlocks()
    {
        return size * Chunk.size;
    }

    public static Vector2f sGetBlockOffset(Vector2f regionOffset)
    {
        return new Vector2f(regionOffset).mul(Region.size).mul(Chunk.size);
    }

    public Vector2f getBlockOffset()
    {
        return new Vector2f(location).mul(Region.size).mul(Chunk.size);
    }

    public Region(RegionState state) {
        map = new ConcurrentHashMap<>();
        this.state = state;
    }

    public Region(RegionState state, Vector2f location) {
        this(state);
        this.location = location;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public Vector2f getLocation2D() {
        return location;
    }

    public void setLocation2D(Vector2f location) {
        this.location = location;
    }

    public RegionState getState() {
        return state;
    }

    public void setState(RegionState state) {
        this.state = state;
    }

    public TemperatureMap getTemperatureMap() {
        return temperatureMap;
    }

    public void setTemperatureMap(TemperatureMap temperatureMap) {
        this.temperatureMap = temperatureMap;
    }

    public PercipitationMap getPercipitationMap() {
        return percipitationMap;
    }

    public void setPercipitationMap(PercipitationMap percipitationMap) {
        this.percipitationMap = percipitationMap;
    }

    public FertilityMap getFertilityMap() {
        return fertilityMap;
    }

    public void setFertilityMap(FertilityMap fertilityMap) {
        this.fertilityMap = fertilityMap;
    }

    public AirQualityMap getAirQualityMap() {
        return airQualityMap;
    }

    public void setAirQualityMap(AirQualityMap airQualityMap) {
        this.airQualityMap = airQualityMap;
    }

    public void setChunk(int x, int z, Chunk chunk) throws Exception
    {
        x = Math.abs(x);
        z = Math.abs(z);

        // Puts a block into the map but if the ConcurrentHashMaps dont exist it will create it
        chunk.setRegion(this);
        chunk.setLocation2D(new Vector2f(x,z));
        map.computeIfAbsent(x, k -> new ConcurrentHashMap<>()).put(z, chunk);
    }

    public void setChunk(Vector2f coordinate, Chunk chunk) throws Exception {
        setChunk((int)coordinate.x, (int)coordinate.y, chunk);
    }

    public Chunk getChunk(int x, int z) throws Exception
    {
        x = Math.abs(x);
        z = Math.abs(z);

        ConcurrentHashMap<Integer, Chunk> zMap = map.get(x);
        if(zMap == null)
        {
            return null;
        }
        return zMap.get(z);
    }

    public Chunk getChunk(Vector2f coordinate) throws Exception {
        return getChunk((int)coordinate.x, (int)coordinate.y);
    }

    public boolean isChunk(int x, int z) throws Exception
    {
        x = Math.abs(x);
        z = Math.abs(z);

        return getChunk(x, z) == null;
    }

    public boolean isChunk(Vector2f coordinate) throws Exception
    {
        return isChunk((int)coordinate.x, (int)coordinate.y);
    }

    public void load() throws Exception
    {
        if(map.size() > 0)
            throw new Exception("Cannot load a region which already has loaded data");

        // TODO: Load in region data from file

        state = RegionState.LOADED;
    }

    public void unload() throws Exception
    {
        state = RegionState.UNLOADED;

        // TODO: Save region to file

        map.clear();
    }

}
