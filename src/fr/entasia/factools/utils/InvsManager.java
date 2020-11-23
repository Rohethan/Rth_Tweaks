package fr.entasia.factools.utils;

import fr.entasia.apis.menus.MenuClickEvent;
import fr.entasia.apis.menus.MenuCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvsManager {

    public static MenuCreator spellMenu = new MenuCreator(){

        @Override
        public void onMenuClick(MenuClickEvent e) {
//            e.
        }
    };



    public static void openSpellMenu(Player p){
        Inventory inv = spellMenu.createInv(5, "§2Sortilèges");




        p.openInventory(inv);
    }

}
