package me.seila.basiccomands;

import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class JunglesEvents extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Jungle's Plugin is enabled");

        //Config The World
        getServer().getWorlds().get(0).setTime(0);
        getServer().getWorlds().get(0).setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
        getServer().getWorlds().get(0).setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);

        //Setup Classes
        EventManager eventManager = new EventManager();
        Stats stats = new Stats(this);
        Menu menu = new Menu(eventManager, stats);

        //Setup loops and listeners
        BukkitTask update = new Update(this, menu, eventManager).runTaskTimer(this, 0L, 1L);
        getServer().getPluginManager().registerEvents(new EventListener(eventManager, menu, stats, this), this);

        //Setup Commands
        getCommand("callAirdrop").setExecutor(eventManager);
        getCommand("menu").setExecutor(menu);
    }

    @Override
    public void onDisable() {
        System.out.println("Jungle's Plugin is disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("ajuda"))
        {
            if (sender instanceof Player)
            {
                Player player = (Player) sender;
                player.sendMessage("\"/Menu\" - Abre o menu");
            }
        }

        return  true;
    }
}
