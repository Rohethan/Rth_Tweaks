package fr.entasia.factools.utils;

import fr.entasia.factools.Utils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ModifierChooser {

    /*
    pas besoin universelement de :
    1
    4
    11
    12
     */
    public ItemMeta modsSword(ItemMeta meta) {
        AttributeModifier mod = new AttributeModifier(UUID.randomUUID(), "spood", 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, mod);
        return meta;
    }


}
