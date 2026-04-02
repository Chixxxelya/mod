package com.paradise.world;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class ParadiseChunkGeneratorSerializer {
    public static final MapCodec<ParadiseChunkGenerator> CODEC = ParadiseChunkGenerator.CODEC;

    public static void register(Registry<ChunkGenerator> registry) {
        Registry.register(registry, 
            new net.minecraft.util.Identifier("paradise-dimension", "paradise_chunk_generator"),
            CODEC);
    }
}