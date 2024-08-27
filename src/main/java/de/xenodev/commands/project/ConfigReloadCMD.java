package de.xenodev.commands.project;

import de.xenodev.xCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfigReloadCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }

            if(args.length == 0){
                p.sendMessage(xCraft.getPrefix() + "§7/configreload <config, backpack>");
            }else{
                if(args[0].equalsIgnoreCase("config")){
                    xCraft.getInstance().reloadConfig();
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast die Config aktualisiert");
                }else if(args[0].equalsIgnoreCase("backpack")){
                    p.sendMessage(xCraft.getError() + "§cDer Backpack wurde erstmal deaktiviert!");
                }
            }
        }else{
            if(args.length == 0){
                sender.sendMessage(xCraft.getPrefix() + "§7/configreload <config, backpack>");
            }else{
                if(args[0].equalsIgnoreCase("config")){
                    xCraft.getInstance().reloadConfig();
                    sender.sendMessage(xCraft.getPrefix() + "§7Du hast die Config aktualisiert");
                }else if(args[0].equalsIgnoreCase("backpack")){
                    sender.sendMessage(xCraft.getError() + "§cDer Backpack wurde erstmal deaktiviert!");
                }
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        if(args.length == 1){
            stringList.add("config");
            stringList.add("backpack");
        }

        return stringList;
    }
}
