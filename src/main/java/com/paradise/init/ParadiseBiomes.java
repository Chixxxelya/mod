package com.paradise.init;

import com.paradise.ParadiseDimensionMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.MusicType;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class ParadiseBiomes {
    public static final RegistryKey<Biome> PARADISE_ISLANDS = RegistryKey.of(
        RegistryKeys.BIOME,
        new Identifier(ParadiseDimensionMod.MOD_ID, "paradise_islands")
    );

    public static void register() {
        // Registration handled through data files
    }

    public static void bootstrap(Registerable<Biome> registry) {
        // Registration is handled through JSON data files
    }
}