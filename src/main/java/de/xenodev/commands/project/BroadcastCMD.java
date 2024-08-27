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

public class BroadcastCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }

            if(args.length >= 1){
                String msg = "";
                for(int i = 0; i < args.length; i++){
                    msg = msg + args[i] + " ";
                }

                Bukkit.broadcastMessage("§8[§4§lBroadcast§8] §8➥ §c" + msg.replace('&', '§'));
            } else {
                p.sendMessage(xCraft.getError() + "§cDeine Nachricht fehlt!");
            }
        }else {
            if (args.length >= 1) {
                String msg = "";
                for (int i = 0; i < args.length; i++) {
                    msg = msg + args[i] + " ";
                }

                Bukkit.broadcastMessage("§8[§4§lBroadcast§8] §8➥ §c" + msg.replace('&', '§'));
            } else {
                sender.sendMessage(xCraft.getError() + "§cDeine Nachricht fehlt!");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        return stringList;
    }
}
