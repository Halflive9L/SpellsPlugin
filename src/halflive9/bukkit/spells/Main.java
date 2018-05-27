package halflive9.bukkit.spells;

import halflive9.bukkit.spells.Listener.PlayerListener;
import halflive9.bukkit.spells.util.CustomEntityRegistry;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public class Main extends JavaPlugin {

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this.getDataFolder()), this);
        CustomEntityRegistry.registerCustomEntity(93, "Chicken", CustomEntityChicken.class);
    }
}
