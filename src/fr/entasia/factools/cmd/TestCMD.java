package fr.entasia.factools.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            float p_xp = ((Player) commandSender).getTotalExperience();
            commandSender.sendMessage(String.valueOf(p_xp));
        }

        return true;
    }
}