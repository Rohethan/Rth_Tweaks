package me.rohethan.rth_tweaks.cmd;

import fr.entasia.apis.other.ItemBuilder;
import me.rohethan.rth_tweaks.customItems.BlazingArrow;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetBlazingArrowCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            ItemStack chad_lib = new ItemBuilder(Material.SPECTRAL_ARROW).fakeEnchant().name(BlazingArrow.ITEM_NAME.name).lore("he do be burning tho").build();
            chad_lib.setAmount(4);
            ((Player) commandSender).getInventory().addItem(chad_lib);
        }
        return true;
    }
}
