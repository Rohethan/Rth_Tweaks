package fr.entasia.factools.listeners;

import fr.entasia.apis.utils.ItemUtils;
import fr.entasia.factools.Main;
import fr.entasia.factools.Utils;
import fr.entasia.factools.utils.Mana;
import fr.entasia.factools.utils.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MagicListeners implements Listener {

/* Obsolète, replacé par affichage constant  ### REMPLACEMENT STILL WIP
    @EventHandler
    public void doesPlayerHaveTheWAND(PlayerItemHeldEvent e) {
        System.out.println(e.getNewSlot());
        System.out.println(e.getPreviousSlot());
        int slotid = e.getNewSlot();
        ItemStack item = e.getPlayer().getInventory().getItem(slotid);
        if (ItemUtils.is(item, Material.STICK, "§5Baguette Magique")) {
            if (Spell.getCurrentSpell(e.getPlayer()) == null) return;
            String hudText = "Mana : "+Mana.getMana(e.getPlayer())+" | Spell : "+Spell.getCurrentSpell(e.getPlayer()).name();
            e.getPlayer().sendActionBar(hudText);
        }
    }
*/
    @EventHandler
    public void ManaGainThroughKill(EntityDeathEvent e) {
        LivingEntity killedEntity = e.getEntity();
        if (killedEntity.getKiller() == null) return;
        Mana.setMana(killedEntity.getKiller(), Mana.getMana(killedEntity.getKiller())+ Mana.manaGain);
        String text = "+10 mana ! Mana actuel : " + Mana.getMana(killedEntity.getKiller());
        killedEntity.getKiller().sendActionBar(text);

    }

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

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (ItemUtils.is(e.getItem(),Material.STICK,"§5Baguette Magique")) {

            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                Spell sp = Spell.getCurrentSpell(e.getPlayer());
                int nextspell;
                if (sp == null) nextspell = 0;
                else {
                    nextspell = sp.id + 1;
                    if (nextspell == 6) nextspell = 0;
                }
                Spell.setCurrentSpell(e.getPlayer(), nextspell);
                e.getPlayer().sendActionBar(String.format("Spell : %s", Spell.getCurrentSpell(e.getPlayer())));
            } else {
                Player p = e.getPlayer();
                Spell sp = Spell.getCurrentSpell(p);
                int currentMana = Mana.getMana(p);
                if (sp == null) return;

                if (sp == Spell.HEAL) { // ----------------------------------------------Spell Heal
                    if (currentMana < 20) {
                        p.sendMessage("§cPas assez de mana !");
                        return;
                    }
                    Mana.setMana(p,(currentMana - 20));

                    Location p_loc = p.getLocation();
                    if (p.getHealth() == 20) {
                        p.sendMessage("Vous êtes déja en pleine forme !");
                    } else {
                        double p_hlth = e.getPlayer().getHealth() + 6.0;
                        if (p_hlth > 20.0) {
                            p_hlth = 20.0;
                        }
                        p.setHealth(p_hlth);
                        p.getWorld().spawnParticle(Particle.SNEEZE, p_loc, 250, 0.0, 0.0, 0.0, 0.125);
                        p.getWorld().spawnParticle(Particle.TOTEM, p_loc, 250, 0.0, 1.0, 0.0, 0.25);
                        p.getWorld().playSound(p_loc, Sound.BLOCK_BEACON_POWER_SELECT, (float) 1.0, (float) 1.5);
                    }


                } else if (sp == Spell.FIREBALL) { //--------------------------------------sort Fireball
                    System.out.println(Mana.getMana(p));
                    if (currentMana < 20) {
                        p.sendMessage("§cPas assez de mana !");
                        return;
                    }
                    Mana.setMana(p,(Mana.getMana(p) - 20));
                    Location p_loc = e.getPlayer().getLocation();
                    p.getWorld().playSound(p_loc, Sound.ITEM_FIRECHARGE_USE, (float) 1.0, (float) 1.0);
                    p.launchProjectile(Fireball.class);
                    p.getWorld().spawnParticle(Particle.FLAME, p_loc,250,0.0,0.0,0.0,0.125);


                } else if (sp == Spell.FLY) { //--------------------------------------sort fly
                    if (currentMana < 20) {
                        p.sendMessage("§cPas assez de mana !");
                        return;
                    }
                    if (p.hasMetadata("glide")) {
                        p.sendMessage("Tu voles déja!");
                    } else {
                        Mana.setMana(p,(currentMana - 20));
                        p.setMetadata("glide", new FixedMetadataValue(Main.main, true));
                        Location p_loc = p.getLocation();
                        p.getWorld().playSound(p_loc, Sound.ENTITY_BAT_TAKEOFF, (float) 1.0, (float) 1.5);
                        p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p_loc, 250, 0.0, 0.0, 0.0, 0.125);
                        new BukkitRunnable() {
                            public void run() {
                                p.setVelocity(new Vector(0, 1.0, 0));
                            }
                        }.runTask(Main.main);
                        new BukkitRunnable() {
                            public void run() {
                                p.setGliding(true);
                                Vector v = p.getLocation().getDirection().setY(0);
                                v.add(v);
                                p.setVelocity(v.normalize());
                            }
                        }.runTaskLater(Main.main, 16);
                    }


                } else if (sp == Spell.FROZE) { //-------------------------------------sort Froze (W.I.P)
                    if (currentMana < 50) {
                        p.sendMessage("§cPas assez de mana !");
                        return;
                    }
                    Location p_loc = p.getLocation();
                    Entity target = p.getTargetEntity(6);
                    if (target instanceof Player) {
                        ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 250));
                        ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255));
                        e.getPlayer().getWorld().playSound(p_loc, Sound.BLOCK_GLASS_BREAK, (float) 1.0, (float) 1.0);
                        Mana.setMana(p,(currentMana - 50));
                    } else {
                        p.sendMessage("§cVise un joueur espèce de bigleux");
                    }

                } else if (sp == Spell.SPEED) { //----------------------------------------Spell speed
                    if (currentMana < 20) {
                        p.sendMessage("§cPas assez de mana !");
                        return;
                    }
                    if(p.hasPotionEffect(PotionEffectType.SPEED)){
                        p.sendMessage("Tu as déjà un effet de speed !");
                    }else{
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 5));
                        Mana.setMana(p,currentMana - 20);
                    }
                } else if (sp == Spell.ZEUS) {
                    if (Mana.getMana(p) < 250) {
                        p.sendMessage("§cPas assez de mana !");
                        return;
                    }
                    Block b = p.getTargetBlock(50);
                    //if (b.getType() == null) return;
                    if (b.getType() == Material.AIR) {
                        p.sendMessage("Vise un block !");
                    } else {
                        p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, b.getLocation(), 10000, 0, 0, 0, 0.25);
                        p.getWorld().spawnParticle(Particle.END_ROD, b.getLocation(), 10000, 0, 0, 0, 0.50);
                        p.getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, (float) 1.0, (float) 0.975);
                        p.getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, (float) 1.0, (float) 0.975);
                        new BukkitRunnable() {
                            public void run() {
                                for (int i = 0; i < 64; i++) {
                                    Location strike = b.getLocation().add(Utils.getRandom(-8, 8), 0, Utils.getRandom(-8, 8));
                                    p.getWorld().spawnFallingBlock(strike, b.getBlockData());
                                    p.getWorld().strikeLightning(strike);
                                    p.getWorld().createExplosion(strike, (float) 1.0,true);

                                }
                            }
                        }.runTaskLater(Main.main, 50);
                        Mana.setMana(e.getPlayer(), (Mana.getMana(e.getPlayer()) - 250));
                    }
                }
            }
        }
    }
}
