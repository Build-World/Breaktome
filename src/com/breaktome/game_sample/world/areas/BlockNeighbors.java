package com.breaktome.game_sample.world.areas;

import com.breaktome.game_sample.blocks.Block;
import com.breaktome.game_sample.world.interfaces.IArea;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class BlockNeighbors implements IArea {

    private Block target;
    private Block north;
    private Block south;
    private Block east;
    private Block west;
    private Block up;
    private Block down;

    public BlockNeighbors(Block target, Block north, Block south, Block east, Block west, Block up, Block down) {
        this.target = target;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.up = up;
        this.down = down;
    }

    public BlockNeighbors(Block target) {
        this.target = target;
    }

    public Block getNorth() {
        return north;
    }

    public void setNorth(Block north) {
        this.north = north;
    }

    public Block getSouth() {
        return south;
    }

    public void setSouth(Block south) {
        this.south = south;
    }

    public Block getEast() {
        return east;
    }

    public void setEast(Block east) {
        this.east = east;
    }

    public Block getWest() {
        return west;
    }

    public void setWest(Block west) {
        this.west = west;
    }

    public Block getUp() {
        return up;
    }

    public void setUp(Block up) {
        this.up = up;
    }

    public Block getDown() {
        return down;
    }

    public void setDown(Block down) {
        this.down = down;
    }

    public Block getTarget() {
        return target;
    }

    @Override
    public Vector2f getLocation2D() {
        return new Vector2f(target.getWorldPosition().x, target.getWorldPosition().z);
    }

    public Vector3f getLocation3D() {
        return new Vector3f(target.getWorldPosition());
    }
}
