package fr.entasia.factools.listeners;

import fr.entasia.apis.utils.ItemUtils;
import fr.entasia.factools.Main;
import fr.entasia.factools.utils.Mana;
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
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpellListeners implements Listener {
    /*
    @EventHandler
    public void doesPlayerHaveTheWAND(PlayerSwapHandItemsEvent e) {
        if (ItemUtils.is(e.getPlayer().getActiveItem(), Material.STICK,"§5Baguette Magique")) {

            e.getPlayer().sendActionBar(String.format("Spell: %s | Mana: %s",Spell.getCurrentSpell(e.getPlayer()),Mana.getMana(e.getPlayer())));
        }

    }

     */

    @EventHandler(ignoreCancelled = true) // ---------------------------------------------- disable "no elytra, no glide" if spell glide used
    public void onEntityToggleGlideEvent(EntityToggleGlideEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().hasMetadata("glide")) {
            if (!e.isGliding() && !e.getEntity().isOnGround()) {
                e.setCancelled(true);
            } else {
                e.getEntity().removeMetadata("glide", Main.main);
            }
        }
    }

    @EventHandler(ignoreCancelled = true) // ---------------------------------------------- disable mouvment completely if freezed (W.I.P)
    public void onPlayerJump(PlayerMoveEvent e) {
        if (e.getPlayer().hasMetadata("tkeblo")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (ItemUtils.is(e.getItem(), Material.STICK, "§5Baguette Magique")) {

            // ---------------- SpellSwitcher2000(tm)
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                Spell sp = Spell.getCurrentSpell(e.getPlayer());
                int nextspell;
                if (sp == null) nextspell = 0;
                else {
                    nextspell = sp.id + 1;
                    if (nextspell == 5) nextspell = 0;
                }
                Spell.setCurrentSpell(e.getPlayer(), nextspell);
                e.getPlayer().sendActionBar(String.format("Spell : %s", Spell.getCurrentSpell(e.getPlayer())));
            } else {


                Player p = e.getPlayer();
                Spell sp = Spell.getCurrentSpell(p);
                if (sp == null) return; // nothing if player don't have equipped spell

                if (sp == Spell.HEAL) { // ----------------------------------------------Spell Heal
                    /*
                    if (!(Mana.getMana(e.getPlayer()) <= 20)) {
                        e.getPlayer().sendMessage("§cPas assez de mana !");
                        return;
                    }
                    Mana.setMana(e.getPlayer(),(Mana.getMana(e.getPlayer()) - 20));
                     */

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


                } else if (sp == Spell.FIREBALL) { //--------------------------------------sort Fireball
                    Location p_loc = e.getPlayer().getLocation();
                    e.getPlayer().getWorld().playSound(p_loc, Sound.ITEM_FIRECHARGE_USE, (float) 1.0, (float) 1.0);
                    e.getPlayer().launchProjectile(Fireball.class);
                    e.getPlayer().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p_loc,250,0.0,0.0,0.0,0.125);


                } else if (sp == Spell.FLY) { //--------------------------------------sort Gli.. HUM, fly pardon  hehe boi
                    if (e.getPlayer().hasMetadata("glide")) {
                        e.getPlayer().sendMessage("Tu voles déja!");
                    } else {
                        e.getPlayer().setMetadata("glide", new FixedMetadataValue(Main.main, true));
                        Location p_loc = e.getPlayer().getLocation();
                        e.getPlayer().getWorld().playSound(p_loc, Sound.ENTITY_BAT_TAKEOFF, (float) 1.0, (float) 1.5);
                        e.getPlayer().getWorld().spawnParticle(Particle.SMOKE_LARGE, p_loc, 250, 0.0, 0.0, 0.0, 0.125);
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


                } else if (sp == Spell.FROZE) { //-------------------------------------sort Froze (W.I.P)
                    Location p_loc = e.getPlayer().getLocation();
                    Entity target = e.getPlayer().getTargetEntity(6);
                    if (target instanceof Player) {
                        e.getPlayer().setJumping(false);
                        ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 250));
                        ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255));
                        e.getPlayer().getWorld().playSound(p_loc, Sound.BLOCK_GLASS_BREAK, (float) 1.0, (float) 1.0);
                    } else {
                        e.getPlayer().sendMessage("§cVise un joueur espèce de bigleux");
                    }


                } else if (sp == Spell.METEOR) { //----------------------------------sort Meteor (W.I.P)
                    Block b = p.getTargetBlock(50);
                    if (b == null || b.getType() == Material.AIR) {
                        p.sendMessage("§cRegarde un bloc");
                        return;
                    }
                    Location targetLoc = b.getLocation();

                    Location pLoc = p.getLocation();
                    pLoc.setY(255);

                    Vector goto_target = targetLoc.toVector().subtract(pLoc.toVector());

                    Fireball fb = p.getWorld().spawn(pLoc, Fireball.class);
                    fb.setDirection(goto_target);

                } else if (sp == Spell.SPEED) { //----------------------------------------Spell speed
                    if(e.getPlayer().hasPotionEffect(PotionEffectType.SPEED)){
                        e.getPlayer().sendMessage("Tu as déjà un effet de speed !");
                    }else{
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 5));
                    }
                }
            }
        }
    }
}
