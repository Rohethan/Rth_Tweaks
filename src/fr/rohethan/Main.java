package fr.rohethan;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;

    @Override
    public void onEnable() {
        main = this;
        getLogger().info("§2Hey ! Listen !");

        getServer().getPluginManager().registerEvents(new Listeners(), this);

    }
}
