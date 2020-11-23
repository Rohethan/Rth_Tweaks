package fr.entasia.factools.listeners;
import fr.entasia.factools.Main;
import fr.entasia.factools.SpellTools;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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

public class Spells implements Listener {

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

          SpellTools sp = SpellTools.getCurrentSpell(p);
          if(sp==null)return;

          if (sp== SpellTools.HEAL) { // ----------------------------------------------Sort Heal
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
          }else if (sp== SpellTools.FIREBALL) { //--------------------------------------sort Fireball
              Location p_loc = e.getPlayer().getLocation();
              e.getPlayer().getWorld().playSound(p_loc, Sound.ITEM_FIRECHARGE_USE, (float)1.0, (float)1.0);
              e.getPlayer().launchProjectile(Fireball.class);
          }else if (sp== SpellTools.FLY) { //--------------------------------------sort Gli.. HUM, fly pardon  hehe boi
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
          }else if (sp== SpellTools.FROZE) { //-------------------------------------sort Froze (gel)
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
          } else if (sp== SpellTools.METEOR) { //----------------------------------sort Meteor
              Location p_loc = e.getPlayer().getLocation();
              p_loc.setY(255.0);
              e.getPlayer().getWorld().spawnEntity(p_loc, EntityType.FIREBALL).setRotation((float)0.0,(float)90.0);
          }
      }
    }
}
