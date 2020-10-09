package icbm.classic.content.blast.thread;

import com.builtbroken.jlib.data.vector.IPos3D;
import icbm.classic.content.blast.Blast;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

@Deprecated
public class BasicResistanceCallBack implements IThreadCallBack {

    public final Blast blast;

    public BasicResistanceCallBack(Blast blast) {
        this.blast = blast;
    }

    @Override
    public float getResistance(World world, IPos3D blastCenter, BlockPos pos, Entity source, Block block) {
        if (block instanceof IFluidBlock)
            return 0.25f;
        else
            return block.getExplosionResistance();
    }

}