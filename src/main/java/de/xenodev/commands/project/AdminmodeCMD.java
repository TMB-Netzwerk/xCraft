package de.xenodev.commands.project;

import de.xenodev.utils.DisplayBuilder;
import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AdminmodeCMD implements CommandExecutor, TabCompleter {

    public static ArrayList<Player> inAdminmode = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }

            if(inAdminmode.contains(p)){
                inAdminmode.remove(p);
                DisplayBuilder.setDisplay(p);
                p.sendMessage(xCraft.getPrefix() + "§7Du hast den §eAdminmode §7verlassen");
                p.setGameMode(GameMode.SURVIVAL);
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§a" + p.getName() + " §7ist beigetreten");
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(!AdminmodeCMD.inAdminmode.contains(all)){
                        all.showPlayer(xCraft.getInstance(), p);
                    }else {
                        p.hidePlayer(xCraft.getInstance(), all);
                    }
                }
            }else{
                inAdminmode.add(p);
                DisplayBuilder.setDisplay(p);
                p.sendMessage(xCraft.getPrefix() + "§7Du hast den §eAdminmode §7betreten");
                p.setGameMode(GameMode.CREATIVE);
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§c" + p.getName() + " §7hat verlassen");
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(!AdminmodeCMD.inAdminmode.contains(all)){
                        all.hidePlayer(xCraft.getInstance(), p);
                    }else {
                        p.showPlayer(xCraft.getInstance(), all);
                        all.showPlayer(xCraft.getInstance(), p);
                    }
                }
            }
        }else{
            sender.sendMessage(xCraft.getError() + "§cDu musst ein Spieler sein");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        return stringList;
    }
}
