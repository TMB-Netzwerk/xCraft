package de.xenodev.events;

import de.xenodev.commands.project.AdminmodeCMD;
import de.xenodev.files.PlayerFilebuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(p);
        String state = playerFilebuilder.getStatus();
        String color = playerFilebuilder.getColor();

        String msg = e.getMessage();
        msg = ChatColor.translateAlternateColorCodes('&', msg);

        if(!AdminmodeCMD.inAdminmode.contains(p)) {
            if(playerFilebuilder.getStatus().equalsIgnoreCase("")) {
                e.setFormat(color + p.getName() + " §8➥ §f" + msg);
            }else{
                e.setFormat(state + "§8| " + color + p.getName() + " §8➥ §f" + msg);
            }
        }else{
            e.setFormat("§c§lAdminmode §8- §4" + p.getName() + " §8➥ §c" + msg);
        }
    }

}
