package fr.entasia.factools.utils;

import com.google.common.collect.Multimap;
import fr.entasia.factools.Utils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ModifierChooser {
    /*
    0 GENERIC_MAX_HEALTH("generic.max_health"),
    1 GENERIC_FOLLOW_RANGE("generic.follow_range"),
    2 GENERIC_KNOCKBACK_RESISTANCE("generic.knockback_resistance"),
    3 GENERIC_MOVEMENT_SPEED("generic.movement_speed"),
    4 GENERIC_FLYING_SPEED("generic.flying_speed"),
    5 GENERIC_ATTACK_DAMAGE("generic.attack_damage"),
    6 GENERIC_ATTACK_KNOCKBACK("generic.attack_knockback"),
    7 GENERIC_ATTACK_SPEED("generic.attack_speed"),
    8 GENERIC_ARMOR("generic.armor"),
    9 GENERIC_ARMOR_TOUGHNESS("generic.armor_toughness"),
    10 GENERIC_LUCK("generic.luck"),
    11 HORSE_JUMP_STRENGTH("horse.jump_strength"),
    12 ZOMBIE_SPAWN_REINFORCEMENTS("zombie.spawn_reinforcements");
     */

    /*
    pas besoin universelement de :
    1
    4
    11
    12
     */
    public static ItemMeta modsSword(ItemMeta meta) {
        AttributeModifier mod = new AttributeModifier(UUID.randomUUID(), "come here narcos", 0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
        ItemMeta help = (ItemMeta) meta.getAttributeModifiers();
        int index = Utils.getRandom(3,8);
        if (index == 4) {index = 5;}
        System.out.println(index);
        System.out.println(Attribute.values()[index]);
        meta.addAttributeModifier(Attribute.values()[index], mod);


        return meta;
    }

    public static Multimap<Attribute, AttributeModifier> modsChestplate(Multimap<Attribute, AttributeModifier> activemods) {


        return activemods;
    }


}
