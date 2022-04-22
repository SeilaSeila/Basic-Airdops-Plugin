package me.seila.basiccomands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ChestLoot {

    private int getRandomNumber(int min, int max)
    {
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    private boolean getChance(int chance)
    {
        return chance >= getRandomNumber(0, 100);
    }

    private void setupInventory(Inventory inventory)
    {

        //each 3 days in mine

        //10 - 15 common items
        //2 - 6 rare items
        // 25% legendary
        // 1% Epic

        //setup inventory

        inventory.clear();

        List<ItemStack> commonItems = new ArrayList<>();
        commonItems.add(new ItemStack(Material.ARROW, 16));
        commonItems.add(new ItemStack(Material.ACACIA_BOAT, 1));
        commonItems.add(new ItemStack(Material.GOLDEN_BOOTS, 1));
        commonItems.add(new ItemStack(Material.WHEAT, 16));
        commonItems.add(new ItemStack(Material.FLINT, 8));
        commonItems.add(new ItemStack(Material.FLINT_AND_STEEL, 1));
        commonItems.add(new ItemStack(Material.COBWEB, 1));
        commonItems.add(new ItemStack(Material.BOWL, 1));
        commonItems.add(new ItemStack(Material.BOW, 1));
        commonItems.add(new ItemStack(Material.IRON_SWORD, 1));
        commonItems.add(new ItemStack(Material.IRON_PICKAXE, 1));
        commonItems.add(new ItemStack(Material.IRON_AXE, 1));
        commonItems.add(new ItemStack(Material.APPLE, 4));
        commonItems.add(new ItemStack(Material.OAK_LOG, 18));
        commonItems.add(new ItemStack(Material.NETHER_WART, 8));
        commonItems.add(new ItemStack(Material.BREWING_STAND, 1));
        commonItems.add(new ItemStack(Material.CHAIN, 16));
        commonItems.add(new ItemStack(Material.ENDER_PEARL, 4));
        commonItems.add(new ItemStack(Material.GOLDEN_BOOTS, 1));
        commonItems.add(new ItemStack(Material.COOKED_CHICKEN, 1));
        commonItems.add(new ItemStack(Material.CHAINMAIL_HELMET, 1));
        commonItems.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
        commonItems.add(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
        commonItems.add(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
        commonItems.add(new ItemStack(Material.GOLDEN_SWORD, 1));
        commonItems.add(new ItemStack(Material.COBBLESTONE, 64));

        List<ItemStack> rareItems = new ArrayList<>();
        rareItems.add(new ItemStack(Material.SHIELD, 1));
        rareItems.add(new ItemStack(Material.IRON_AXE, 1));
        rareItems.add(new ItemStack(Material.TNT, 64));
        rareItems.add(new ItemStack(Material.DIAMOND_SWORD, 1));
        rareItems.add(new ItemStack(Material.IRON_ORE, 64));
        rareItems.add(new ItemStack(Material.GOLD_ORE, 64));
        rareItems.add(new ItemStack(Material.TURTLE_HELMET, 1));
        rareItems.add(new ItemStack(Material.GOLDEN_APPLE, 16));
        rareItems.add(new ItemStack(Material.DIAMOND_AXE, 1));
        rareItems.add(new ItemStack(Material.DIAMOND_PICKAXE, 1));
        rareItems.add(new ItemStack(Material.DIAMOND_HELMET, 1));
        rareItems.add(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        rareItems.add(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        rareItems.add(new ItemStack(Material.DIAMOND_BOOTS, 1));
        rareItems.add(new ItemStack(Material.ENCHANTING_TABLE, 1));
        rareItems.add(new ItemStack(Material.CROSSBOW, 1));
        rareItems.add(new ItemStack(Material.DIAMOND, 8));

        List<ItemStack> legendaryItems = new ArrayList<>();
        legendaryItems.add(new ItemStack(Material.OBSIDIAN, 43));
        legendaryItems.add(new ItemStack(Material.NETHERITE_PICKAXE, 1));
        legendaryItems.add(new ItemStack(Material.NETHERITE_SWORD, 1));
        legendaryItems.add(new ItemStack(Material.NETHERITE_AXE, 1));
        legendaryItems.add(new ItemStack(Material.NETHERITE_HELMET, 1));
        legendaryItems.add(new ItemStack(Material.NETHERITE_CHESTPLATE, 1));
        legendaryItems.add(new ItemStack(Material.NETHERITE_LEGGINGS, 1));
        legendaryItems.add(new ItemStack(Material.NETHERITE_BOOTS, 1));
        legendaryItems.add(new ItemStack(Material.END_CRYSTAL, 64));
        legendaryItems.add(new ItemStack(Material.TOTEM_OF_UNDYING, 1));
        legendaryItems.add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        legendaryItems.add(new ItemStack(Material.TRIDENT));

        List<ItemStack> epicItems = new ArrayList<>();
        epicItems.add(new ItemStack(Material.BEDROCK, 5));
        epicItems.add(new ItemStack(Material.NETHERITE_BLOCK, 2));

        int commonItemsCount = getRandomNumber(10,15);
        int rareItemsCount = getRandomNumber(2,6);

        //Get Empty Slots

        List<Integer> emptySlots = new ArrayList<>();
        for (int i = 0; i < inventory.getSize(); i++)
        {
            if (inventory.getItem(i) == null)
                emptySlots.add(i);
        }

        if (emptySlots.size() <= 0)
            return;

        //place Epic items

        if (getChance(1))
        {
            int index = getRandomNumber(0, emptySlots.size() - 1);
            int randomSlot = emptySlots.get(index - 1);
            inventory.setItem(randomSlot, epicItems.get(getRandomNumber(0, epicItems.size() - 1)));
            emptySlots.remove(index);
        }

        //place Legendary items

        if (getChance(25))
        {
            int index = getRandomNumber(0, emptySlots.size() - 1);
            int randomSlot = emptySlots.get(index - 1);
            inventory.setItem(randomSlot, legendaryItems.get(getRandomNumber(0, legendaryItems.size() - 1)));
            emptySlots.remove(index);
        }

        //Place Rare items
        for (int i = 0; i < rareItemsCount; i++)
        {
            int index = getRandomNumber(0, emptySlots.size() - 1);
            int randomSlot = emptySlots.get(index);
            inventory.setItem(randomSlot, rareItems.get(getRandomNumber(0, rareItems.size() - 1)));
            emptySlots.remove(index);
        }

        //Place Common items
        for (int i = 0; i < commonItemsCount; i++)
        {
            int index = getRandomNumber(0, emptySlots.size() - 1);
            int randomSlot = emptySlots.get(index);
            inventory.setItem(randomSlot, commonItems.get(getRandomNumber(0, commonItems.size() - 1)));
            emptySlots.remove(index);
        }
    }

    public void setupChest(Location position)
    {
        //Getting location
        Block chestBlock = position.getBlock();
        chestBlock.setType(Material.CHEST);
        Chest chest = (Chest)chestBlock.getState();
        Inventory inv = chest.getInventory();

        //place items
        setupInventory(inv);

        //call a lightning effect
        position.getBlock().getWorld().strikeLightningEffect(position);
    }
}
