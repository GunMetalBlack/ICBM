package icbm.classic.api.events;

import icbm.classic.content.blocks.launcher.TileLauncherPrefab;
import icbm.classic.lib.transform.vector.Pos;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * Called when the target of a launcher is set.
 * Use this to change the position that is being set,
 * or cancel the event to not set any position.
 */
@Cancelable
public class LauncherSetTargetEvent extends Event {

    public final TileLauncherPrefab launcher;
    public Pos target;

    public LauncherSetTargetEvent(TileLauncherPrefab launcher, Pos target) {
        this.launcher = launcher;
        this.target = target;
    }

}
