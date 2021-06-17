package me.rohethan.rth_tweaks.listeners;

import me.rohethan.rth_tweaks.customItems.BlazingArrow;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ArrowBlazing implements Listener {

    @EventHandler
    public static void blazingArrowGoesBRRRR(EntityShootBowEvent e) {
        ItemStack consumable = e.getConsumable();
        if (consumable.getData().getItemType() == Material.SPECTRAL_ARROW && consumable.getItemMeta().getDisplayName() == BlazingArrow.ITEM_NAME.name) {
            e.getProjectile().setVisualFire(true);
            e.getProjectile().setCustomName(BlazingArrow.PROJ_NAME.name);
            e.setConsumeItem(true);
        }
    }

    @EventHandler
    public static void hitWithBlazingArrow(ProjectileHitEvent e ) {
        Projectile p = e.getEntity();
        World w = p.getWorld();
        if (!(e.getEntity().getName() == BlazingArrow.PROJ_NAME.name)) return;
        if (!(e.getHitEntity() == null)) {
            e.getHitEntity().setFireTicks(200);
            e.getHitEntity().getWorld().createExplosion(p.getLocation(), 1f, true, false);
        } else w.createExplosion(p.getLocation(), 2f, true, false); p.setGlowing(false);
        }
}