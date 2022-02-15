package us.vyx.objects.staff;

import us.vyx.utilities.ChatUtil;
import org.bukkit.Sound;

/**
 * Created by DevEmx
 * Project: VyxStaff
 */
public class StaffHandler {

    public static void sendMessageAllStaff(String message, boolean sound) {
        for (Staff staff : Staff.getStaffs().values()) {
            if (sound) staff.getPlayer().playSound(staff.getPlayer().getLocation(), Sound.ANVIL_USE, 1, 1);
            staff.getPlayer().sendMessage(ChatUtil.translate(message));
        }
    }

    public static void disable() {
        for (Staff staff : Staff.getStaffs().values()) {
            staff.disableStaffMode(false);
        }
    }
}
