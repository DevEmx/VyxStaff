package us.vyx.objects.staff.commands;

import us.vyx.objects.staff.Staff;
import us.vyx.utilities.ChatUtil;
import us.vyx.utilities.command.BaseCommand;
import us.vyx.utilities.command.Command;
import us.vyx.utilities.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by DevEmx
 * Project: VyxStaff
 */
public class VanishCommand extends BaseCommand {

    @Command(name = "vanish", aliases = {"v"}, permission = "vyxstaff.command.vanish")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        Staff staff = Staff.getStaff(player.getUniqueId());

        if (staff == null) {
            player.sendMessage(ChatUtil.translate("&cYou are not a staff member!"));
            return;
        }

        if (staff.isVanished()) {
            staff.disableVanish(true);
        }
        else {
            staff.enableVanish(true);
        }
    }
}
