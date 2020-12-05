package fr.entasia.factools.cmd;

import fr.entasia.factools.utils.Mana;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManaCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) return true;

        if (strings[0].equalsIgnoreCase("set")) {
            Player p = ((Player) sender).getPlayer();
            Mana.setMana(p, Mana.getMana(p)+100);
            p.sendMessage("+100 mana ajoutés.");
        }

        if (strings[0].equalsIgnoreCase("get")) {
            Player p = ((Player) sender).getPlayer();
            String mana_amount = Integer.toString(Mana.getMana(p));
            p.sendMessage(mana_amount);
        }

        if (strings[0].equalsIgnoreCase("clear")) {
            Player p = ((Player) sender).getPlayer();
            Mana.setMana(p,0);
            p.sendMessage("Poof, plus de mana");
        }

        return true;
    }
}
