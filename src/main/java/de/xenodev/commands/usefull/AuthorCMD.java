package de.xenodev.commands.usefull;

import de.xenodev.xCraft;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class AuthorCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;

            if (p.getInventory().getItemInMainHand().getType() == Material.WRITTEN_BOOK) {
                if (args.length >= 1) {
                    String msg = "";
                    for (int i = 0; i < args.length; i++) {
                        msg = msg + args[i] + " ";
                    }

                    ItemStack item = p.getInventory().getItemInMainHand();
                    BookMeta bookMeta = (BookMeta) item.getItemMeta();
                    bookMeta.setAuthor(msg);
                    bookMeta.setGeneration(BookMeta.Generation.TATTERED);
                    item.setItemMeta(bookMeta);
                    p.sendMessage(xCraft.getPrefix() + "§7Du hast den Author des Buches geändert");
                } else {
                    p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/author <name>");
                }
            } else {
                p.sendMessage(xCraft.getError() + "§cDu musst ein beschriebenes Buch in der Hand haben!");
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
