package com.jdawg3636.icbm.common.block.launcher_control_panel;

import com.jdawg3636.icbm.common.block.launcher_platform.TileLauncherPlatform;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class TileLauncherControlPanel extends TileEntity {

    public TileLauncherControlPanel(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public void setTargetX(double targetX) {}

    public void setTargetZ(double targetZ) {}

    public void setTargetY(double targetY) {}

    public void setRadioFrequency(int radioFrequency) {}

    public double getTargetX() {
        return 0D;
    }

    public double getTargetZ() {
        return 0D;
    }

    public double getTargetY() {
        return 63D;
    }

    public int getRadioFrequency() {
        return 0;
    }

    // TODO Implement Varying Accuracy Based on Support Frames
    public void launchMissile() {
        BlockPos platformPos = getBlockPos().offset(getBlockState().getValue(BlockLauncherControlPanel.FACING).getOpposite().getNormal());
        TileEntity platformTile = level.getBlockEntity(platformPos);
        BlockPos targetPos = new BlockPos(getTargetX(), getTargetY(), getTargetZ());
        if(platformTile instanceof TileLauncherPlatform) ((TileLauncherPlatform)platformTile).launchMissile(platformPos, targetPos, level.getHeight(), (int)Math.sqrt(platformPos.distSqr(targetPos)));
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition, 1, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        return save(tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        if(level != null && level.isClientSide()) {
            handleUpdateTag(getBlockState(), pkt.getTag());
            if (Minecraft.getInstance().screen instanceof ScreenLauncherControlPanel) {
                ((ScreenLauncherControlPanel)Minecraft.getInstance().screen).updateGui();
            }
        }
    }

}
