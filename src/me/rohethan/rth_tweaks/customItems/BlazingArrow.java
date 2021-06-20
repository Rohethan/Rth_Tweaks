package me.rohethan.rth_tweaks.customItems;


import fr.entasia.apis.other.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum BlazingArrow {
    PROJ_NAME ("#he_do_be_blazing_tho"),
    ITEM_NAME("§6Blazing Arrow");

    public String name;
    BlazingArrow(String name) {
        this.name = name;
    }

    public ItemStack getitem() {
        ItemStack e = new ItemBuilder(Material.SPECTRAL_ARROW).fakeEnchant().name(BlazingArrow.ITEM_NAME.name).lore("he do be burning tho").build();
        return e;
    }

}
