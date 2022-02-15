package us.vyx.objects.freeze;

import com.google.common.collect.Maps;
import us.vyx.objects.staff.Staff;
import us.vyx.objects.staff.StaffHandler;
import us.vyx.utilities.ChatUtil;
import us.vyx.utilities.PlayerUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

/**
 * Created by DevEmx
 * Project: VyxStaff
 */

@Getter @Setter
public class Freeze {

    @Getter
    private static Map<UUID, Freeze> freezes = Maps.newHashMap();

    private UUID uuid;
    private Staff staff;
    private boolean frozen;
    private ItemStack frozenItem = new ItemStack(Material.PACKED_ICE);

    private ItemStack[] armorContents;

    public Freeze(UUID uuid) {
        this.uuid = uuid;

        freezes.put(uuid, this);
    }

    public void freezePlayer(boolean message) {
        if (Staff.getStaff(uuid) != null) {
            Player staffPlayer = staff.getPlayer();
            staffPlayer.sendMessage(ChatUtil.translate("&cYou can't frozen " + getName() + " because is staff."));
            return;
        }

        setFrozen(true);

        Player player = getPlayer();

        setArmorContents(player.getInventory().getArmorContents());

        PlayerUtil.clear(player, true, false);
        PlayerUtil.denyMovement(player);

        player.getInventory().setHelmet(getFrozenItem());
        player.updateInventory();

        if (message) {
            StaffHandler.sendMessageAllStaff("&c" + getName() + " &7has been frozen by &c" + staff.getName(), false);
            player.sendMessage(ChatUtil.translate("&cYou have been frozen by &c" + staff.getName()));
        }
    }

    public void unFreezePlayer(boolean message) {
        if (Staff.getStaff(uuid) != null) {
            Player staffPlayer = staff.getPlayer();
            staffPlayer.sendMessage(ChatUtil.translate("&cYou can't unfrozen " + getName() + " because is staff."));
            return;
        }

        setFrozen(false);

        Player player = getPlayer();

        PlayerUtil.allowMovement(player);
        PlayerUtil.clear(player, true, false);

        player.getInventory().setArmorContents(getArmorContents());
        player.updateInventory();

        if (message) {
            StaffHandler.sendMessageAllStaff("&c" + getName() + " &7has been unfrozen by &c" + staff.getName(), false);
            player.sendMessage(ChatUtil.translate("&cYou have been unfrozen by &c" + staff.getName()));
        }

        freezes.remove(uuid);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public String getName() {
        return getPlayer().getName();
    }

    public static Freeze getFreeze(UUID uuid) {
        return freezes.get(uuid);
    }
}
