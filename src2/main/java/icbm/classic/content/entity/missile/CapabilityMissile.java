package icbm.classic.content.entity.missile;

import icbm.classic.api.caps.IMissile;
import icbm.classic.api.explosion.BlastState;
import icbm.classic.content.reg.ItemReg;
import icbm.classic.lib.transform.vector.Pos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/7/19.
 */
public class CapabilityMissile implements IMissile {

    public final EntityMissile missile;

    public CapabilityMissile(EntityMissile missile) {
        this.missile = missile;
    }

    @Override
    public void dropMissileAsItem() {
        ItemStack stack = toStack();
        if (stack != null && !stack.isEmpty() && world() != null)
            world().addEntity(new ItemEntity(world(), x(), y(), z(), stack));
        missile.setDead();
    }

    @Override
    public BlastState doExplosion() {
        return missile.doExplosion();
    }

    @Override
    public boolean hasExploded() {
        return missile.isExploding;
    }

    @Override
    public ItemStack toStack() {
        return new ItemStack(ItemReg.itemMissile, 1, missile.explosiveID);
    }

    @Override
    public int getTicksInAir() {
        return missile.ticksInAir;
    }

    @Override
    public Entity getMissileEntity() {
        return missile;
    }

    @Override
    public void launch(double x, double y, double z, double height) {
        missile.launch(new Pos(x, y, z), (int) height);
    }

    @Override
    public void launchNoTarget() {
        missile.launch(null, 0);
    }

    @Override
    public World world() {
        return missile != null ? missile.world : null;
    }

    @Override
    public double z() {
        return missile != null ? missile.getPosZ() : 0;
    }

    @Override
    public double x() {
        return missile != null ? missile.getPosX() : 0;
    }

    @Override
    public double y() {
        return missile != null ? missile.getPosY() : 0;
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IMissile.class, new Capability.IStorage<IMissile>() {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IMissile> capability, IMissile instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IMissile> capability, IMissile instance, Direction side, INBT nbt) {

                    }
                },
                () -> new CapabilityMissile(null));
    }
}
