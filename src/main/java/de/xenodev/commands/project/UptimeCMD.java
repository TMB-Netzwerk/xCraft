package de.xenodev.commands.project;

import de.xenodev.files.StartFilebuilder;
import de.xenodev.xCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UptimeCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;

            String date = StartFilebuilder.getDatum();
            Integer seconds = StartFilebuilder.getSeconds();
            Integer minutes = StartFilebuilder.getMinutes();
            Integer hours = StartFilebuilder.getHours();
            Integer days = StartFilebuilder.getDays();
            Integer weeks = StartFilebuilder.getWeeks();
            Integer months = StartFilebuilder.getMonths();
            Integer years = StartFilebuilder.getYears();

            p.sendMessage(xCraft.getPrefix() + "§5§l" + xCraft.getInstance().getConfig().getString("projectname").replace("&", "§") + " §7ist am §6" + date + " §7gestartet");
            p.sendMessage(xCraft.getPrefix() + "§7Die laufende Zeit beträgt:");
            p.sendMessage(xCraft.getPrefix() + "§8§l- §3" + years + " §7Jahre");
            p.sendMessage(xCraft.getPrefix() + "§8§l- §3" + months + " §7Monate");
            p.sendMessage(xCraft.getPrefix() + "§8§l- §3" + weeks + " §7Wochen");
            p.sendMessage(xCraft.getPrefix() + "§8§l- §3" + days + " §7Tage");
            p.sendMessage(xCraft.getPrefix() + "§8§l- §3" + hours + " §7Stunden");
            p.sendMessage(xCraft.getPrefix() + "§8§l- §3" + minutes + " §7Minuten");
            p.sendMessage(xCraft.getPrefix() + "§8§l- §3" + seconds + " §7Sekunden");
        }else{
            sender.sendMessage(xCraft.getError() + "§cDu musst ein Spieler sein!");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        return stringList;
    }
}
