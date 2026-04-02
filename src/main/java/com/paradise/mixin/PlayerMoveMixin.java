package com.paradise.mixin;

import com.paradise.ParadiseDimensionMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMoveMixin {
    
    @Inject(method = "tickMovement", at = @At("TAIL"))
    private void checkParadiseEntry(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        
        if (player.getWorld().isClient()) {
            return; // Only run on server
        }
        
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
        
        // Check if player is flying with elytra and above Y=999
        if (serverPlayer.isFallFlying() && serverPlayer.getY() > 999) {
            ServerWorld overworld = serverPlayer.getServer().getWorld(ServerWorld.OVERWORLD);
            
            if (overworld != null && serverPlayer.getWorld().getRegistryKey() == ServerWorld.OVERWORLD) {
                // Teleport player to paradise dimension
                ServerWorld paradiseWorld = serverPlayer.getServer().getWorld(
                    com.paradise.init.ParadiseDimensions.PARADISE_DIMENSION
                );
                
                if (paradiseWorld != null) {
                    // Keep player's position but in paradise dimension
                    Vec3d position = serverPlayer.getPos();
                    
                    TeleportTarget teleportTarget = new TeleportTarget(
                        paradiseWorld,
                        position,
                        serverPlayer.getVelocity(),
                        serverPlayer.getYaw(),
                        serverPlayer.getPitch()
                    );
                    
                    serverPlayer.teleportTo(teleportTarget);
                    serverPlayer.sendMessage(net.minecraft.text.Text.literal("Welcome to Paradise Dimension!"), true);
                }
            }
        }
        
        // Check if player is falling below Y=950 in paradise dimension
        if (serverPlayer.getY() < 950) {
            ServerWorld paradiseWorld = serverPlayer.getServer().getWorld(
                com.paradise.init.ParadiseDimensions.PARADISE_DIMENSION
            );
            
            if (paradiseWorld != null && serverPlayer.getWorld().getRegistryKey() == paradiseWorld.getRegistryKey()) {
                // Teleport player back to overworld
                ServerWorld overworld = serverPlayer.getServer().getWorld(ServerWorld.OVERWORLD);
                
                if (overworld != null) {
                    Vec3d position = new Vec3d(serverPlayer.getX(), 100, serverPlayer.getZ());
                    
                    TeleportTarget teleportTarget = new TeleportTarget(
                        overworld,
                        position,
                        serverPlayer.getVelocity(),
                        serverPlayer.getYaw(),
                        serverPlayer.getPitch()
                    );
                    
                    serverPlayer.teleportTo(teleportTarget);
                    serverPlayer.sendMessage(net.minecraft.text.Text.literal("Returning to Overworld!"), true);
                }
            }
        }
    }
}