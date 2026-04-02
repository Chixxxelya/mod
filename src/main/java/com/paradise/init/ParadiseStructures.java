package com.paradise.init;

import com.paradise.ParadiseDimensionMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ParadiseStructures {
    public static final RegistryKey<Object> CASTLE = RegistryKey.of(
        RegistryKeys.STRUCTURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "castle")
    );

    public static void register() {
    }
}
