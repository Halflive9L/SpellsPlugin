package halflive9.bukkit.spells.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD && event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            player.sendMessage("Lightnigh spawned!");
            player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
        }
    }
}

