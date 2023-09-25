package dittonut.ditto_factory;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Hopper;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class DittoFactory extends JavaPlugin implements Listener {

    private ProtocolManager protocolManager;
    // dddd

    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        // registerPacketListener();
        // Plugin startup logic
        System.out.println("[DittoFactory] init");
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("df").setExecutor(new CommandManager()); // not nullable.
        // cuz i defined the command at plugin.yml

    }

    @EventHandler
    public void onItemMove(InventoryMoveItemEvent event) {
        Inventory sourceInventory = event.getSource();
        Inventory destinationInventory = event.getDestination();

        if (isConveyor(sourceInventory)) {
            display((Hopper) sourceInventory);
        } else if (isConveyor(destinationInventory)) {
            display((Hopper) sourceInventory);
        }

    }
    private void display(Hopper h) {
        getLogger().info("Item in HOPPER!! Location: " + h.getLocation().toString()+", Item: " + h.getInventory().getItem(0).getType().toString());
    }

    /*@EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placedBlock = event.getBlockPlaced();

        if (event.getItemInHand().getItemMeta().getDisplayName().equals("Conveyor") && event.getItemInHand().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
            // if item's displayName is "Conveyor" and item has HIDE_ENCHANTS itemFlag
            Location location = event.getBlockPlaced().getLocation();
            event.setCancelled(true);
            location.getBlock().setType(Material.HOPPER);
            Hopper hopper = (Hopper) location.getBlock();
            hopper.setLock("wrench"); // isConveyor returns true
            hopper.setCustomName("Conveyor"); //isConveyor returns true
        }
    }*/

    public boolean isConveyor(Inventory inv) {
        if (inv instanceof Hopper hopper) {
            return (hopper.getCustomName() != null) && (hopper.getCustomName().equals("Conveyor")) && (hopper.getLock().equals("wrench"));
            // if hopper's customName is Conveyor(null check) and hopper's lock is 'wrench'
            // why getlock has no null check? cuz ide says always true
        }
        return false;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[DittoFactory] shutting down!");
    }
}
