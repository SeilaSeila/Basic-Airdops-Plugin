package me.seila.basiccomands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    DeathEvent deathEvent = new DeathEvent();

    EventManager eventManager;
    Menu menu;
    Stats stats;
    JunglesEvents junglesEvents;

    public  EventListener(EventManager eventManager, Menu menu, Stats stats, JunglesEvents junglesEvents) {
        this.eventManager = eventManager;
        this.menu = menu;
        this.stats = stats;
        this.junglesEvents = junglesEvents;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        e.getPlayer().setPlayerListName("...");
        e.getPlayer().hidePlayer(junglesEvents, e.getPlayer());

        stats.CheckStatus(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage("");
        menu.ClosePlayerMenu(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player victim = e.getEntity();

        if (e.getEntity().getKiller() != null) {
            Player killer = e.getEntity().getKiller();

            stats.IncreaseKill(killer);
            stats.IncreaseDeath(victim);

            stats.RegulateRank(victim);
            stats.RegulateRank(killer);

            deathEvent.giveHead(victim, killer);
        }

        stats.SetLastDeathPlace(victim);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
    }
}
