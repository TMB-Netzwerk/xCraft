package de.xenodev.events;

import de.xenodev.xCraft;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpawnSafeEvent implements Listener {

    private final int spawnRadius = xCraft.getInstance().getConfig().getInt("spawn-radius");

    @EventHandler
    public void handleDamage(EntityDamageByEntityEvent event){
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player)event.getEntity();
        if(!isInSpawnRadius(player)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void handlePlace(BlockPlaceEvent event){
        if(event.getPlayer().hasPermission("tmb.admin")) return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if(!isInSpawnRadius(event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void handleBreak(BlockBreakEvent event){
        if(event.getPlayer().hasPermission("tmb.admin")) return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if(!isInSpawnRadius(event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void handleInteract(PlayerInteractEvent event){
        if(event.getPlayer().hasPermission("tmb.admin")) return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if(!isInSpawnRadius(event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void handleFood(FoodLevelChangeEvent event){
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player)event.getEntity();
        if(!isInSpawnRadius(player)) return;
        player.setHealth(20D);
        player.setFoodLevel(10);
        event.setCancelled(true);
    }

    private boolean isInSpawnRadius(Player player){
        if(!player.getWorld().getName().equals("world")) return false;
        return player.getWorld().getSpawnLocation().distance(player.getLocation()) <= spawnRadius;
    }
}
