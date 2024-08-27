package de.xenodev.events;

import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public class MOTDEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void ListPing(ServerListPingEvent event) {
        FileConfiguration config = xCraft.getInstance().getConfig();
        List<String> motdmessages = config.getStringList("MOTD-Messages");
        Random r = new Random();
        String motdmessages2 = motdmessages.get(r.nextInt(motdmessages.size()));
        while (motdmessages2.indexOf("%rc%") != -1) {
            String[] Colours = {
                    "a", "b", "c", "d", "e", "f", "0", "1", "2", "3",
                    "4", "5", "6", "7", "8", "9" };
            Random r2 = new Random();
            String randcol = Colours[r2.nextInt(Colours.length)];
            randcol = "&" + randcol;
            randcol = motdmessages2.replaceFirst("%rc%", randcol);
            motdmessages2 = ChatColor.translateAlternateColorCodes('&', randcol);
        }
        while (motdmessages2.indexOf("%mcver%") != -1) {
            String mcver1 = motdmessages2.replaceAll("%mcver%", Bukkit.getBukkitVersion());
            String[] parts = mcver1.split("-");
            motdmessages2 = parts[0];
        }
        while (motdmessages2.indexOf("%bukkitver%") != -1)
            motdmessages2 = motdmessages2.replaceAll("%bukkitver%", Bukkit.getBukkitVersion());
        while (motdmessages2.indexOf("%max%") != -1)
            motdmessages2 = motdmessages2.replaceAll("%max%", String.valueOf(event.getMaxPlayers()));
        while (motdmessages2.indexOf("%online%") != -1)
            motdmessages2 = motdmessages2.replaceAll("%online%", String.valueOf(event.getNumPlayers()));
        while (motdmessages2.indexOf("%ip%") != -1) {
            String ip1 = motdmessages2.replaceAll("%ip%", String.valueOf(event.getAddress()));
            motdmessages2 = ip1.replaceAll("/", "");
        }
        while (motdmessages2.indexOf("&") != -1)
            motdmessages2 = ChatColor.translateAlternateColorCodes('&', motdmessages2);
        event.setMotd(motdmessages2);
    }

}
