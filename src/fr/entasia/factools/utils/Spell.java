package fr.entasia.factools.utils;

import fr.entasia.factools.Main;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public enum Spell {
  HEAL(0),
  FLY(1),
  METEOR(2),
  FROZE(3),
  FIREBALL(4),
  SPEED(5);

  public int id;

  Spell(int id) {
    this.id = id;
  }

  public static Spell get(int id) {
    for (Spell sp : values()) {
      if (sp.id == id) return sp;
    }
    return null;
  }

  public static Spell getCurrentSpell(Player p) {
    List<MetadataValue> list = p.getMetadata("spell");
    if (list.size() == 0) return null;
    int id = list.get(0).asInt();
    return get(id);
  }

  public static void setCurrentSpell(Player p, Spell sp) {
    setCurrentSpell(p, sp.id);
  }

  public static void setCurrentSpell(Player p, int id) {
    p.removeMetadata("spell", Main.main);
    p.setMetadata("spell", new FixedMetadataValue(Main.main, id));
    p.getWorld().playSound(p.getLocation(), Sound.UI_TOAST_IN, 1, (float) 1.5);
    p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 100, 0.0, 0.0, 0.0, 0.025);
  }

  public static void removeCurrentSpell(Player p) {
    p.removeMetadata("spell", Main.main);

  }
}