package de.xenodev.events;

import de.xenodev.files.PlayerFilebuilder;
import de.xenodev.utils.DisplayBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(p);
        playerFilebuilder.addDeaths();
        DisplayBuilder.setDisplay(p);
    }

}
