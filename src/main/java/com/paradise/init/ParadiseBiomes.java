package com.paradise.init;

import com.paradise.ParadiseDimensionMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ParadiseBiomes {
    public static final RegistryKey<Biome> PARADISE_ISLANDS = RegistryKey.of(
        RegistryKeys.BIOME,
        new Identifier(ParadiseDimensionMod.MOD_ID, "paradise_islands")
    );

    public static void register() {
    }
}
