package fr.entasia.factools;

import fr.entasia.factools.cmd.GiveWandCMD;
import fr.entasia.factools.cmd.ManaCMD;
import fr.entasia.factools.cmd.SpellSelectCMD;
import fr.entasia.factools.listeners.ManaGainListeners;
import fr.entasia.factools.listeners.SpellListeners;
import fr.entasia.factools.utils.Mana;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin {

    public static Main main;
    public static Random r = new Random();

    @Override
    public void onEnable() {
        try{
            main = this;

            saveDefaultConfig();
            main.reloadConfig();
            loadConfig();

            getLogger().info("Activation du plugin...");

            getLogger().info("Activation des spells");
            getServer().getPluginManager().registerEvents(new SpellListeners(), this);
            getServer().getPluginManager().registerEvents(new ManaGainListeners(), this);
            getCommand("spell").setExecutor(new SpellSelectCMD());
            getCommand("givewand").setExecutor(new GiveWandCMD());
            getCommand("mana").setExecutor(new ManaCMD());

            getLogger().info("Plugin FacTools activé !");
        }catch(Throwable t){
            t.printStackTrace();
            getLogger().severe("ERREUR SURVENUE !! Arrêt du serveur! (devs: ah shit here we go again)");
            getServer().shutdown();
        }
    }

    public static void loadConfig() throws Throwable {
        Mana.manaGain = main.getConfig().getInt("mana_gain");
        Mana.manaLimit = main.getConfig().getInt("mana_limit");
    }
}
