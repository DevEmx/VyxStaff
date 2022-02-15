package us.vyx.objects.freeze;

/**
 * Created by DevEmx
 * Project: VyxStaff
 */
public class FreezeHandler {

    public static void disable() {
        for (Freeze freeze : Freeze.getFreezes().values()) {
            freeze.unFreezePlayer(false);
        }
    }
}
