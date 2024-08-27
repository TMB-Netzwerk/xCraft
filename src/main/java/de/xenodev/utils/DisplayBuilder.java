package de.xenodev.utils;

import de.xenodev.commands.project.AdminmodeCMD;
import de.xenodev.files.PlayerFilebuilder;
import org.bukkit.entity.Player;

public class DisplayBuilder {

    public static void setDisplay(Player p){
        PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(p);
        String state = playerFilebuilder.getStatus();
        Integer deaths = playerFilebuilder.getDeaths();
        String color = playerFilebuilder.getColor();

        if(!AdminmodeCMD.inAdminmode.contains(p)) {
            if(playerFilebuilder.getStatus().equalsIgnoreCase("")) {
                p.setPlayerListName(color + p.getName() + " §8[§6" + deaths + "§8]");
            }else{
                p.setPlayerListName(state + "§8| " + color + p.getName() + " §8[§6" + deaths + "§8]");
            }
        } else {
            p.setPlayerListName("§c§lAdminmode §8- §4§k" + p.getName() + " §8[§6" + deaths + "§8]");
        }
    }

}
