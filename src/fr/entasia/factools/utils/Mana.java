package fr.entasia.factools.utils;

import fr.entasia.factools.Main;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class Mana {
    public static void setMana(Player p, int amount) {
        p.setMetadata("mana", new FixedMetadataValue(Main.main, amount));
    }
    public static int getMana(Player p) {
        List<MetadataValue> list = p.getMetadata("mana");
        if (list.size() == 0) return 0;
        int mana_p = list.get(0).asInt();
        return mana_p;
    }
}