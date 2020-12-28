package fr.entasia.factools.others;

import fr.entasia.factools.utils.Mana;
import fr.entasia.factools.utils.Spell;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class ManaHUD extends BukkitRunnable {
    @Override
    public void run() {
        Spell sp;
        for(Player p : Bukkit.getOnlinePlayers()){
            sp = Spell.getCurrentSpell(p);
            if(sp==null)continue;
            send(p, sp);
        }
    }

    public static void send(Player p){
        Spell sp = Spell.getCurrentSpell(p);
        if(sp==null)return;
        send(p, sp);
    }

    public static void send(Player p, Spell sp){
        p.sendActionBar("Mana : "+ Mana.getMana(p)+" | Spell : "+sp.name());
    }

}
