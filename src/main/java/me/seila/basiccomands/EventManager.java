package me.seila.basiccomands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.*;

public class EventManager implements CommandExecutor {

    ChestLoot chestLoot = new ChestLoot();

    private int getRandomNumber(int min, int max)
    {
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    private int airdropCoolDown = 0;
    private int nextAirdrop = 4;
    private int minPlayersToAirdrop = 3;

    public int getMaxDays() {
        return nextAirdrop;
    }

    public int getCurrentAirdropDay() {
        return  airdropCoolDown;
    }

    public void setNextDay()
    {
        if (getServer().getOnlinePlayers().size() < minPlayersToAirdrop)
            return;

        airdropCoolDown++;

        if (airdropCoolDown >= nextAirdrop)
        {
            CallAirDrop();
            airdropCoolDown = 0;
        }
    }

    public void CallAirDrop()
    {
        int range = 500;
        int posX = getRandomNumber(-range,range);
        int posZ = getRandomNumber(-range,range);
        World world = getWorlds().get(0);

        Location spot = world.getBlockAt(posX, 0, posZ).getLocation();
        //Location spot = world.getHighestBlockAt(posX, posZ).getLocation(); Slower and take fluid blocks in final results

        for (int i = 256; i > -64; i -= 10)
        {
            //find the right spot

            spot.setY(i);

            if (spot.getBlock().isEmpty() || spot.getBlock().isLiquid())
                continue;

            //Checking the last 30 blocks, used to avoid chest fall into the ground (can fail)

            for (; i < 256; i++)
            {
                spot.setY(i);

                if (!spot.getBlock().isEmpty() && !spot.getBlock().isLiquid())
                    continue;

                break;
            }

            world.setThundering(true);
            chestLoot.setupChest(spot);
            getServer( ).broadcastMessage( ChatColor.YELLOW + "Um airdrop caiu na posição: X: " + spot.getX() + " Y: " + spot.getY() +" Z: " + spot.getZ());
            break;
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp())
        {
            CallAirDrop();
        }
        else if (sender instanceof  Player player)
        {
            player.sendMessage("Você não tem permissão para usar esse comando" + ChatColor.RED);
        }

        return true;
    }
}
