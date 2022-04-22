package me.seila.basiccomands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Stats {

    JunglesEvents plugin;

    private String lastDeathKey = "LastDeath";
    private String deathCountKey = "deathCount";
    private String killCountKey = "killCount";
    private String rankKey = "PvPRank";

    Stats (JunglesEvents plugin)
    {
        this.plugin = plugin;
    }

    public String GetRank(Player player)
    {
        PersistentDataContainer data = player.getPersistentDataContainer();
        String currentRank = data.get(new NamespacedKey(plugin, rankKey), PersistentDataType.STRING);
        return currentRank;
    }

    public void RegulateRank(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();


        String currentRank = "Sem Ranque";

        if (GetKills(player) > 3)
        {
            int deaths = GetDeaths(player);
            float KD = GetKills(player) / (deaths <= 0 ? 1 : deaths);

            if (KD <= 1)
                currentRank = ChatColor.LIGHT_PURPLE + "inutil";
            else if (KD <= 1.5)
                currentRank = ChatColor.DARK_GREEN + "Sobrevivente";
            else if (KD <= 2)
                currentRank = ChatColor.DARK_RED + "Gladiador";
            else if (KD <= 4)
                currentRank = ChatColor.AQUA + "Heroi";
            else if (KD <= 7)
                currentRank = ChatColor.YELLOW + "Lenda" ;
            else if (KD > 7)
                currentRank = ChatColor.BLACK + "Berserker";
        }

        data.set(new NamespacedKey(plugin, rankKey), PersistentDataType.STRING, currentRank);
    }

    public int GetDeaths(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return data.get(new NamespacedKey(plugin, deathCountKey), PersistentDataType.INTEGER);
    }

    public void IncreaseDeath(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();

        int count = data.get(new NamespacedKey(plugin, deathCountKey), PersistentDataType.INTEGER);

        count++;

        data.set(new NamespacedKey(plugin, deathCountKey), PersistentDataType.INTEGER, count);
    }

    public int GetKills(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return data.get(new NamespacedKey(plugin, killCountKey), PersistentDataType.INTEGER);
    }

    public void IncreaseKill(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();

        int count = data.get(new NamespacedKey(plugin, killCountKey), PersistentDataType.INTEGER);

        count++;

        data.set(new NamespacedKey(plugin, killCountKey), PersistentDataType.INTEGER, count);
    }

    public String GetLastDeathPlace(Player player)
    {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return data.get(new NamespacedKey(plugin, lastDeathKey), PersistentDataType.STRING);
    }

    public  void SetLastDeathPlace(Player player)
    {
        PersistentDataContainer data = player.getPersistentDataContainer();

        Location location = player.getLocation();
        String lastDeathPlace = ChatColor.YELLOW +
                "X: " + location.getBlockX() +
                " Y: " + location.getBlockY() +
                " Z: " + location.getBlockZ();

        data.set(new NamespacedKey(plugin, lastDeathKey), PersistentDataType.STRING, lastDeathPlace);
    }



    public void CheckStatus(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();

        if (!data.has(new NamespacedKey(plugin, deathCountKey), PersistentDataType.INTEGER)){
            data.set(new NamespacedKey(plugin, deathCountKey), PersistentDataType.INTEGER, 0);
        }

        if (!data.has(new NamespacedKey(plugin, killCountKey), PersistentDataType.INTEGER)){
        data.set(new NamespacedKey(plugin, killCountKey), PersistentDataType.INTEGER, 0);
        }

        if (!data.has(new NamespacedKey(plugin, rankKey), PersistentDataType.STRING)){
            data.set(new NamespacedKey(plugin, rankKey), PersistentDataType.STRING, "Sem Ranque");
        }

        if (!data.has(new NamespacedKey(plugin, lastDeathKey), PersistentDataType.STRING)){
            data.set(new NamespacedKey(plugin, lastDeathKey), PersistentDataType.STRING, "Sem mortes");
        }
    }

}
