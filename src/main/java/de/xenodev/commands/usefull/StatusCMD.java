package de.xenodev.commands.usefull;

import de.xenodev.files.PlayerFilebuilder;
import de.xenodev.utils.DisplayBuilder;
import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StatusCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(p);
            if (args.length >= 1) {
                if(args[0].equalsIgnoreCase("reset") && args.length == 1){
                    playerFilebuilder.setStatus("");
                    DisplayBuilder.setDisplay(p);
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast deinen Status gelöscht");
                }else if(args[0].equalsIgnoreCase("update") && args.length == 1){
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        DisplayBuilder.setDisplay(all);
                    }
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast die Tablist aktualisiert");
                }else if(args[0].equalsIgnoreCase("copy") && args.length == 2){
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    PlayerFilebuilder targetBuilder = new PlayerFilebuilder(offlinePlayer);
                    Bukkit.getServer().dispatchCommand(p, "status " + targetBuilder.getStatus());
                }else{
                    String state = "";
                    for(int i = 0; i < args.length; i++){
                        state = state + args[i] + " ";
                    }
                    state = ChatColor.translateAlternateColorCodes('&', state);
                    playerFilebuilder.setStatus(state);
                    DisplayBuilder.setDisplay(p);
                    p.sendMessage(xCraft.getPrefix() + "§7Dein neuer Status lautet: " + state);
                }
            } else {
                p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/status <status, reset, update, copy>");
            }
        }else{
            sender.sendMessage(xCraft.getError() + "§cDu musst ein Spieler sein!");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        if(args.length == 1){
            stringList.add("update");
            stringList.add("reset");
            stringList.add("copy");
        }else if(args.length == 2){
            if(args[0].equalsIgnoreCase("copy")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    stringList.add(all.getName());
                }
            }
        }

        return stringList;
    }
}
