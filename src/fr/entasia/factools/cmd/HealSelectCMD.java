package fr.entasia.factools.cmd;

import fr.entasia.factools.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class HealSelectCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player p = (Player)commandSender;

        if (p.hasMetadata("spellHeal")) {
            p.removeMetadata("spellHeal", Main.main);
            p.sendMessage("(§2Spells§f) Heal §4désactivé");
        } else {
            p.setMetadata("spellHeal", new FixedMetadataValue(Main.main, true));
            p.sendMessage("(§2Spells§f) Heal §2activé");
        }
        //desac autres spells
        if (p.hasMetadata("spellGlide")) {
            p.removeMetadata("spellGlide",Main.main);
        }
        if (p.hasMetadata("spellFireball")) {
            p.removeMetadata("spellFireball",Main.main);
        }
        return true;
    }
}
