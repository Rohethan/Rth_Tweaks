package me.rohethan.rth_tweaks;

import me.rohethan.rth_tweaks.cmd.GetBlazingArrowCMD;
import me.rohethan.rth_tweaks.cmd.GetPhantomArrowCMD;
import me.rohethan.rth_tweaks.listeners.CustomArrowHitBlock;
import me.rohethan.rth_tweaks.listeners.CustomArrowHitEntity;
import me.rohethan.rth_tweaks.listeners.CustomArrowShoot;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin {

    public static Main main;
    public static Random r = new Random();

    @Override
    public void onEnable() {
        try{
            main = this;
            getServer().getPluginManager().registerEvents(new CustomArrowShoot(), this);
            getServer().getPluginManager().registerEvents(new CustomArrowHitEntity(), this);
            getServer().getPluginManager().registerEvents(new CustomArrowHitBlock(), this);


            getCommand("blazing_give").setExecutor(new GetBlazingArrowCMD());
            getCommand("phantom_give").setExecutor(new GetPhantomArrowCMD());

        }catch(Throwable t){
            t.printStackTrace();
            getLogger().severe("me be like, :Marisa_exe:");
            getServer().shutdown();
        }
    }
}
