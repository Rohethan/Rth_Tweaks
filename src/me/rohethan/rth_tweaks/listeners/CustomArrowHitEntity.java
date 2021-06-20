package me.rohethan.rth_tweaks.listeners;

import me.rohethan.rth_tweaks.customItems.BlazingArrow;
import me.rohethan.rth_tweaks.customItems.PhantomArrow;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class CustomArrowHitEntity implements Listener {

    @EventHandler
    public static void onHit(ProjectileHitEvent e) {
        System.out.println(e.getEntity().getName());

        Entity arrow = e.getEntity();
        if (arrow.getName().equals(BlazingArrow.PROJ_NAME.name)) {
            arrow.getWorld().createExplosion(arrow.getLocation(), 1f, true,false);
            arrow.remove();
        }
        if (arrow.getName().equals(PhantomArrow.PROJ_NAME.name)) {
            arrow.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, arrow.getLocation(), 50 , 0,0,0, 0.25);
            arrow.remove();
        }
    }
}
