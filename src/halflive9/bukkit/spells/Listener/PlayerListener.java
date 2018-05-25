package halflive9.bukkit.spells.Listener;

import halflive9.bukkit.spells.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.DisguiseCraft.disguise.Disguise;
import pgDev.bukkit.DisguiseCraft.disguise.DisguiseType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;

public class PlayerListener implements Listener {

    private File file;
    private List<String> spells;
    private int index = 0;
    DisguiseCraftAPI dcAPI;

    public PlayerListener(File dataFolder) {
        this.file = dataFolder;
        setupDisguiseCraft();
    }


    private void setupDisguiseCraft() {
        dcAPI = DisguiseCraft.getAPI();
    }

    //TODO: Map spells on Spell class to make spell usage generic without massive if-else
    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.RECORD_3 && event.getAction().equals(Action.LEFT_CLICK_AIR))
        {
            player.sendMessage("Index" + index+ " - spell: " + spells.get(index));
            if(spells.get(index).equals("Avis")) {
                player.sendMessage(spells.get(index));
                player.getWorld().spawnEntity(player.getTargetBlock(null, 5).getLocation()
                        , EntityType.CHICKEN);
            } else {
                player.launchProjectile(Snowball.class);
            }
        }
        if(player.getInventory().getItemInMainHand().getType() == Material.RECORD_3 && event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            switchSpell(player);
        }
    }

    private void switchSpell(Player player) {
        index = index < spells.size() -1 ? index+1 : 0;
        player.sendMessage("§eSpell set: §5" + spells.get(index));

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        File playerFile =  new File ( file + File.separator + "plugins/essentials/userdata", player.getUniqueId() + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        this.spells = (List<String>)playerData.get("Spells");
        player.sendMessage(spells.toString());
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        Player player = (Player)e.getEntity();
        Disguise disguise = new Disguise(1, DisguiseType.Chicken);
        dcAPI.disguisePlayer(player, disguise);
        player.sendMessage("You've been hit");
    }
}

