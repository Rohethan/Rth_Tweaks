package fr.entasia.factools.listeners;

import fr.entasia.factools.utils.Mana;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ManaGainListeners implements Listener {

    @EventHandler
    public void ManaGainThroughKill(EntityDeathEvent e) {
        LivingEntity killed_entity = e.getEntity();
        if (!(killed_entity.getKiller() instanceof Player)) return;
        Mana.setMana(killed_entity.getKiller(), Mana.getMana(killed_entity.getKiller())+10);
    }
}
