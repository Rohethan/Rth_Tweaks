package fr.entasia.factools.cmd;

import fr.entasia.factools.utils.Spell;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpellSelectCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;
        if(sender.hasPermission("factions.spellcmd")){
            Player p = (Player) sender;
            if (args.length==0) {
                p.sendMessage("§cSorts disponibles :");
                for (Spell sp : Spell.values()) {
                    p.sendMessage("§c- " + sp.name());
                }
            }else {
                try {
                    Spell sp = Spell.valueOf(args[0].toUpperCase()); // throw IllegalArgumentException if not a valid spell
                    Spell.setCurrentSpell(p, sp);
                    p.sendMessage("§aTu as choisi le sort "+sp.name()+" !");
                } catch (IllegalArgumentException e) {
                    p.sendMessage("§cCe spell n'existe pas !");
                }
            }
        }else sender.sendMessage("§cTu n'as pas la permission d'éxecuter cette commande !");
        return true;
    }
}
