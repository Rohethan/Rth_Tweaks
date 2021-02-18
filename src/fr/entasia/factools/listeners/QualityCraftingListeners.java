package fr.entasia.factools.listeners;

import fr.entasia.factools.Utils;
import fr.entasia.factools.utils.ModifierChooser;
import net.minecraft.server.v1_16_R3.EnumArmorMaterial;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QualityCraftingListeners implements Listener {

    // pls do not judge, nms is hard :)
    public static HashMap<String, EnumItemSlot> armorPieces = new HashMap<>();
    static{
        armorPieces.put("BOOTS", EnumItemSlot.FEET);
        armorPieces.put("LEGGINGS", EnumItemSlot.LEGS);
        armorPieces.put("CHESTPLATE", EnumItemSlot.CHEST);
        armorPieces.put("HELMET", EnumItemSlot.HEAD);
    }

    public static int getPoints(Material armor){ // any negative value is fail
        String armorStr = armor.toString().toUpperCase();
        for(EnumArmorMaterial nms : EnumArmorMaterial.values()){
            if(armorStr.startsWith(nms.name())){
                for(Map.Entry<String, EnumItemSlot> e : armorPieces.entrySet()){
                    if(armorStr.endsWith(e.getKey()))return nms.b(e.getValue());
                }
                return -1;
            }
        }
        return -2;
    }
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack craftOutput = e.getInventory().getResult();
        if(craftOutput==null)return;

        if (craftOutput.getType().toString().endsWith("CHESTPLATE")) {
            EquipmentSlot slot = EquipmentSlot.CHEST;
            ItemMeta meta = craftOutput.getItemMeta();
            //armor first
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.nameUUIDFromBytes("armorprotbaseyay".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", getPoints(craftOutput.getType()), AttributeModifier.Operation.ADD_NUMBER, slot));
            if (craftOutput.getType().toString().startsWith("DIAMOND")) {meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.nameUUIDFromBytes("armortoughnessbase".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", 2, AttributeModifier.Operation.ADD_NUMBER, slot));}
            //you got luck ! here. have some mods
            int rollForCharisma = (int) Utils.getRandom(1,4);
            System.out.println(rollForCharisma);
            if (rollForCharisma == 3) {
                craftOutput.setItemMeta(ModifierChooser.modsArmor(meta, slot));
                e.getView().setCursor(craftOutput);
            }
        }
        if (craftOutput.getType().toString().endsWith("LEGGINGS")) {
            EquipmentSlot slot = EquipmentSlot.LEGS;
            ItemMeta meta = craftOutput.getItemMeta();
            //armor first
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.nameUUIDFromBytes("armorprotbaseyay".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", getPoints(craftOutput.getType()), AttributeModifier.Operation.ADD_NUMBER, slot));
            if (craftOutput.getType().toString().startsWith("DIAMOND")) {meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.nameUUIDFromBytes("armortoughnessbase".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", 2, AttributeModifier.Operation.ADD_NUMBER, slot));}
            //you got luck ! here. have some mods
            int rollForCharisma = (int) Utils.getRandom(1,4);
            System.out.println(rollForCharisma);
            if (rollForCharisma == 3) {
                craftOutput.setItemMeta(ModifierChooser.modsArmor(meta, slot));
                e.getView().setCursor(craftOutput);
            }
        }
        if (craftOutput.getType().toString().endsWith("HELMET")) {
            EquipmentSlot slot = EquipmentSlot.HEAD;
            ItemMeta meta = craftOutput.getItemMeta();
            //armor first
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.nameUUIDFromBytes("armorprotbaseyay".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", getPoints(craftOutput.getType()), AttributeModifier.Operation.ADD_NUMBER, slot));
            if (craftOutput.getType().toString().startsWith("DIAMOND")) {meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.nameUUIDFromBytes("armortoughnessbase".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", 2, AttributeModifier.Operation.ADD_NUMBER, slot));}
            //you got luck ! here. have some mods
            int rollForCharisma = (int) Utils.getRandom(1,4);
            System.out.println(rollForCharisma);
            if (rollForCharisma == 3) {
                craftOutput.setItemMeta(ModifierChooser.modsArmor(meta, slot));
                e.getView().setCursor(craftOutput);
            }
        }
        if (craftOutput.getType().toString().endsWith("BOOTS")) {
            EquipmentSlot slot = EquipmentSlot.FEET;
            ItemMeta meta = craftOutput.getItemMeta();
            //armor first
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.nameUUIDFromBytes("armorprotbaseyay".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", getPoints(craftOutput.getType()), AttributeModifier.Operation.ADD_NUMBER, slot));
            if (craftOutput.getType().toString().startsWith("DIAMOND")) {meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.nameUUIDFromBytes("armortoughnessbase".getBytes()), "Si vous pouvez lire ca, vous devriez devenir dev sur le serveur", 2, AttributeModifier.Operation.ADD_NUMBER, slot));}
            //you got luck ! here. have some mods
            int rollForCharisma = (int) Utils.getRandom(1,4);
            System.out.println(rollForCharisma);
            if (rollForCharisma == 3) {
                craftOutput.setItemMeta(ModifierChooser.modsArmor(meta, slot));
                e.getView().setCursor(craftOutput);
            }
        }

    }
}
