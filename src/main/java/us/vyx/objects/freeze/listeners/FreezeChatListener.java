package us.vyx.objects.freeze.listeners;

import us.vyx.StaffMode;
import us.vyx.objects.freeze.Freeze;
import us.vyx.objects.staff.Staff;
import us.vyx.utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by DevEmx
 * Project: VyxStaff
 */
public class FreezeChatListener implements Listener {

    public FreezeChatListener(StaffMode plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onFrozenChat(AsyncPlayerChatEvent event) {
        Freeze freeze = Freeze.getFreeze(event.getPlayer().getUniqueId());

        if (freeze != null && freeze.isFrozen()) {
            event.setCancelled(true);

            freeze.getPlayer().sendMessage(ChatUtil.translate("&c[Freeze Chat] &7" + freeze.getName() + ": &f" + event.getMessage()));

            Staff staff = freeze.getStaff();

            if (staff != null) {
                staff.getPlayer().sendMessage(ChatUtil.translate("&c[Freeze Chat] &7" + freeze.getName() + ": &f" + event.getMessage()));
            }
        }
    }
}
