package fr.entasia.factools.listeners;

import fr.entasia.factools.utils.Mana;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ManaGainListeners implements Listener {

    @EventHandler
    public void ManaGainThroughKill(EntityDeathEvent e) {
        LivingEntity killedEntity = e.getEntity();
        if (killedEntity.getKiller() == null) return;
        Mana.setMana(killedEntity.getKiller(), Mana.getMana(killedEntity.getKiller())+ Mana.manaGain);
        String text = "+10 mana ! Mana actuel : " + Mana.getMana(killedEntity.getKiller());
        killedEntity.getKiller().sendActionBar(text);

    }
}
