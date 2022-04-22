package me.seila.basiccomands;

import com.google.common.base.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class Menu implements CommandExecutor {

    Stats stats;
    EventManager eventManager;

    public Menu (EventManager eventManager, Stats stats)
    {
        this.eventManager = eventManager;
        this.stats = stats;
    }

    public static HashMap<UUID, Boolean> isOpen = new HashMap<>();

    private String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor, ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + completedColor + symbol, progressBars)
                + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
    }

    public void ClosePlayerMenu(Player p)
    {
        UUID id = p.getUniqueId();

        if (isOpen.containsKey(id))
            isOpen.remove(id);
    }

    private void CreateMenu(Player player)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Menu", "dummy", ChatColor.YELLOW + "Coordenadas");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score cords = objective.getScore("Coordenadas: " + GetCords(player));
        cords.setScore(4);

        Score deathCords = objective.getScore("Ultima Morte: " + stats.GetLastDeathPlace(player));
        deathCords.setScore(3);

        Score rank = objective.getScore("Ranque: " + stats.GetRank(player));
        rank.setScore(2);

        Score daysToAirDrop = objective.getScore("Airdrop: " + getProgressBar(eventManager.getCurrentAirdropDay(), eventManager.getMaxDays(), 8, '|', ChatColor.YELLOW, ChatColor.BLACK) + " %");
        daysToAirDrop.setScore(1);

        player.setScoreboard(scoreboard);
    }

    public void Update()
    {
        for(Player player : Bukkit.getServer().getOnlinePlayers()){

            UUID id = player.getUniqueId();

            if (!isOpen.containsKey(id) || !isOpen.get(id))
                continue;

            CreateMenu(player);
        }
    }

    private String GetCords(Player p)
    {
        Location location = p.getLocation();

        String X =  ChatColor.GREEN + " X: " + location.getBlockX();
        String Y =  ChatColor.GREEN + " Y: " + location.getBlockY();
        String Z =  ChatColor.GREEN + " Z: " + location.getBlockZ();

        return X + Y + Z;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player)
        {
            UUID id = player.getUniqueId();

            if (!isOpen.containsKey(id)){isOpen.put(id, true); return true;}

            boolean state = isOpen.get(id);
            isOpen.remove(id);

            state = !state;
            isOpen.put(id, state);

            if (state){
                CreateMenu(player);
            }
            else {
                player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
        }

        return true;
    }
}
