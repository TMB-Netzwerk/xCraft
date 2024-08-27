package de.xenodev.files;

import de.xenodev.xCraft;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerFilebuilder {

    public static File file;
    public static YamlConfiguration cfg;

    private OfflinePlayer p;

    public PlayerFilebuilder(OfflinePlayer p){
        this.p = p;
        file = new File("plugins/" + xCraft.getInstance().getName() + "/players", p.getUniqueId() + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public Integer getDeaths(){
        if(cfg.get("death") != null) {
            return cfg.getInt("death");
        }else{
            return 0;
        }
    }

    public void addDeaths(){
        cfg.set("death", getDeaths() + 1);
        save();
    }

    public void setDeaths(){
        cfg.set("death", 0);
        save();
    }

    public void setDeath(Integer amount){
        cfg.set("death", amount);
        save();
    }

    public String getColor(){
        if(cfg.get("color") != null) {
            return cfg.getString("color").replace("&", "§");
        }else{
            return "§7";
        }
    }

    public void setColor(String color){
        cfg.set("color", color);
        save();
    }

    public String getStatus(){
        if(cfg.get("status") != null) {
            return cfg.getString("status").replace("&", "§");
        }else{
            return "";
        }
    }

    public void setStatus(String status){
        cfg.set("status", status);
        save();
    }

    public Boolean getFirst(){
        if(cfg.get("first") != null) {
            return cfg.getBoolean("first");
        }else {
            return false;
        }
    }

    public void setFirst(){
        cfg.set("first", true);
    }

    public Boolean getElytra(){
        if(cfg.get("elytra") != null) {
            return cfg.getBoolean("elytra");
        }else {
            return false;
        }
    }

    public void setElytra(Boolean bool){
        cfg.set("elytra", bool);
        save();
    }

    public void setName(){
        cfg.set("name", p.getPlayer().getName());
        save();
    }

    private void save(){
        try { cfg.save(file); } catch (IOException e) { throw new RuntimeException(e); }
    }

}
