package de.xenodev.commands.project;

import de.xenodev.files.StartFilebuilder;
import de.xenodev.xCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StartCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("tmb.admin")){
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }

            if(StartFilebuilder.getStart().equals(false)) {
                for(int i = 0; i < 5; i++){
                    Bukkit.broadcastMessage("");
                }
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§8>>> " + xCraft.getInstance().getConfig().getString("projectname").replace("&", "§") + " §8<<<");
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§7Guten Tag, an alle Teilnehmer");
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§7Vielen Dank das Ihr mitmacht.");
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§cDas Regelwerk bitte beachten!");
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§7Regelwerk: §fhttps://discord.gg/S3ctpX4RAa.");
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§7Ich hoffe wir haben eine schöne Zeit.");
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§eViel Spaß.... wünscht das");
                Bukkit.broadcastMessage(xCraft.getPrefix() + "§5§lTMB-Team.");
                for(int i = 0; i < 5; i++){
                    Bukkit.broadcastMessage("");
                }
                StartFilebuilder.setStart();
            }else{
                p.sendMessage(xCraft.getError() + "§cDas Projekt ist bereits gestartet!");
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
