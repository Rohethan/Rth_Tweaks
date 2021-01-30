package fr.entasia.factools;

import fr.entasia.factools.cmd.GiveWandCMD;
import fr.entasia.factools.cmd.ManaCMD;
import fr.entasia.factools.cmd.SpellSelectCMD;
import fr.entasia.factools.listeners.MagicListeners;
import fr.entasia.factools.listeners.QualityCraftingListeners;
import fr.entasia.factools.others.ManaHUD;
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

            getLogger().info("---Factools Loading--- : Listeners");
            getServer().getPluginManager().registerEvents(new MagicListeners(), this);
            getServer().getPluginManager().registerEvents(new QualityCraftingListeners(), this);

            getLogger().info("---Factools Loading--- : Commannds");
            getCommand("spell").setExecutor(new SpellSelectCMD());
            getCommand("givewand").setExecutor(new GiveWandCMD());
            getCommand("mana").setExecutor(new ManaCMD());
            getLogger().info("---Factools Loading--- : Async tasks");
            new ManaHUD().runTaskTimerAsynchronously(this, 0, 20*2);
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
