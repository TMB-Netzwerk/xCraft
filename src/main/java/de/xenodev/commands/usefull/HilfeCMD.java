package de.xenodev.commands.usefull;

import de.xenodev.xCraft;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HilfeCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0){
                p.sendMessage("§8§l» " + xCraft.getInstance().getConfig().getString("projectname").replace("&", "§") + " §8§l«");
                p.sendMessage("");
                p.sendMessage("§8§l➤ §7Pluginversion: §5§l" + xCraft.getInstance().getDescription().getVersion() + " §8| §7Codet by §3§lXenoDEV");
                p.sendMessage("");
                p.sendMessage("§7Was willst du wissen?");
                TextComponent textComponent4 = new TextComponent("Das sind die wichtigsten Regeln");
                textComponent4.setColor(ChatColor.GOLD);
                textComponent4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hilfe rules"));
                textComponent4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Clicke um es anzuzeigen")));
                p.spigot().sendMessage(textComponent4);
                TextComponent textComponent1 = new TextComponent("Das sind unsere Commands");
                textComponent1.setColor(ChatColor.GOLD);
                textComponent1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hilfe commands 1"));
                textComponent1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Clicke um es anzuzeigen")));
                p.spigot().sendMessage(textComponent1);
                TextComponent textComponent2 = new TextComponent("Das sind unsere Besonderheiten");
                textComponent2.setColor(ChatColor.GOLD);
                textComponent2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hilfe features"));
                textComponent2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Clicke um es anzuzeigen")));
                p.spigot().sendMessage(textComponent2);
                TextComponent textComponent3 = new TextComponent("Das sind die neusten Änderungen");
                textComponent3.setColor(ChatColor.GOLD);
                textComponent3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hilfe news"));
                textComponent3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Clicke um es anzuzeigen")));
                p.spigot().sendMessage(textComponent3);
                p.sendMessage("");
            }else{
                if(args[0].equalsIgnoreCase("commands")){
                    if(args[1].equalsIgnoreCase("1")){
                        p.sendMessage("");
                        p.sendMessage("§8- §7Unsere Commands §8-");
                        p.sendMessage("");
                        p.sendMessage("§8✘ §e/uptime §8| §7§oSchaue dir die Projektzeit an");
                        p.sendMessage("§8✘ §e/anonym §8| §7§oSchreibe eine anonyme Nachricht");
                        p.sendMessage("§8✘ §e/author §8| §7§oSetze den Author eines Buches um");
                        p.sendMessage("§8✘ §e/color §8| §7§oSetzte deine Fabe");
                        p.sendMessage("§8✘ §e/rename §8| §7§oSetzte den Namen eines Items um");
                        p.sendMessage("§8✘ §e/status §8| §7§oSetzte deinen Status");
                        p.sendMessage("§8✘ §e/hilfe §8| §7§oSchaue dir das hilfreicheste an");
                        p.sendMessage("§8✘ §e/market §8| §7§oHandle mit dem Bauamt für ein Shop");
                        if(p.isOp()){
                            p.sendMessage("");
                            p.sendMessage("§8- §7Admin Commands §8-");
                            p.sendMessage("");
                            p.sendMessage("§8✘ §e/adminmode §8| §7§oGeh in dem Adminmode");
                            p.sendMessage("§8✘ §e/broadcast §8| §7§oSende eine Nachricht an alle");
                            p.sendMessage("§8✘ §e/clearchat §8| §7§oLösche die Chatnachrichten");
                            p.sendMessage("§8✘ §e/clearlag  §8| §7§oLösche alle Items auf dem Boden");
                            p.sendMessage("§8✘ §e/configreload §8| §7§oLade verschiedene Configs neu");
                            p.sendMessage("§8✘ §e/gamemode §8| §7§oSetzte den Gamemode um");
                            p.sendMessage("§8✘ §e/uuid §8| §7§oBekomme die UUID von einem Spieler");
                            p.sendMessage("§8✘ §e/position §8| §7§oSetzte verschiedene Positionen");
                            p.sendMessage("§8✘ §e/start §8| §7§oStarte das Projekt");
                            p.sendMessage("§8✘ §e/reset §8| §7§oSetzte das Projekt oder einen Spieler zurück");
                        }
                        p.sendMessage("");
                    }
                }else if(args[0].equalsIgnoreCase("features")){
                    p.sendMessage("");
                    p.sendMessage("§8- §7Unsere Features §8-");
                    p.sendMessage("");
                    p.sendMessage("§8● §7Booste dich vom Spawn mit der Elytra (leftclick)");
                    p.sendMessage("§8● §7Schreibe Fabig auf Schildern, im Chat und in Büchern");
                    p.sendMessage("§8● §7Nacht wird übersprungen auch wenn nicht alle schlafen");
                    p.sendMessage("§8● §7Erhalte am Anfang ein Backpack als Shulkerersatz");
                    p.sendMessage("");
                }else if(args[0].equalsIgnoreCase("news")){
                    p.sendMessage("");
                    p.sendMessage("§8- §7Letzte Änderungen §8-" + " §8[§5v" + xCraft.getInstance().getDescription().getVersion() + "§8]");
                    p.sendMessage("");
                    p.sendMessage("§8❱ §7Sourcecode verbessert");
                    p.sendMessage("§8❱ §7Auf MC v1.20.4 Upgradet");
                    p.sendMessage("§8❱ §7Scoreboard hinzugefügt");
                    p.sendMessage("§8❱ §7Tablist entfernt");
                    p.sendMessage("");
                }else if(args[0].equalsIgnoreCase("rules")){
                    p.sendMessage("");
                    p.sendMessage("§8- §7Die wichtigsten Regeln §8-");
                    p.sendMessage("");
                    p.sendMessage("§8I. §7Basen innerhalb von 5k Bläcke des Spawns entfernt");
                    p.sendMessage("§8II. §7Base-Koordinaten müssen mit /coords eingetragen werden");
                    p.sendMessage("§8III. §7Keine Beleidigungen im Voice oder Chat");
                    p.sendMessage("§8IV. §7Keine Hackclients, Bugabuse oder Duplizieren");
                    p.sendMessage("§8V. §7Absichtliches Griefen ist verboten");
                    p.sendMessage("§8VI. §7Der Aufenthalt auf dem Discord ist pflicht");
                    p.sendMessage("§8VII. §7Das Töten anderer Spieler ist erlaubt, endet aber bei Aufforderung");
                    p.sendMessage("");
                    p.sendMessage("§8§oBeim Teilnehmen akzeptierst du die Regeln automatisch!");
                    p.sendMessage("§c§oMissachtung der Regeln führt zu einem Projektausschluss!");
                    p.sendMessage("");
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

        return stringList;
    }
}
