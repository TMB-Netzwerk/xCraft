package de.xenodev.commands.usefull;

import de.xenodev.files.PlayerFilebuilder;
import de.xenodev.utils.DisplayBuilder;
import de.xenodev.xCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ColorCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 1) {
                if(args[0].equalsIgnoreCase("reset") && args.length == 1){
                    PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(p);
                    playerFilebuilder.setColor("§7");
                    DisplayBuilder.setDisplay(p);
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast deine Farbe zurückgesetzt");
                }else{
                    if(args[0].contains("k") || !args[0].contains("&")){
                        p.sendMessage(xCraft.getError() + "§cDu hast falsche Codes verwendet!");
                        return true;
                    }
                    PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(p);
                    playerFilebuilder.setColor(args[0]);
                    DisplayBuilder.setDisplay(p);
                    p.sendMessage(xCraft.getPrefix() + "§7Deine neue Farbe ist: " + args[0].replace("&", "§") + p.getName());
                }
            } else {
                p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/color <color, reset>");
            }
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
