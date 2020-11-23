package fr.entasia.factools.listeners;

import fr.entasia.factools.Main;
import fr.entasia.factools.utils.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

//--- Emplacement afk floobits -----
// :-)
// ---------------------------------

public class SpellListeners implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityToggleGlideEvent(EntityToggleGlideEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().hasMetadata("glide")) {

            if (!e.isGliding() && !e.getEntity().isOnGround()) {
                e.setCancelled(true);
            } else {

                e.getEntity().removeMetadata("glide", Main.main);

            }
        }

    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJump(PlayerMoveEvent e) {
        if (e.getPlayer().hasMetadata("tkeblo")) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if(e.getMaterial()==Material.STICK){
            Player p = e.getPlayer();

            Spell sp = Spell.getCurrentSpell(p);
            if(sp==null)return;

            if (sp== Spell.HEAL) { // ----------------------------------------------Sort Heal
                Location p_loc = e.getPlayer().getLocation();
                if (e.getPlayer().getHealth() == 20) {
                    e.getPlayer().sendMessage("Vous êtes déja en pleine forme !");
                } else {
                    double p_hlth = e.getPlayer().getHealth() + 5.0;
                    if (p_hlth > 20.0) {
                        p_hlth = 20.0;
                    }
                    e.getPlayer().setHealth(p_hlth);
                    e.getPlayer().getWorld().spawnParticle(Particle.SNEEZE, p_loc, 250, 0.0, 0.0, 0.0, 0.125);
                    e.getPlayer().getWorld().spawnParticle(Particle.TOTEM, p_loc, 250, 0.0, 1.0, 0.0, 0.25);
                    e.getPlayer().getWorld().playSound(p_loc, Sound.BLOCK_BEACON_POWER_SELECT, (float) 1.0, (float) 1.5);
                }
            }else if (sp== Spell.FIREBALL) { //--------------------------------------sort Fireball
                Location p_loc = e.getPlayer().getLocation();
                e.getPlayer().getWorld().playSound(p_loc, Sound.ITEM_FIRECHARGE_USE, (float)1.0, (float)1.0);
                e.getPlayer().launchProjectile(Fireball.class);
            }else if (sp== Spell.FLY) { //--------------------------------------sort Gli.. HUM, fly pardon  hehe boi
                if (e.getPlayer().hasMetadata("glide")){
                    e.getPlayer().sendMessage("Tu voles déja!");
                } else {
                    e.getPlayer().setMetadata("glide", new FixedMetadataValue(Main.main, true));
                    Location p_loc = e.getPlayer().getLocation();
                    e.getPlayer().getWorld().playSound(p_loc, Sound.ENTITY_BAT_TAKEOFF, (float) 1.0, (float) 1.5);
                    e.getPlayer().getWorld().spawnParticle(Particle.SMOKE_LARGE, p_loc,250, 0.0,0.0,0.0, 0.125);
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
            }else if (sp==Spell.FROZE) { //-------------------------------------sort Froze (gel)
                Location p_loc = e.getPlayer().getLocation();
                Entity target = e.getPlayer().getTargetEntity(6);
                e.getPlayer().setJumping(false);
                if(target instanceof Player){
                    ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 250 ));
                    ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255 ));
                    e.getPlayer().getWorld().playSound(p_loc, Sound.BLOCK_GLASS_BREAK, (float) 1.0, (float) 1.0);
                }else{
                    e.getPlayer().sendMessage("§cVise un joueur espèce de bigleux");
                }
            } else if (sp== Spell.METEOR) { //----------------------------------sort Meteor

                Block b = p.getTargetBlock(50);
                if(b==null){
                    p.sendMessage("§cRegarde un block !");
                    return;
                }
                Location loc;
                for(int i=0;i<1;i++){
                    loc = b.getLocation();
                    loc.setY(loc.getY()+50);
                    if(loc.getY()>255)loc.setY(255);
                    loc.setX(loc.getX()+Main.r.nextInt(20)-10);
                    loc.setZ(loc.getZ()+Main.r.nextInt(20)-10);

                    Vector v = new Vector(Main.r.nextDouble()*2-1, -1, Main.r.nextDouble()*2-1);
                    Fireball fb = loc.getWorld().spawn(loc, Fireball.class);
                    fb.setVelocity(v);
                    fb.setDirection(new Vector(0, 0, 0));
                }

            }
        }
    }
}
