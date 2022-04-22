package me.seila.basiccomands;

import org.bukkit.scheduler.BukkitRunnable;

class Update extends BukkitRunnable {

    int updateMenuRate = 10; //in ticks
    int eachDayRate = 24000; //in ticks

    EventManager eventManager;
    JunglesEvents plugin;
    Menu menu;

    Update(JunglesEvents plugin, Menu menu, EventManager eventManager)
    {
        this.plugin = plugin;
        this.eventManager = eventManager;
        this.menu = menu;
    }

    private void EachDay()
    {
        System.out.println("a cycle has passed");
        eventManager.setNextDay();
    }

    private void OnMenuUpdate()
    {
        menu.Update();
    }

    int currentMenuTick = 0;
    int currentEachDayTick = 0;

    @Override
    public void run() {
        currentMenuTick++;
        currentEachDayTick ++;

        if (currentMenuTick >= updateMenuRate)
        {
            OnMenuUpdate();
            currentMenuTick = 0;
        }

        if (currentEachDayTick >= eachDayRate)
        {
            EachDay();
            currentEachDayTick = 0;
        }

    }
}
