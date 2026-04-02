# Paradise Dimension Mod

A Minecraft Fabric mod that adds a beautiful paradise dimension located 1000 blocks above the world.

## Features

- **Floating Islands**: Beautiful islands at Y=1000 with varying sizes
- **Tall Trees**: Gorgeous trees with lush foliage
- **Castles with Loot**: Small castles containing valuable items (diamonds, gold, emeralds, iron, food, arrows)
- **Lush Vegetation**: Flowers, grass, and greenery everywhere
- **Peaceful Mobs**: Rabbits, parrots, and bats spawn in this dimension
- **Easy Access**: Simply fly up with elytra above Y=999 to enter
- **Easy Return**: Fall below Y=950 to return to the overworld

## How to Enter

1. Equip elytra
2. Fly straight up into the sky
3. When you reach Y=999 or higher, you'll be teleported to the Paradise Dimension
4. To return, simply fall below Y=950

## Installation

### For TLauncher

1. Download and install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.20.1
2. Download [Fabric API](https://modrinth.com/mod/fabric-api) for Minecraft 1.20.1
3. Build the mod using `gradle build` or download the pre-built jar
4. Place the following files in your `.minecraft/mods` folder:
   - `paradise-dimension-1.0.0.jar` (this mod)
   - `fabric-api-*.jar` (Fabric API)
5. Launch Minecraft with the Fabric 1.20.1 profile in TLauncher

### Building from Source

```bash
# Clone or download the source
cd minecrafmod

# Build the mod
./gradlew build

# The built jar will be in build/libs/
```

## Requirements

- Minecraft 1.20.1
- Fabric Loader 0.14.21 or higher
- Fabric API 0.83.0 or higher
- Java 17 or higher

## Dimension Details

- **Y Level**: Islands generate at Y=1000
- **Sky Color**: Beautiful sunset orange (#FFE0B2)
- **Water Color**: Crystal blue (#4FC3F7)
- **Fog Color**: Warm cream (#FFF8E1)
- **Grass Color**: Vibrant green (#81C784)
- **Foliage Color**: Lush green (#66BB6A)
- **Time**: Fixed at daytime (6000 ticks)

## Mobs

The paradise dimension is peaceful with only passive mobs:
- Rabbits (common)
- Parrots (uncommon)
- Bats (rare)

No hostile mobs spawn in this dimension!

## Loot

Castles contain chests with:
- Diamonds (1-5)
- Gold Ingots (3-10)
- Emeralds (2-8)
- Iron Ingots (5-15)
- Bread (3-10)
- Arrows (10-30)

## License

MIT License - See LICENSE file for details

## Support

If you encounter any issues, please check:
1. You're using Minecraft 1.20.1
2. Fabric API is installed
3. All mod files are in the mods folder
4. You're using the Fabric profile in TLauncher