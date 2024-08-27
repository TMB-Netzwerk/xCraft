package de.xenodev.files;

import de.xenodev.xCraft;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StartFilebuilder {

    public static File file = new File("plugins/" + xCraft.getInstance().getName(), "start.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setStart(){
        cfg.set("Datum", new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        cfg.set("Started", true);
        setSeconds(0);
        setMinutes(0);
        setHours(0);
        setDays(0);
        setWeeks(0);
        setMonths(0);
        setYears(0);
        save();
    }

    public static void restart(Boolean bool){
        if(bool == true){
            cfg.set("Started", false);
            setSeconds(0);
            setMinutes(0);
            setHours(0);
            setDays(0);
            setWeeks(0);
            setMonths(0);
            setYears(0);
            cfg.set("Datum", null);
        } else {
            cfg.set("Started", false);
        }
        save();
    }

    public static Boolean getStart(){
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public static String getDatum(){
        return cfg.getString("Datum");
    }

    public static void setSeconds(Integer time){
        cfg.set("Start.Seconds", time);
        save();
    }

    public static void setMinutes(Integer time){
        cfg.set("Start.Minutes", time);
        save();
    }

    public static void setHours(Integer time){
        cfg.set("Start.Hours", time);
        save();
    }

    public static void setDays(Integer time){
        cfg.set("Start.Days", time);
        save();
    }

    public static void setWeeks(Integer time){
        cfg.set("Start.Weeks", time);
        save();
    }

    public static void setMonths(Integer time){
        cfg.set("Start.Months", time);
        save();
    }

    public static void setYears(Integer time){
        cfg.set("Start.Years", time);
        save();
    }

    public static Integer getSeconds(){
        return cfg.getInt("Start.Seconds");
    }

    public static Integer getMinutes(){
        return cfg.getInt("Start.Minutes");
    }

    public static Integer getHours(){
        return cfg.getInt("Start.Hours");
    }

    public static Integer getDays(){
        return cfg.getInt("Start.Days");
    }

    public static Integer getWeeks(){
        return cfg.getInt("Start.Weeks");
    }

    public static Integer getMonths(){
        return cfg.getInt("Start.Months");
    }

    public static Integer getYears(){
        return cfg.getInt("Start.Years");
    }

    private static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTime(){
        String seconds = " §6" + "0§7s";
        String minutes = " §6" + "0§7m";
        String hours = " §6" + "0§7h";
        String days = " §6" + "0 §7Days";
        String weeks = " §6" + "0 §7Weeks";
        String months = " §6" + "0 §7Months";
        String years = " §6" + "0 §7Years";

        if(getSeconds() != 0){
            seconds = " §6" + getSeconds() + "§7s";
        }
        if(getMinutes() != 0){
            minutes = " §6" + getMinutes() + "§7m";
        }
        if(getHours() != 0){
            hours = " §6" + getHours() + "§7h";
        }
        if(getDays() != 0){
            if(getDays() == 1){
                days = " §6" + getDays() + " §7Tag";
            }else {
                days = " §6" + getDays() + " §7Tage";
            }
        }
        if(getWeeks() != 0){
            if(getWeeks() == 1) {
                weeks = " §6" + getWeeks() + " §7Woche";
            }else{
                weeks = " §6" + getWeeks() + " §7Wochen";
            }
        }
        if(getMonths() != 0){
            if(getMonths() == 1) {
                months = " §6" + getMonths() + " §7Monat";
            }else {
                months = " §6" + getMonths() + " §7Monate";
            }
        }
        if(getYears() != 0){
            if(getYears() == 1){
                years = " §6" + getYears() + " §7Jahr";
            }else{
                years = " §6" + getYears() + " §7Jahre";
            }
        }

        String time = "§8Projektzeit:";

        if(getDays() == 0 && getWeeks() == 0 && getMonths() == 0 && getYears() == 0){
            time = time + hours + minutes + seconds;
        }else if(getDays() != 0 && getWeeks() == 0) {
            time = time + days;
        }else if(getWeeks() != 0 && getMonths() == 0) {
            time = time + weeks;
        }else if(getMonths() != 0 && getYears() == 0) {
            time = time + months;
        }else if(getYears() != 0) {
            time = time + years;
        }else{
            time = time + "§c§lKonnte nicht geladen werden...";
        }

        return time;
    }
}
