package me.rohethan.rth_tweaks.listeners;

import me.rohethan.rth_tweaks.customItems.BlazingArrow;
import me.rohethan.rth_tweaks.customItems.PhantomArrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class CustomArrowShoot implements Listener {

    @EventHandler
    public static void shoot(EntityShootBowEvent e) {
        Entity arrow = e.getProjectile();
        if (e.getConsumable().getItemMeta().getDisplayName().equals(BlazingArrow.ITEM_NAME.name)) {
            arrow.setVisualFire(true);
            arrow.setGlowing(true);
            arrow.setCustomName(BlazingArrow.PROJ_NAME.name);
        }
        if (e.getConsumable().getItemMeta().getDisplayName().equals(PhantomArrow.ITEM_NAME.name)) {
            arrow.setCustomName(PhantomArrow.PROJ_NAME.name);
            arrow.setGravity(false);
        }
    }
}
