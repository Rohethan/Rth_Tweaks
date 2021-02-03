package fr.entasia.factools.listeners;

import fr.entasia.factools.Main;
import fr.entasia.factools.utils.ModifierChooser;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

public class QualityCraftingListeners implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack craftOutput = e.getInventory().getResult();
        ItemStack craftResult = e.getRecipe().getResult();
        if (craftOutput == null) return;
        if (craftOutput.getType().toString().endsWith("SWORD")){
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack hey = craftOutput;
                    ItemMeta meta = hey.getItemMeta();
                    ItemMeta mods = ModifierChooser.modsSword(meta);
                    hey.setItemMeta(mods);


                    e.getView().setCursor(hey);
                }
            }.runTaskLater(Main.main, 1);

        }
        if (craftOutput.getType().toString().endsWith("CHESTPLATE")){
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemMeta meta = craftOutput.getItemMeta();
                    AttributeModifier mod = new AttributeModifier(UUID.nameUUIDFromBytes("ID".getBytes()),":)",0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
                    meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, mod);
                    craftOutput.setItemMeta(meta);
                    e.getView().setCursor(craftOutput);
                }
            }.runTaskLater(Main.main, 1);

        }
    }
}
