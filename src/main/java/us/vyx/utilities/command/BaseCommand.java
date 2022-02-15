package us.vyx.utilities.command;

import us.vyx.StaffMode;

public abstract class BaseCommand {

    public BaseCommand() {
        StaffMode.get().getCommandManager().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs command);
}
