package us.vyx;

import us.vyx.objects.freeze.FreezeHandler;
import us.vyx.objects.freeze.listeners.FreezeChatListener;
import us.vyx.objects.freeze.listeners.FreezeListener;
import us.vyx.objects.staff.StaffHandler;
import us.vyx.objects.staff.commands.FreezeCommand;
import us.vyx.objects.staff.commands.StaffModeCommand;
import us.vyx.objects.staff.commands.VanishCommand;
import us.vyx.objects.staff.listeners.StaffItemsListener;
import us.vyx.objects.staff.listeners.StaffListener;
import us.vyx.utilities.command.CommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class StaffMode extends JavaPlugin {

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        this.commandManager = new CommandManager(this);

        new StaffListener(this);
        new StaffItemsListener(this);
        new FreezeListener(this);
        new FreezeChatListener(this);

        new StaffModeCommand();
        new VanishCommand();
        new FreezeCommand();
    }

    @Override
    public void onDisable() {
        StaffHandler.disable();
        FreezeHandler.disable();
    }

    public static StaffMode get() {
        return getPlugin(StaffMode.class);
    }
}