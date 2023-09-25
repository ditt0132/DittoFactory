package dittonut.ditto_factory;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args[0].equalsIgnoreCase("getitem")) {
                if (args[1].equals("conveyor")) {
                    ItemStack itemStack = new ItemStack(Material.WHITE_CARPET);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("conveyor");
                    itemStack.setItemMeta(itemMeta);
                    ((Player) sender).getInventory().addItem(itemStack);
                }
            }
            return true;
        } else {
            // 플레이어가 아닌 경우
            sender.sendMessage("플레이어만 이 커맨드를 실행할 수 있습니다.");
            return false;
        }
    }
}
