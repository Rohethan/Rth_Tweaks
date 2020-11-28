package fr.entasia.factools.cmd;

import fr.entasia.apis.other.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveWandCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (sender.hasPermission("factions.givewandcmd")) {
            ItemStack wand = new ItemBuilder(Material.STICK).fakeEnchant().name("Baguette Magique").lore("Une baguette multifonctions").build();
            Player p = (Player) sender;
            p.getInventory().addItem(wand);
        } else {
            sender.sendMessage("!cPas de perms, pas de baguette !");
        }
        return true;
    }
}
