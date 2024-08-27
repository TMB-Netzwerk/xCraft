package de.xenodev.commands.project;

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

public class GamemodeCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    if (p.hasPermission("tmb.gm.survival")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eSurvival §7geändert");
                    } else {
                        p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                    }
                } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    if (p.hasPermission("tmb.gm.creative")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eCreative §7geändert");
                    } else {
                        p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                    }
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    if (p.hasPermission("tmb.gm.adventure")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eAdventure §7geändert");
                    } else {
                        p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                    }
                } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    if (p.hasPermission("tmb.gm.spectator")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eSpectator §7geändert");
                    } else {
                        p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                    }
                }else{
                    p.sendMessage(xCraft.getError() + "§cDiesen Gamemode gibt es nicht!");
                }
            } else if (args.length == 2) {
                Player t = Bukkit.getPlayerExact(args[1]);
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if (p.hasPermission("tmb.gm.survival.other")) {
                            t.setGameMode(GameMode.SURVIVAL);
                            t.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eSurvival §7geändert");
                            p.sendMessage(xCraft.getPrefix() + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eSurvival §7geändert");
                        } else {
                            p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                        }
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if (p.hasPermission("tmb.gm.creative.other")) {
                            t.setGameMode(GameMode.CREATIVE);
                            t.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eCreative §7geändert");
                            p.sendMessage(xCraft.getPrefix() + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eCreative §7geändert");
                        } else {
                            p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                        }
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if (p.hasPermission("tmb.gm.adventure.other")) {
                            t.setGameMode(GameMode.ADVENTURE);
                            t.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eAdventure §7geändert");
                            p.sendMessage(xCraft.getPrefix() + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eAdventure §7geändert");
                        } else {
                            p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                        }
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if (p.hasPermission("tmb.gm.spectator.other")) {
                            t.setGameMode(GameMode.SPECTATOR);
                            t.sendMessage(xCraft.getPrefix() + "§7Dein Gamemode wurde auf §eSpectator §7geändert");
                            p.sendMessage(xCraft.getPrefix() + "§7Du hast den Gamemode von §a" + t.getName() + " §7auf §eSpectator §7geändert");
                        } else {
                            p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                        }
                    }else{
                        p.sendMessage(xCraft.getError() + "§cDiesen Gamemode gibt es nicht!");
                    }
            } else {
                p.sendMessage(xCraft.getPrefix() + "§7Bitte benutze §a/gm <0/1/2/3>");
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
            stringList.add("0");
            stringList.add("1");
            stringList.add("2");
            stringList.add("3");
        }else if(args.length == 2){
            for(Player all : Bukkit.getOnlinePlayers()){
                stringList.add(all.getName());
            }
        }

        return stringList;
    }
}
