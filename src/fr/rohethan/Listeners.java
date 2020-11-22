package fr.rohethan;

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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class Listeners implements Listener {

    boolean p_gld = false;

    @EventHandler(ignoreCancelled = true)
    public void onEntityToggleGlideEvent(EntityToggleGlideEvent event) {
        Entity ent = event.getEntity();
        if (ent instanceof Player && p_gld) {
            event.setCancelled(true);
        }
        if (ent.isOnGround()) {
            p_gld = false;
        }
    }


    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if(e.getHand()!= EquipmentSlot.HAND)return;
        if (e.getItem() == null) return;


        if (e.getMaterial() == Material.STICK) { // ----------------------------------------------Sort Heal
            Location p_loc = e.getPlayer().getLocation();
            if (e.getPlayer().getHealth() == 20) {
                    e.getPlayer().sendMessage("Vous êtes déja en pleine forme !");
            } else {
                double p_hlth = e.getPlayer().getHealth() + 5.0;
                if (p_hlth > 20.0) {
                    p_hlth = 20.0;
                }
                e.getPlayer().setHealth(p_hlth);
                e.getPlayer().spawnParticle(Particle.SNEEZE, p_loc,250, 0.0,0.0,0.0, 0.125);
                e.getPlayer().spawnParticle(Particle.TOTEM, p_loc,250, 0.0,1.0,0.0, 0.25);
                e.getPlayer().playSound(p_loc, Sound.BLOCK_BEACON_POWER_SELECT, (float)1.0, (float)1.5);
            }
        }


        if (e.getMaterial() == Material.SPECTRAL_ARROW) { //--------------------------------------sort Fireball
            Location p_loc = e.getPlayer().getLocation();
            e.getPlayer().playSound(p_loc, Sound.ITEM_FIRECHARGE_USE, (float)1.0, (float)1.0);
            e.getPlayer().launchProjectile(Fireball.class);
        }


        if (e.getMaterial() == Material.GLOWSTONE_DUST) { //--------------------------------------sort Glide
            if (p_gld){
                e.getPlayer().sendMessage("Tu voles déja!");
            } else {
                p_gld = true;
                Location p_loc = e.getPlayer().getLocation();
                e.getPlayer().spawnParticle(Particle.SMOKE_LARGE,p_loc,250, 0.0,0.0,0.0, 0.125);
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
