package de.xenodev.commands.usefull;

import de.xenodev.files.ItemFileBuilder;
import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MarketCMD implements CommandExecutor, TabCompleter {

    public static ArrayList<Player> trading = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            Integer integer = 0;

            if(args.length == 0){
                p.sendMessage(xCraft.getPrefix() + "§7Ein Markt-Chunk kostet §31 Diablock");
                p.sendMessage(xCraft.getPrefix() + "§7");
                p.sendMessage(xCraft.getPrefix() + "§7Kaufe nun einen Markt-Chunk mit §a/market trade");
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("trade")){
                    for(ItemStack itemStack : p.getInventory().getContents()){
                        if(itemStack != null) {
                            if (itemStack.getType().equals(Material.DIAMOND_BLOCK)) {
                                if (itemStack.getAmount() >= 1) {
                                    if(integer == 0) {
                                        integer++;
                                        itemStack.setAmount(itemStack.getAmount() - 1);
                                        p.sendMessage(xCraft.getPrefix() + "§7Du hast dir einen Chunk für einen Diablock gekauft");
                                        ItemStack itemStack1 = new ItemStack(Material.DIAMOND_BLOCK);
                                        ItemMeta itemMeta = itemStack1.getItemMeta();
                                        itemMeta.setLore(Arrays.asList("", "§7Diablock von: §e" + p.getName(), "§7Gekauft am: §a" + new SimpleDateFormat("dd.MM.yyyy").format(new Date())));
                                        itemStack1.setItemMeta(itemMeta);
                                        ItemFileBuilder.saveItem(itemStack1);
                                    }
                                }
                            }
                        }
                    }
                }else if(args[0].equals("admin")){
                    if(p.hasPermission("tmb.admin.market")){
                        Inventory inventory = Bukkit.createInventory(null, 9*6, "§7Market Tresor");
                        for(ItemStack itemStack : ItemFileBuilder.loadItems()) {
                            if(itemStack != null) {
                                inventory.addItem(itemStack);
                            }
                        }
                        p.openInventory(inventory);
                    }
                }else if(args[0].equals("delete")){
                    if(p.hasPermission("tmb.admin.market")){
                        ItemFileBuilder.deleteItem(p.getInventory().getItemInMainHand());
                        p.sendMessage(xCraft.getPrefix() + "§7Du hast das Item gelöscht!");
                        ItemStack itemStack = p.getInventory().getItemInMainHand();
                        itemStack.setAmount(itemStack.getAmount() - 1);
                    }
                }
            }
        }else{
            sender.sendMessage(xCraft.getError() + "§cDu musst ein Spieler sein!");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        if(args.length == 1) {
            stringList.add("trade");
        }

        return stringList;
    }
}
