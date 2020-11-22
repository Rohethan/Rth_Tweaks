package fr.rohethan.listeners;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.rohethan.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;


public class Spells implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityToggleGlideEvent(EntityToggleGlideEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().hasMetadata("glide")) {
            if (e.isGliding()) {
                e.setCancelled(true);
            } else {
                e.getEntity().removeMetadata("glide", Main.main);
            }
        }
    }


    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;

        if (e.getItem() != null && e.getMaterial() == Material.STICK) { // ----------------------------------------------Sort Heal
            Location p_loc = e.getPlayer().getLocation();
            if (e.getPlayer().getHealth() == 20) {
                e.getPlayer().sendMessage("Vous êtes déja en pleine forme !");
            } else {
                double p_hlth = e.getPlayer().getHealth() + 5.0;
                if (p_hlth > 20.0) {
                    p_hlth = 20.0;
                }
                e.getPlayer().setHealth(p_hlth);
                e.getPlayer().spawnParticle(Particle.SNEEZE, p_loc, 250, 0.0, 0.0, 0.0, 0.125);
                e.getPlayer().spawnParticle(Particle.TOTEM, p_loc, 250, 0.0, 1.0, 0.0, 0.25);
                e.getPlayer().playSound(p_loc, Sound.BLOCK_BEACON_POWER_SELECT, (float) 1.0, (float) 1.5);
            }
        }else if (e.getItem() != null && e.getMaterial() == Material.SPECTRAL_ARROW) { //--------------------------------------sort Fireball
            Location p_loc = e.getPlayer().getLocation();
            e.getPlayer().playSound(p_loc, Sound.ITEM_FIRECHARGE_USE, (float)1.0, (float)1.0);
            e.getPlayer().launchProjectile(Fireball.class);
        }else if (e.getItem() != null && e.getMaterial() == Material.GLOWSTONE_DUST) { //--------------------------------------sort Glide
            if (e.getPlayer().hasMetadata("glide")){
                e.getPlayer().sendMessage("Tu voles déja!");
            } else {
                e.getPlayer().setMetadata("glide", new FixedMetadataValue(Main.main, true));
                Location p_loc = e.getPlayer().getLocation();
                e.getPlayer().spawnParticle(Particle.SMOKE_LARGE, p_loc,250, 0.0,0.0,0.0, 0.125);
                new BukkitRunnable() {
                    public void run() {
                        e.getPlayer().setVelocity(new Vector(0, 1.0, 0));
                    }
                }.runTask(Main.main);
                new BukkitRunnable() {
                    public void run() {
                        e.getPlayer().setGliding(true);
                    }
                }.runTaskLater(Main.main, 16);
            }
        }
    }
}
