package de.xenodev;

import de.xenodev.commands.project.*;
import de.xenodev.commands.usefull.*;
import de.xenodev.events.*;
import de.xenodev.files.StartFilebuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class xCraft extends JavaPlugin {

    private static xCraft instance;

    @Override
    public void onEnable() {
        instance = this;

        if(!new File("plugins/" + getName(), "config.yml").exists()){
            saveDefaultConfig();
        }

        init(Bukkit.getPluginManager());
        setupStartTime();
    }

    private void init(PluginManager pluginManager){
        pluginManager.registerEvents(new SleepEvent(), this);
        pluginManager.registerEvents(new DeathEvent(), this);
        pluginManager.registerEvents(new MOTDEvent(), this);
        pluginManager.registerEvents(new ChangeColorEvent(), this);
        pluginManager.registerEvents(new ChatEvent(), this);
        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new LeaveEvent(), this);
        pluginManager.registerEvents(new ElytraEvent(this), this);
        pluginManager.registerEvents(new SpawnSafeEvent(), this);

        getCommand("broadcast").setExecutor(new BroadcastCMD());
        getCommand("clearchat").setExecutor(new ClearChatCMD());
        getCommand("configreload").setExecutor(new ConfigReloadCMD());
        getCommand("gamemode").setExecutor(new GamemodeCMD());
        getCommand("adminmode").setExecutor(new AdminmodeCMD());
        getCommand("clearlag").setExecutor(new ClearlagCMD());
        getCommand("position").setExecutor(new PositionCMD());
        getCommand("anonym").setExecutor(new AnonymCMD());
        getCommand("author").setExecutor(new AuthorCMD());
        getCommand("rename").setExecutor(new RenameCMD());
        getCommand("start").setExecutor(new StartCMD());
        getCommand("uptime").setExecutor(new UptimeCMD());
        getCommand("status").setExecutor(new StatusCMD());
        getCommand("color").setExecutor(new ColorCMD());
        getCommand("hilfe").setExecutor(new HilfeCMD());
        getCommand("reset").setExecutor(new ResetCMD());
        getCommand("uuid").setExecutor(new PlayerUUIDCMD());
        getCommand("market").setExecutor(new MarketCMD());

        getCommand("broadcast").setTabCompleter(new BroadcastCMD());
        getCommand("clearchat").setTabCompleter(new ClearChatCMD());
        getCommand("configreload").setTabCompleter(new ConfigReloadCMD());
        getCommand("gamemode").setTabCompleter(new GamemodeCMD());
        getCommand("adminmode").setTabCompleter(new AdminmodeCMD());
        getCommand("clearlag").setTabCompleter(new ClearlagCMD());
        getCommand("position").setTabCompleter(new PositionCMD());
        getCommand("anonym").setTabCompleter(new AnonymCMD());
        getCommand("author").setTabCompleter(new AuthorCMD());
        getCommand("rename").setTabCompleter(new RenameCMD());
        getCommand("start").setTabCompleter(new StartCMD());
        getCommand("uptime").setTabCompleter(new UptimeCMD());
        getCommand("status").setTabCompleter(new StatusCMD());
        getCommand("color").setTabCompleter(new ColorCMD());
        getCommand("hilfe").setTabCompleter(new HilfeCMD());
        getCommand("reset").setTabCompleter(new ResetCMD());
        getCommand("uuid").setTabCompleter(new PlayerUUIDCMD());
        getCommand("market").setTabCompleter(new MarketCMD());
    }

    public static xCraft getInstance() {
        return instance;
    }

    public static String getPrefix(){
        return getInstance().getConfig().getString("projectprefix").replace("&", "§");
    }

    public static String getError(){
        return getInstance().getConfig().getString("projecterror").replace("&", "§");
    }

    private void setupStartTime() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                if (StartFilebuilder.getStart().equals(true)) {
                    if (StartFilebuilder.getSeconds() == 60) {
                        StartFilebuilder.setSeconds(0);
                        StartFilebuilder.setMinutes(StartFilebuilder.getMinutes() + 1);
                    }
                    if (StartFilebuilder.getMinutes() == 60) {
                        StartFilebuilder.setMinutes(0);
                        StartFilebuilder.setHours(StartFilebuilder.getHours() + 1);
                    }
                    if (StartFilebuilder.getHours() == 24) {
                        StartFilebuilder.setHours(0);
                        StartFilebuilder.setDays(StartFilebuilder.getDays() + 1);
                    }
                    if (StartFilebuilder.getDays() == 7) {
                        StartFilebuilder.setDays(0);
                        StartFilebuilder.setWeeks(StartFilebuilder.getWeeks() + 1);
                    }

                    if (StartFilebuilder.getWeeks() == 4) {
                        StartFilebuilder.setWeeks(0);
                        StartFilebuilder.setMonths(StartFilebuilder.getMonths() + 1);
                    }

                    if (StartFilebuilder.getMonths() == 12) {
                        StartFilebuilder.setMonths(0);
                        StartFilebuilder.setYears(StartFilebuilder.getYears() + 1);
                    }
                    StartFilebuilder.setSeconds(StartFilebuilder.getSeconds() + 1);
                }
            }
        }, 0L, 20L);
    }
}