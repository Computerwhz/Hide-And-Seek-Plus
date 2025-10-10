package dev.tylerm.khs.game.events;

import dev.tylerm.khs.Main;
import dev.tylerm.khs.configuration.Config;
import dev.tylerm.khs.configuration.Localization;
import dev.tylerm.khs.configuration.LocalizationString;
import dev.tylerm.khs.configuration.Map;
import dev.tylerm.khs.game.Game;
import dev.tylerm.khs.game.PlayerLoader;
import dev.tylerm.khs.game.util.Status;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Blockchange implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = event.getItem();
            if (item != null) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.getDisplayName().equals(Config.changeBlockItem.getItemMeta().getDisplayName())) {
                    event.setCancelled(true);
                    Game game = Main.getInstance().getGame();
                    if (game.getStatus().equals(Status.PLAYING)) {
                        Map map = game.getCurrentMap();
                        PlayerLoader.openBlockHuntPicker(event.getPlayer(), map);
                    } else {
                        LocalizationString block = Localization.message("GAME_BLOCKCHANGE");
                        event.getPlayer().sendMessage(Config.messagePrefix + block);
                    }
                }
            }
        }
    }
}
