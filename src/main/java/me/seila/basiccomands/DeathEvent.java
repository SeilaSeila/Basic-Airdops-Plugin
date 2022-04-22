package me.seila.basiccomands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class DeathEvent {

    private static ItemStack getHead(Player victim, Player murder) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(victim.getName());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(victim.getName() + " Foi brutalmente assassinado por " + murder.getName());
        skull.setLore(lore);
        skull.setOwningPlayer(victim);
        item.setItemMeta(skull);
        return item;
    }

    public void giveHead(Player victim, Player murder)
    {
        Location dropLocation = victim.getLocation();

        dropLocation.getWorld().dropItemNaturally(dropLocation, getHead(victim,murder));
    }

}
