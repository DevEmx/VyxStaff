package us.vyx.objects.staff.commands;

import us.vyx.objects.freeze.Freeze;
import us.vyx.objects.staff.Staff;
import us.vyx.utilities.ChatUtil;
import us.vyx.utilities.command.BaseCommand;
import us.vyx.utilities.command.Command;
import us.vyx.utilities.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by DevEmx
 * Project: VyxStaff
 */
public class FreezeCommand extends BaseCommand {

    @Command(name = "freeze", aliases = {"ss"}, permission = "vyxstaff.command.freeze")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(ChatUtil.translate("&cUsage: /" + command.getLabel() + " <player>"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(ChatUtil.translate("&cPlayer " + args[0] + " not found!"));
            return;
        }

        Staff staff = Staff.getStaff(player.getUniqueId());

        if (staff == null) {
            player.sendMessage(ChatUtil.translate("&cYou are not a staff member!"));
            return;
        }

        Freeze freeze;

        if (Freeze.getFreezes().get(target.getUniqueId()) == null) {
            freeze = new Freeze(target.getUniqueId());
        }
        else {
            freeze = Freeze.getFreezes().get(target.getUniqueId());
        }

        freeze.setStaff(staff);

        if (freeze.isFrozen()) {
            freeze.unFreezePlayer(true);
        }
        else {
            freeze.freezePlayer(true);
        }
    }
}
