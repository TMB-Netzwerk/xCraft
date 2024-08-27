package de.xenodev.commands.usefull;

import de.xenodev.xCraft;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RenameCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;
            if (p.getItemInUse() != null) {
                if(p.getLevel() >= 3) {
                    if (args.length >= 1) {
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }

                        ItemStack item = p.getInventory().getItemInMainHand();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(msg.replace("&", "§"));
                        item.setItemMeta(meta);
                        p.setLevel(p.getLevel() - 3);
                        p.sendMessage(xCraft.getPrefix() + "§7Du hast das Item umbenannt");
                    } else {
                        p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/rename <name>");
                    }
                }else{
                    if(p.getGameMode() == GameMode.CREATIVE){
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }

                        ItemStack item = p.getInventory().getItemInMainHand();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(msg.replace("&", "§"));
                        item.setItemMeta(meta);
                        p.sendMessage(xCraft.getPrefix() + "§7Du hast das Item umbenannt");
                        return true;
                    }
                    p.sendMessage(xCraft.getError() + "§cDu hast nicht genügen Level!");
                }
            } else {
                p.sendMessage(xCraft.getError() + "§cDu musst ein Item in der Hand haben!");
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
