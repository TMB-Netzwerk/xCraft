package de.xenodev.commands.project;

import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClearChatCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }

            for(int i = 0; i < 105; ++i){
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(!all.hasPermission("tmb.admin")){
                        all.sendMessage("");
                    }
                }
            }
            Bukkit.broadcastMessage(xCraft.getPrefix() + "§7Der Chat wurde von §e§l" + p.getName() + "§7 geleert!");
        }else{
            for(int i = 0; i < 105; ++i){
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.sendMessage("");
                }
            }
            Bukkit.broadcastMessage("§o[Server]" + " §7§oDer Chat wurde geleert!");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        return stringList;
    }
}
