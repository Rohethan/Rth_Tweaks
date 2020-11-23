package fr.entasia.factools;

import fr.entasia.factools.cmd.FireballSelectCMD;
import fr.entasia.factools.cmd.FrozeSelectCMD;
import fr.entasia.factools.cmd.GlideSelectCMD;
import fr.entasia.factools.cmd.HealSelectCMD;
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
            getCommand("s_glide").setExecutor(new GlideSelectCMD());
            getCommand("s_fireball").setExecutor(new FireballSelectCMD());
            getCommand("s_heal").setExecutor(new HealSelectCMD());
            getLogger().info("s_GEL REGISTER ---------------------------------------------------------------------------------------");
            getCommand("s_gel").setExecutor(new FrozeSelectCMD());
            getLogger().info("s_GEL REGISTER ---------------------------------------------------------------------------------------");

            getLogger().info("Plugin de spells activé !");
        }catch(Throwable t){
            t.printStackTrace();
            getLogger().severe("ERREUR SURVENUE !! Arrêt du serveur! (devs: ah shit here we go again)");
            getServer().shutdown();
        }
    }
}
