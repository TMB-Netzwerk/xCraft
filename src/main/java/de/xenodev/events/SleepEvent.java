package de.xenodev.events;

import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.ArrayList;
import java.util.List;

public class SleepEvent implements Listener {

    private static List<Player> isSleeping = new ArrayList<>();

    @EventHandler
    public void onBedJoin(PlayerBedEnterEvent e){
        Player p = e.getPlayer();
        if(!p.getWorld().getName().equalsIgnoreCase("world")){
            e.setCancelled(true);
            return;
        }

        Location bed = e.getBed().getLocation();
        Location player = p.getLocation();
        double distance = bed.distance(player);

        if(!(distance <= 5)){
            p.sendMessage(xCraft.getPrefix() + "§7Du musst näher am Bett stehen");
            e.setCancelled(true);
            return;
        }

        if(p.getWorld().getTime() <= 12541L || p.getWorld().getTime() >= 23458L){
            p.sendMessage(xCraft.getPrefix() + "§7Du kannst nur Nachts schlafen");
            e.setCancelled(true);
            return;
        }

        isSleeping.add(p);
        addBed();
    }

    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent e){
        Player p = e.getPlayer();

        if(!p.getWorld().getName().equalsIgnoreCase("world")){
            e.setCancelled(true);
            return;
        }

        isSleeping.remove(p);
        removeBed();
    }

    private void addBed() {
        Integer allplayer = Bukkit.getWorld("world").getPlayers().size();
        Double minplayer = Double.valueOf(allplayer / 2);
        Integer sleepingplayer = isSleeping.size();

        if(minplayer > 1.0){
            if(sleepingplayer >= minplayer){
                for(Player all : Bukkit.getWorld("world").getPlayers()){
                    if(all.getWorld().getTime() >= 12541L || all.getWorld().getTime() <= 23458L){
                        if(isSleeping.contains(all)) {
                            isSleeping.remove(all);
                            all.sendMessage(xCraft.getPrefix() + "§a§lDu fühlst dich ausgeruht");
                        }else{
                            if(isSleeping.contains(all)){
                                isSleeping.remove(all);
                                return;
                            }
                            all.sendMessage(xCraft.getPrefix() + "§c§lDu hast nicht geschlafen");
                        }
                    }
                }
                isSleeping.clear();
                World world = Bukkit.getWorld("world");
                world.setTime(24000);
                world.setStorm(false);
                world.setThundering(false);
            }else{
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§7Es schlafen §e" + sleepingplayer +  " §7/ §e" + String.format("%0" ,minplayer) + " §7Spieler");
            }
        }else{
            for(Player all : Bukkit.getWorld("world").getPlayers()){
                if(all.getWorld().getTime() >= 12541L || all.getWorld().getTime() <= 23458L){
                    if(isSleeping.contains(all)) {
                        isSleeping.remove(all);
                        all.sendMessage(xCraft.getPrefix() + "§a§lDu fühlst dich ausgeruht");
                    }else{
                        if(isSleeping.contains(all)){
                            isSleeping.remove(all);
                            return;
                        }
                        all.sendMessage(xCraft.getPrefix() + "§c§lDu hast nicht geschlafen");
                    }
                }
            }
            isSleeping.clear();
            World world = Bukkit.getWorld("world");
            world.setTime(24000);
            world.setStorm(false);
            world.setThundering(false);
        }
    }

    private void removeBed(){
        Integer allplayer = Bukkit.getWorld("world").getPlayers().size();
        Double minplayer = Double.valueOf(allplayer / 2);
        Integer sleepingplayer = isSleeping.size();

        if(sleepingplayer >= 1){
            Bukkit.broadcastMessage(xCraft.getPrefix() + "§7Es schlafen §e" + sleepingplayer +  " §7/ §e" + String.format("%0" ,minplayer) + " §7Spieler");
        }
    }

}
