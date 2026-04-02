package com.paradise.init;

import com.paradise.ParadiseDimensionMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ParadiseFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> TALL_TREE = RegistryKey.of(
        RegistryKeys.CONFIGURED_FEATURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "tall_tree")
    );
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_PATCH = RegistryKey.of(
        RegistryKeys.CONFIGURED_FEATURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "flower_patch")
    );
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> GRASS_PATCH = RegistryKey.of(
        RegistryKeys.CONFIGURED_FEATURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "grass_patch")
    );

    public static final RegistryKey<PlacedFeature> PLACED_TALL_TREE = RegistryKey.of(
        RegistryKeys.PLACED_FEATURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "placed_tall_tree")
    );
    
    public static final RegistryKey<PlacedFeature> PLACED_FLOWER_PATCH = RegistryKey.of(
        RegistryKeys.PLACED_FEATURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "placed_flower_patch")
    );
    
    public static final RegistryKey<PlacedFeature> PLACED_GRASS_PATCH = RegistryKey.of(
        RegistryKeys.PLACED_FEATURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "placed_grass_patch")
    );

    public static void register() {
    }
}
