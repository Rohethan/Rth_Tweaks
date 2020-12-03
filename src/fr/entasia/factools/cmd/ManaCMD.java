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

        if (strings[0] == "set") {
            Player p = ((Player) sender).getPlayer();
            Mana.setMana(p, Integer.parseInt(strings[1]));
        }

        if (strings[0] == "get") {
            Player p = ((Player) sender).getPlayer();
            p.sendMessage(Integer.toString(Mana.getMana(p)));
        }

        return true;
    }
}
