package de.xenodev.commands.project;

import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClearlagCMD implements CommandExecutor, TabCompleter {

    private HashMap<World, Integer> itemlist = new HashMap<>();
    private ArrayList<Item> netheritems = new ArrayList<>();
    private ArrayList<Item> enditems = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1){
                if(!p.hasPermission("tmb.admin")){
                    p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                    return true;
                }
                if(args[0].equalsIgnoreCase("world")){
                    itemlist.put(Bukkit.getWorld("world"), 0);
                    for(Entity entity : Bukkit.getWorld("world").getEntities()){
                        if(entity instanceof Item) {
                            itemlist.put(Bukkit.getWorld("world"), itemlist.get(Bukkit.getWorld("world")) + ((Item) entity).getItemStack().getAmount());
                            entity.remove();
                        }
                    }
                    Bukkit.broadcastMessage("§8[§6ClearLag§8] §6" + p.getName() + " §7hat alle Items in der §2Overworld §7gelöscht §8[§e" + itemlist.get(Bukkit.getWorld("world")) + "§8]");
                    itemlist.remove(Bukkit.getWorld("world"));
                }else if(args[0].equalsIgnoreCase("nether")){
                    itemlist.put(Bukkit.getWorld("world_nether"), 0);
                    for(Entity entity : Bukkit.getWorld("world_nether").getEntities()){
                        if(entity instanceof Item) {
                            itemlist.put(Bukkit.getWorld("world_nether"), itemlist.get(Bukkit.getWorld("world_nether")) + ((Item) entity).getItemStack().getAmount());
                            entity.remove();
                        }
                    }
                    Bukkit.broadcastMessage("§8[§6ClearLag§8] §6" + p.getName() + " §7hat alle Items im §cNether §7gelöscht §8[§e" + itemlist.get(Bukkit.getWorld("world_nether")) + "§8]");
                    itemlist.remove(Bukkit.getWorld("world_nether"));
                }else if(args[0].equalsIgnoreCase("end")){
                    itemlist.put(Bukkit.getWorld("world_the_end"), 0);
                    for(Entity entity : Bukkit.getWorld("world_the_end").getEntities()){
                        if(entity instanceof Item) {
                            itemlist.put(Bukkit.getWorld("world_the_end"), itemlist.get(Bukkit.getWorld("world_the_end")) + ((Item) entity).getItemStack().getAmount());
                            entity.remove();
                        }
                    }
                    Bukkit.broadcastMessage("§8[§6ClearLag§8] §6" + p.getName() + " §7hat alle Items im §8End §7gelöscht §8[§e" + itemlist.get(Bukkit.getWorld("world_the_end")) + "§8]");
                    itemlist.remove(Bukkit.getWorld("world_the_end"));
                }else if(args[0].equalsIgnoreCase("all")){
                    itemlist.put(Bukkit.getWorld("world"), 0);
                    itemlist.put(Bukkit.getWorld("world_nether"), 0);
                    itemlist.put(Bukkit.getWorld("world_the_end"), 0);
                    for(Entity entity : Bukkit.getWorld("world").getEntities()){
                        if(entity instanceof Item) {
                            itemlist.put(Bukkit.getWorld("world"), itemlist.get(Bukkit.getWorld("world")) + ((Item) entity).getItemStack().getAmount());
                            entity.remove();
                        }
                    }

                    for(Entity entity : Bukkit.getWorld("world_nether").getEntities()){
                        if(entity instanceof Item) {
                            itemlist.put(Bukkit.getWorld("world_nether"), itemlist.get(Bukkit.getWorld("world_nether")) + ((Item) entity).getItemStack().getAmount());
                            entity.remove();
                        }
                    }

                    for(Entity entity : Bukkit.getWorld("world_the_end").getEntities()){
                        if(entity instanceof Item) {
                            itemlist.put(Bukkit.getWorld("world_the_end"), itemlist.get(Bukkit.getWorld("world_the_end")) + ((Item) entity).getItemStack().getAmount());
                            entity.remove();
                        }
                    }
                    Integer world = itemlist.get(Bukkit.getWorld("world"));
                    Integer nether = itemlist.get(Bukkit.getWorld("world_nether"));
                    Integer end = itemlist.get(Bukkit.getWorld("world_the_end"));
                    Integer allitems = world + nether + end;
                    Bukkit.broadcastMessage("§8[§6ClearLag§8] §6" + p.getName() + " §7hat alle Items gelöscht §8[§e" + allitems + "§8]");
                    itemlist.remove(Bukkit.getWorld("world"));
                    itemlist.remove(Bukkit.getWorld("world_nether"));
                    itemlist.remove(Bukkit.getWorld("world_the_end"));
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

        if(args.length == 1){
            stringList.add("world");
            stringList.add("nether");
            stringList.add("end");
            stringList.add("all");
        }

        return stringList;
    }
}
