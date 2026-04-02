package com.paradise.world;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

public class ParadiseChunkGenerator extends ChunkGenerator {
    public static final MapCodec<ParadiseChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Biome.REGISTRY_CODEC.fieldOf("biome").forGetter(ParadiseChunkGenerator::getBiome)
        ).apply(instance, ParadiseChunkGenerator::new)
    );

    private final RegistryEntry<Biome> biome;

    public ParadiseChunkGenerator(RegistryEntry<Biome> biome) {
        super(biome);
        this.biome = biome;
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(Chunk chunk) {
    }

    @Override
    public void buildSurface(Chunk chunk) {
    }

    @Override
    public void generateBlocks(Chunk chunk) {
        HeightLimitView heightLimitView = chunk;
        int minHeight = heightLimitView.getBottomY();
        int maxHeight = heightLimitView.getTopY();
        
        int islandBaseY = 1000;
        
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunk.getPos().getStartX() + x;
                int worldZ = chunk.getPos().getStartZ() + z;
                
                double noise = getIslandNoise(worldX, worldZ);
                int islandHeight = (int) (noise * 20) + 5;
                
                if (noise > 0.3) {
                    for (int y = islandBaseY; y < islandBaseY + islandHeight; y++) {
                        if (y >= minHeight && y < maxHeight) {
                            BlockState state;
                            if (y == islandBaseY + islandHeight - 1) {
                                state = Blocks.GRASS_BLOCK.getDefaultState();
                            } else if (y > islandBaseY + islandHeight - 4) {
                                state = Blocks.DIRT.getDefaultState();
                            } else {
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
        double scale1 = 0.008;
        double scale2 = 0.02;
        double scale3 = 0.05;
        
        double noise1 = Math.sin(x * scale1 + 1.0) * Math.cos(z * scale1);
        double noise2 = Math.sin(x * scale2 + 2.0) * Math.cos(z * scale2 + 1.0);
        double noise3 = Math.sin(x * scale3 + 3.0) * Math.cos(z * scale3 + 2.0);
        
        double combined = (noise1 * 0.5 + noise2 * 0.3 + noise3 * 0.2 + 1.0) * 0.5;
        
        double randomFactor = Math.sin(x * 12.9898 + z * 78.233) * 43758.5453;
        randomFactor = (randomFactor - Math.floor(randomFactor)) * 0.1;
        
        return MathHelper.clamp(combined + randomFactor, 0.0, 1.0);
    }

    @Override
    public int getSeaLevel() {
        return 63;
    }

    @Override
    public int getMinimumY() {
        return -64;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world) {
        double noise = getIslandNoise(x, z);
        if (noise > 0.3) {
            return 1000 + (int) (noise * 20) + 5;
        }
        return 1000;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world) {
        return new VerticalBlockSample(1000, Blocks.GRASS_BLOCK.getDefaultState());
    }
}
