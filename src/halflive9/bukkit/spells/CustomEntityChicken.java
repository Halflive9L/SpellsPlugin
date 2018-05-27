package halflive9.bukkit.spells;

import net.minecraft.server.v1_12_R1.EntityChicken;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CustomEntityChicken extends EntityChicken {


    @SuppressWarnings("deprecation")
    public CustomEntityChicken(World world, Player player) {
        super(world);

        Chicken craftChicken = (Chicken) this.getBukkitEntity();

        craftChicken.setMaxHealth(player.getMaxHealth());
        craftChicken.setHealth(player.getHealth());

        this.setCustomName(player.getName());
        this.setCustomNameVisible(true);
        this.setInvisible(false);

        Location loc = player.getLocation();
        this.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
        world.addEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        this.getWorld().addEntity(this);
    }
}
