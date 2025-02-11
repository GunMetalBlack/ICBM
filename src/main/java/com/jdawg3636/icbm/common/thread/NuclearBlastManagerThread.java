package com.jdawg3636.icbm.common.thread;

import com.jdawg3636.icbm.common.reg.BlockReg;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NuclearBlastManagerThread extends RaytracedBlastManagerThread {

    @Override
    public String getRegistryName() {
        return "icbm:nuclear";
    }

    @Override
    public RaytracedBlastWorkerThread getNewWorkerThread() {
        return new NuclearBlastWorkerThread();
    }

    @Override
    public void decorate(World level, BlockPos blockPos) {
        level.setBlockAndUpdate(blockPos, BlockReg.RADIOACTIVE_MATERIAL.get().defaultBlockState());
    }

}
