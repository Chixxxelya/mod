package com.paradise.init;

import com.paradise.ParadiseDimensionMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.Structure;
import net.minecraft.util.Identifier;

public class ParadiseStructures {
    public static final RegistryKey<Structure> CASTLE = RegistryKey.of(
        RegistryKeys.STRUCTURE,
        new Identifier(ParadiseDimensionMod.MOD_ID, "castle")
    );

    public static void register() {
        // Registration handled through data files
    }

    public static void bootstrap(Registerable<Structure> registry) {
        // Registration is handled through JSON data files
    }
}