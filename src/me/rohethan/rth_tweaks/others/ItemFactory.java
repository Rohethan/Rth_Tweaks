package me.rohethan.rth_tweaks.others;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// will be quite litteraly a cheap clone of fr.entasia.apis.other.ItemBuilder
// while waiting for 1.17 support (target version)

public class ItemFactory {
    public ItemStack item;
    public ItemMeta meta;

    public ItemFactory(Material type) {this(type, 1);}

    public ItemFactory(Material type, int count) {
        this(new ItemStack(type, count));
    }

    public ItemFactory(ItemStack itemStack) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    public ItemFactory(Material type, int count, ItemMeta meta) {
        this.item = new ItemStack(type, count);
        this.meta = meta;
    }

    // fonction dans ordre d'importance

    public ItemFactory name(String name) {
        this.meta.setDisplayName(name);
        return this;
    }
}
