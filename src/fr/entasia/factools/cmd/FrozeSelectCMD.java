package fr.entasia.factools.cmd;

import fr.entasia.factools.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class FrozeSelectCMD implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (!(commandSender instanceof Player)) return true;
		Player p = (Player)commandSender;
		if (p.hasMetadata("spellFroze")){
			p.removeMetadata("spellFroze", Main.main);
			p.sendMessage("(§2Spells§f) Gel §4désactivé");
		} else {
			p.setMetadata("spellFroze", new FixedMetadataValue(Main.main, true));
			p.sendMessage("(§2Spells§f) Gel §2activé");

		}
		if (p.hasMetadata("spellGlide")) {
			p.removeMetadata("spellGlide",Main.main);
		}
		if (p.hasMetadata("spellFireball")) {
			p.removeMetadata("spellFireball",Main.main);
		}
		if (p.hasMetadata("spellHeal")) {
			p.removeMetadata("spellHeal",Main.main);
		}

		return true;
	}
}
