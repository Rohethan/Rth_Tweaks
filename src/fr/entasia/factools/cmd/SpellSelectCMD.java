package fr.entasia.factools.cmd;

import fr.entasia.factools.SpellTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpellSelectCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;

        Player p = (Player) commandSender;
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("heal")) {
                SpellTools.setCurrentSpell(p, SpellTools.HEAL);
            } else if (args[0].equalsIgnoreCase("fireball")) {
                SpellTools.setCurrentSpell(p, SpellTools.FIREBALL);
            } else if (args[0].equalsIgnoreCase("fly")) {
                SpellTools.setCurrentSpell(p, SpellTools.FLY);
            } else if (args[0].equalsIgnoreCase("froze")) {
                SpellTools.setCurrentSpell(p, SpellTools.FROZE);
            } else if (args[0].equalsIgnoreCase("meteor")) {
                SpellTools.setCurrentSpell(p, SpellTools.FROZE);
            }
        }
        return true;
    }
}
