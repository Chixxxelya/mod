package com.paradise.init;

import com.paradise.ParadiseDimensionMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class ParadiseDimensions {
    public static final RegistryKey<DimensionOptions> PARADISE_DIMENSION = RegistryKey.of(
        RegistryKeys.DIMENSION,
        new Identifier(ParadiseDimensionMod.MOD_ID, "paradise")
    );

    public static final RegistryKey<DimensionType> PARADISE_DIMENSION_TYPE = RegistryKey.of(
        RegistryKeys.DIMENSION_TYPE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "paradise_type")
    );

    public static void register() {
        // Dimension registration is handled through data files
    }

    public static void bootstrap(Registerable<DimensionOptions> registry) {
        // Registration is handled through JSON data files
    }
}