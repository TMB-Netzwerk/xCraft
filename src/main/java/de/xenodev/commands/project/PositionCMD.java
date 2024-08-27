package de.xenodev.commands.project;

import de.xenodev.xCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PositionCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }
            if (args.length == 1) {
                if(args[0].equalsIgnoreCase("pos1")){
                    xCraft.getInstance().getConfig().set("locations.Elytra.pos1", p.getLocation());
                    xCraft.getInstance().saveConfig();
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast die §6Abfrageposition 1 §7gesetzt");
                }else if(args[0].equalsIgnoreCase("pos2")){
                    xCraft.getInstance().getConfig().set("locations.Elytra.pos2", p.getLocation());
                    xCraft.getInstance().saveConfig();
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast die §6Abfrageposition 2 §7gesetzt");
                }else if(args[0].equalsIgnoreCase("spawn")){
                    p.getWorld().setSpawnLocation(p.getLocation());
                    xCraft.getInstance().getConfig().set("locations.Spawn", p.getLocation());
                    xCraft.getInstance().saveConfig();
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast den §6Spawnpunkt §7gesetzt");
                }
            } else {
                p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/position <pos1, pos2, spawn>");
            }
        }else{
            sender.sendMessage(xCraft.getError() + "§cDu musst ein Spieler sein");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        if(args.length == 1){
            stringList.add("pos1");
            stringList.add("pos2");
            stringList.add("spawn");
        }

        return stringList;
    }
}
