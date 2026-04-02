package com.paradise;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParadiseDimensionMod implements ModInitializer {
    public static final String MOD_ID = "paradise-dimension";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Paradise Dimension mod initialized!");
        
        // Register chunk generator serializer
        Registry.register(RegistryKeys.CHUNK_GENERATOR, 
            new Identifier(MOD_ID, "paradise_chunk_generator"),
            com.paradise.world.ParadiseChunkGenerator.CODEC);
        
        LOGGER.info("Paradise Dimension - All registrations complete!");
    }
}