package halflive9.bukkit.spells.Listener;

import halflive9.bukkit.spells.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Spellcaster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;

public class PlayerListener implements Listener {

    File file;
    List<String> Spells;
    FileConfiguration playerData;
    int index = 0;

    public PlayerListener(File dataFolder) {
        this.file = dataFolder;
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.RECORD_3 && event.getAction().equals(Action.LEFT_CLICK_AIR))
        {

            //player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
            //player.launchProjectile(Snowball.class);
            player.getWorld().spawnEntity(player.getTargetBlock( null, 5).getLocation()
                    , EntityType.CHICKEN);
        }
        if(player.getInventory().getItemInMainHand().getType() == Material.RECORD_3 && event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            switchSpell(player);
        }
    }

    private void switchSpell(Player player) {
        player.sendMessage("§eSpell set: §5" + Spells.get(index));
        index = index < Spells.size() -1 ? index+1 : 0;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        File playerFile =  new File ( file + File.separator + "plugins/essentials/userdata", player.getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        this.Spells = (List<String>)playerData.get("Spells");
        player.sendMessage(Spells.toString());
    }
}

