package de.xenodev.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import java.util.LinkedList;
import java.util.List;

public class ChangeColorEvent implements Listener {

    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent e) {
        BookMeta bookMeta = e.getNewBookMeta();
        List<String> list = new LinkedList<>(bookMeta.getPages());
        list.replaceAll(page -> page.replace('&', '§'));
        bookMeta.setPages(list);
        e.setNewBookMeta(bookMeta);
    }

    @EventHandler
    public void onSign(SignChangeEvent e){
        String[] lines = e.getLines();
        for(int i = 0; i <= 3; i++){
            e.setLine(i, ChatColor.translateAlternateColorCodes('&', lines[i]));
        }
    }
}
