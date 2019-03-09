package com.breaktome.game.world;

public class Chunk {
    public static final int size = 16;

    private int[][][] data;

    public Chunk(int[][][] data) {
        this.data = data;
    }

    public Chunk() {
        data = new int[size][size][size];

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                for(int k = 0; k < size; k++)
                {
                    data[i][j][k] = -1;
                }
            }
        }
    }

    public int[][][] getData() {
        return data;
    }

    public int getBlock(int x, int y, int z)
    {
        return data[x][y][z];
    }

    public boolean isAir(int x, int y, int z)
    {
        return data[x][y][z] == -1;
    }

    public void setBlock(int x, int y, int z, int block)
    {
        data[x][y][z] = block;
    }
}
