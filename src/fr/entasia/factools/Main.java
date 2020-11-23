package fr.entasia.factools;

import fr.entasia.factools.cmd.*;
import fr.entasia.factools.listeners.Spells;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;

    @Override
    public void onEnable() {
        try{
            main = this;
            getLogger().info("Activation du plugin...");

            getLogger().info("Activation des spells");
            getServer().getPluginManager().registerEvents(new Spells(), this);
            getCommand("spell").setExecutor(new SpellSelectCMD());

            getLogger().info("Plugin FacTools activé !");
        }catch(Throwable t){
            t.printStackTrace();
            getLogger().severe("ERREUR SURVENUE !! Arrêt du serveur! (devs: ah shit here we go again)");
            getServer().shutdown();
        }
    }
}
