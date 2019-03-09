package com.breaktome.game.world.advanced;

import com.breaktome.engine.interfaces.IRepresentation;
import com.breaktome.game.blocks.BlockRegistry;
import com.breaktome.game.world.Chunk;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Advanced Chunk Wrapper
 * Adds more features to a chunk at the cost of performance and memory size
 * Only use this wrapper when nessecary
 */
public class AdvancedChunk implements IRepresentation {

    public static AdvancedChunk wrap(Chunk chunk) {
        return new AdvancedChunk(chunk);
    }

    private Chunk chunk;

    public AdvancedChunk(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }

    /**
     * Randomly fills the chunk with registered blocks and air
     *
     * @param blockRegistry
     */
    public void randomize(BlockRegistry blockRegistry) {
        if(blockRegistry.getSize() < 1)
        {
            return;
        }

        float weight = 2;
        for (int i = 0; i < Chunk.size; i++) {
            for (int j = 0; j < Chunk.size; j++) {
                for (int k = 0; k < Chunk.size; k++) {
                    int rand = ThreadLocalRandom.current().nextInt(0, (int)(blockRegistry.getSize() * weight));
                    if(blockRegistry.getSize() > rand)
                    {
                        chunk.setBlock(i,j,k,rand);
                    }
                }
            }
        }
    }

    @Override
    public String __repr__() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Chunk.size; i++) {
            sb.append("X: ").append(i).append('\n');
            for (int j = 0; j < Chunk.size; j++) {
                for (int k = 0; k < Chunk.size; k++) {
                    int data = chunk.getData()[i][j][k];
                    if (data < 0) {
                        sb.append(" -- ");
                    } else {
                        sb.append(String.format("%04d", data));
                    }
                    sb.append(" ");

                }
                sb.append('\n');
            }

            sb.append('\n');
        }
        return sb.toString();
    }
}
