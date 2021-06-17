package me.rohethan.rth_tweaks;

import me.rohethan.rth_tweaks.listeners.ArrowBlazing;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin {

    public static Main main;
    public static Random r = new Random();

    @Override
    public void onEnable() {
        try{
            main = this;
            getServer().getPluginManager().registerEvents(new ArrowBlazing(), this);

        }catch(Throwable t){
            t.printStackTrace();
            getLogger().severe("me be like, :Marisa_exe:");
            getServer().shutdown();
        }
    }
}
