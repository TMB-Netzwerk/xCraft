package de.xenodev.commands.project;

import de.xenodev.files.PlayerFilebuilder;
import de.xenodev.files.StartFilebuilder;
import de.xenodev.utils.DisplayBuilder;
import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ResetCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }

            if(args.length == 1){
                if(args[0].equalsIgnoreCase("projekt")){
                    if(args[1].equalsIgnoreCase("true")){
                        StartFilebuilder.restart(true);
                        p.sendMessage(xCraft.getPrefix() + "§7Das Projekt wurde gestoppt. Alle Daten darüber wurden gelöscht!");
                    }else{
                        StartFilebuilder.restart(false);
                        p.sendMessage(xCraft.getPrefix() + "§7Das Projekt wurde gestoppt.");
                    }
                }else{
                    p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/reset projekt (true <- Löscht die Startdaten)");
                    p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/reset death,itemlist player anzahl");
                }
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("death")) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(offlinePlayer);
                    playerFilebuilder.setDeaths();
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast die Tode von §e" + offlinePlayer.getName() + " §7zurückgesetzt.");
                    if(offlinePlayer.isOnline()){
                        DisplayBuilder.setDisplay(offlinePlayer.getPlayer());
                    }
                }else{
                    p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/reset death,itemlist player");
                }
            }else if(args.length == 3){
                if(args[0].equalsIgnoreCase("death")) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    PlayerFilebuilder playerFilebuilder = new PlayerFilebuilder(offlinePlayer);
                    playerFilebuilder.setDeath(Integer.valueOf(args[2]));
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast die Tode von §e" + offlinePlayer.getName() + " §7auf §6" + args[2] + " §7gesetzt");
                    if(offlinePlayer.isOnline()){
                        DisplayBuilder.setDisplay(offlinePlayer.getPlayer());
                    }
                }else{
                    p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/reset death player anzahl");
                }
            }else{
                p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/reset projekt (true <- Löscht die Startdaten)");
                p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/reset deaths,itemlist");
            }
        }else{
            sender.sendMessage(xCraft.getError() + "§cDu musst ein Spieler sein");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        if(args.length == 1) {
            stringList.add("projekt");
            stringList.add("death");
            stringList.add("itemlist");
        }else if(args.length == 2){
            if(!args[0].equalsIgnoreCase("projekt")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    stringList.add(all.getName());
                }
            }
        }

        return stringList;
    }
}
