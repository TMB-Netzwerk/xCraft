package de.xenodev.commands.usefull;

import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AnonymCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length >= 1){
                String msg = "";
                for(int i = 0; i < args.length; i++){
                    msg = msg + args[i] + " ";
                }

                for(Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage("§8[§d§lAnonym§8] §4§l" + msg.replace('&', '§'));
                }
            } else {
                p.sendMessage(xCraft.getError() + "§cDeine Nachricht fehlt!");
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
