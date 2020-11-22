package fr.entasia.factools;

import fr.entasia.factools.listeners.Spells;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;

    @Override
    public void onEnable() {
        try{
            main = this;
            getLogger().info("Activation du plugin...");

            getServer().getPluginManager().registerEvents(new Spells(), this);

            getLogger().info("Plugin activé !");
        }catch(Throwable t){
            t.printStackTrace();
            getLogger().severe("Une erreur s'est produite ! ARRET DU SERVEUR");
            getServer().shutdown();
        }
    }
}
