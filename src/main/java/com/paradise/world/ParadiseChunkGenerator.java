package com.paradise.world;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkGenerationContext;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

public class ParadiseChunkGenerator extends ChunkGenerator {
    public static final MapCodec<ParadiseChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Biome.REGISTRY_CODEC.fieldOf("biome").forGetter(ParadiseChunkGenerator::getBiomeSource)
        ).apply(instance, ParadiseChunkGenerator::new)
    );

    private final RegistryEntry<Biome> biomeSource;

    public ParadiseChunkGenerator(RegistryEntry<Biome> biome) {
        super(biome);
        this.biomeSource = biome;
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public RegistryEntry<Biome> getBiomeSource() {
        return biomeSource;
    }

    @Override
    public void carve(Chunk chunk, ChunkGenerationContext context) {
        // No carving needed for paradise dimension
    }

    @Override
    public void buildSurface(Chunk chunk, ChunkGenerationContext context) {
        // Surface building handled in generateBlocks
    }

    @Override
    public void generateBlocks(Chunk chunk, ChunkGenerationContext context) {
        HeightLimitView heightLimitView = context.chunk();
        int minHeight = heightLimitView.getBottomY();
        int maxHeight = heightLimitView.getTopY();
        
        // Generate islands at Y=1000
        int islandBaseY = 1000;
        
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunk.getPos().getStartX() + x;
                int worldZ = chunk.getPos().getStartZ() + z;
                
                // Create island shape using noise
                double noise = getIslandNoise(worldX, worldZ);
                int islandHeight = (int) (noise * 20) + 5; // 5-25 blocks thick
                
                if (noise > 0.3) { // Only generate where noise is high enough
                    // Generate island from bottom to top
                    for (int y = islandBaseY; y < islandBaseY + islandHeight; y++) {
                        if (y >= minHeight && y < maxHeight) {
                            BlockState state;
                            if (y == islandBaseY + islandHeight - 1) {
                                // Top layer - grass
                                state = Blocks.GRASS_BLOCK.getDefaultState();
                            } else if (y > islandBaseY + islandHeight - 4) {
                                // Upper layers - dirt
                                state = Blocks.DIRT.getDefaultState();
                            } else {
                                // Lower layers - stone
                                state = Blocks.STONE.getDefaultState();
                            }
                            chunk.setBlockState(x, y - minHeight, z, state, false);
                        }
                    }
                }
            }
        }
    }

    private double getIslandNoise(int x, int z) {
        // Create varied island shapes using multiple sine waves
        double scale1 = 0.008;
        double scale2 = 0.02;
        double scale3 = 0.05;
        
        double noise1 = Math.sin(x * scale1 + 1.0) * Math.cos(z * scale1);
        double noise2 = Math.sin(x * scale2 + 2.0) * Math.cos(z * scale2 + 1.0);
        double noise3 = Math.sin(x * scale3 + 3.0) * Math.cos(z * scale3 + 2.0);
        
        // Combine noises with different weights
        double combined = (noise1 * 0.5 + noise2 * 0.3 + noise3 * 0.2 + 1.0) * 0.5;
        
        // Add some randomness based on position
        double randomFactor = Math.sin(x * 12.9898 + z * 78.233) * 43758.5453;
        randomFactor = (randomFactor - Math.floor(randomFactor)) * 0.1;
        
        return MathHelper.clamp(combined + randomFactor, 0.0, 1.0);
    }

    @Override
    public int getSeaLevel() {
        return 63; // Standard sea level
    }

    @Override
    public int getMinimumY() {
        return -64;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world) {
        // Return height for island surface
        double noise = getIslandNoise(x, z);
        if (noise > 0.3) {
            return 1000 + (int) (noise * 20) + 5;
        }
        return 1000; // Base height for void areas
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world) {
        // Return column sample for debugging
        return new VerticalBlockSample(1000, Blocks.GRASS_BLOCK.getDefaultState());
    }
}