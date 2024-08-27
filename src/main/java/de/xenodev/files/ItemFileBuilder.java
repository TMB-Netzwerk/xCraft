package de.xenodev.files;

import de.xenodev.xCraft;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ItemFileBuilder {

    public static File file = new File("plugins/" + xCraft.getInstance().getName() + "/items", "market.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static ArrayList<ItemStack> loadItems(){
        ArrayList<ItemStack> arrayList = (ArrayList<ItemStack>) cfg.getList("items");
        if(arrayList == null){
            arrayList = new ArrayList<>();
        }

        return arrayList;
    }

    public static void saveItem(ItemStack itemStack){
        ArrayList<ItemStack> arrayList = (ArrayList<ItemStack>) cfg.getList("items");
        if(arrayList == null){
            arrayList = new ArrayList<>();
        }

        arrayList.add(itemStack);
        cfg.set("items", null);
        cfg.set("items", arrayList);
        save();
    }

    public static void deleteItem(ItemStack itemStack){
        ArrayList<ItemStack> arrayList = (ArrayList<ItemStack>) cfg.getList("items");
        if(arrayList == null){
            arrayList = new ArrayList<>();
        }

        arrayList.remove(itemStack);
        cfg.set("items", null);
        cfg.set("items", arrayList);
        save();
    }

    private static void save(){
        try { cfg.save(file); } catch (IOException e) { throw new RuntimeException(e); }
    }


}
