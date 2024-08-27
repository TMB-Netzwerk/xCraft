package de.xenodev.events;

import de.xenodev.commands.project.AdminmodeCMD;
import de.xenodev.files.PlayerFilebuilder;
import de.xenodev.utils.DisplayBuilder;
import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(p);
        if(xCraft.getInstance().getConfig().get("locations.Spawn") == null){
            if(p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getPrefix() + "§4Du musst den Spawn setzen");
                return;
            }
        }else{
            if(playerFilebuilder.getFirst().equals(false)) {
                p.teleport(xCraft.getInstance().getConfig().getLocation("locations.Spawn"));
                playerFilebuilder.setFirst();
                playerFilebuilder.setStatus("");
                playerFilebuilder.setColor("§7");
                playerFilebuilder.setDeaths();
                playerFilebuilder.setName();
            }else{
                playerFilebuilder.setName();
            }
        }
        DisplayBuilder.setDisplay(p);
        for(Player all :Bukkit.getOnlinePlayers()){
            if(AdminmodeCMD.inAdminmode.contains(all)){
                p.hidePlayer(xCraft.getInstance(), all);
            }else{
                p.showPlayer(xCraft.getInstance(), all);
            }
        }
        e.setJoinMessage(xCraft.getPrefix() + "§a" + p.getName() + " §7ist beigetreten");
        if(xCraft.getInstance().getConfig().get("locations.Elytra") == null){
            if(p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getPrefix() + "§4Du musst beide Elytra-Spots noch setzen");
            }
        }else{
        }
    }

}
