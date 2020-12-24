package icbm.classic.client;

import icbm.classic.ICBMConstants;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


/**
 * Enum of sounds used by ICBM
 *
 *
 * Created by Dark(DarkGuardsman, Robert) on 1/6/2018.
 * <p>
 * Credit to https://github.com/kitsushadow for sharing info on how to do sounds in MC 1.12
 */
@Mod.EventBusSubscriber(modid = ICBMConstants.DOMAIN)
public enum ICBMSounds {

    ANTIMATTER("antimatter"),
    BEAM_CHARGING("beamcharging"),
    COLLAPSE("collapse"),
    DEBILITATION("debilitation"),
    EMP("emp"),
    EXPLOSION("explosion"),
    EXPLOSION_FIRE("explosionfire"),
    GAS_LEAK("gasleak"),
    HYPERSONIC("hypersonic"),
    MACHINE_HUM("machinehum"),
    POWER_DOWN("powerdown"),
    TARGET_LOCKED("targetlocked"),
    REDMATTER("redmatter"),
    SONICWAVE("sonicwave"),
    MISSILE_LAUNCH("missilelaunch"),
    MISSILE_ENGINE("missileinair");

    private final ResourceLocation location;
    private SoundEvent sound;


    ICBMSounds(String path) {
        location = new ResourceLocation(ICBMConstants.DOMAIN, path);
    }

    /**
     * Gets the sound event for use with MC code
     *
     * @return sound event
     */
    public SoundEvent getSound() {
        return sound;
    }

    /**
     * Players the given sound at the entity's location with the arguments
     *
     * @param entity        - source
     * @param volume        - sound level
     * @param pitch         - sound pitch
     * @param distanceDelay - should the sound be delayed by distance
     */
    public void play(Entity entity, float volume, float pitch, boolean distanceDelay) {
        //TODO move audio settings to constants attached to configs
        play(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), volume, pitch, distanceDelay);
    }

    /**
     * Players the given sound at the entity's location with the arguments
     *
     * @param world         - location
     * @param x-            location
     * @param y-            location
     * @param z-            location
     * @param volume        - sound level
     * @param pitch         - sound pitch
     * @param distanceDelay - should the sound be delayed by distance
     */
    public void play(World world, double x, double y, double z, float volume, float pitch, boolean distanceDelay) {
        world.playSound(null, x, y, z, getSound(), SoundCategory.BLOCKS, volume, pitch);
    }

    public void play(World world, BlockPos pos, float volume, float pitch, boolean distanceDelay) {
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), getSound(), SoundCategory.BLOCKS, volume, pitch);
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (ICBMSounds icbmSounds : values()) {
            icbmSounds.sound = new SoundEvent(icbmSounds.location).setRegistryName(icbmSounds.location);
            event.getRegistry().register(icbmSounds.sound);
        }
    }

}
