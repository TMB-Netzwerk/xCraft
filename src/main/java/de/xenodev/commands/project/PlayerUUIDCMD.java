package de.xenodev.commands.project;

import de.xenodev.xCraft;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUUIDCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if (!p.hasPermission("tmb.admin")) {
                p.sendMessage(xCraft.getError() + "§cDazu hast du keine Rechte!");
                return true;
            }

            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            p.sendMessage(xCraft.getPrefix() + "§7Die UUID von §e" + offlinePlayer.getName() + " §7lautet:");
            TextComponent textComponent = new TextComponent(offlinePlayer.getUniqueId().toString());
            textComponent.setColor(ChatColor.GREEN);
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, offlinePlayer.getUniqueId().toString()));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Clicke um zu kopieren")));
            p.spigot().sendMessage(textComponent);
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> stringList = new ArrayList<>();

        if(args.length == 1){
            for(Player all : Bukkit.getOnlinePlayers()){
                stringList.add(all.getName());
            }
        }

        return stringList;
    }
}
