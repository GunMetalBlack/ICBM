package icbm.classic.content.blocks;

import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.core.network.IPacketReceiver;
import com.builtbroken.mc.core.network.packet.PacketTile;
import com.builtbroken.mc.core.network.packet.PacketType;
import com.builtbroken.mc.core.registry.implement.IRecipeContainer;
import com.builtbroken.mc.lib.render.block.BlockRenderHandler;
import com.builtbroken.mc.lib.transform.region.Cube;
import com.builtbroken.mc.lib.transform.vector.Pos;
import com.builtbroken.mc.prefab.items.ItemBlockBase;
import com.builtbroken.mc.prefab.tile.Tile;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.classic.ICBMClassic;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.List;

public class TileCamouflage extends Tile implements IPacketReceiver, IRecipeContainer
{
    @SideOnly(Side.CLIENT)
    public static IIcon icon;

    // The block Id this block is trying to mimick
    private Block _blockToMimic = null;
    private int metaToMimic = 0;
    private boolean isSolid = true;

    /** Bitmask **/
    private byte renderSides = 0;

    public TileCamouflage()
    {
        super("camouflage", Material.grass);
        this.itemBlock = ItemBlockBase.class;
        this.renderType = BlockRenderHandler.ID;
        this.renderNormalBlock = false;
        this.renderTileEntity = false;
    }

    @Override
    public Tile newTile()
    {
        return new TileCamouflage();
    }

    @Override
    public boolean canUpdate()
    {
        return false;
    }

    @Override
    public void read(ByteBuf data, EntityPlayer player, PacketType packet)
    {
        if (isClient())
        {
            String blockName = ByteBufUtils.readUTF8String(data);
            this._blockToMimic = blockName.isEmpty() ? null : (Block) Block.blockRegistry.getObject(blockName);
            this.metaToMimic = data.readInt();
            this.renderSides = data.readByte();
            this.isSolid = data.readBoolean();
            markRender();
        }
    }

    @Override
    public void onWorldJoin()
    {
        markRender();
    }

    @Override
    public PacketTile getDescPacket()
    {
        String blockName = "";
        if (getMimicBlock() != null)
        {
            blockName = Block.blockRegistry.getNameForObject(getMimicBlock());
            if (blockName == null)
            {
                blockName = "";
            }
        }
        return new PacketTile(this, blockName, this.metaToMimic, this.renderSides, this.isSolid);
    }

    public boolean getCanCollide()
    {
        return this.isSolid;
    }

    public void setCanCollide(boolean isSolid)
    {
        this.isSolid = isSolid;

        if (!this.worldObj.isRemote)
        {
            sendDescPacket();
        }
        markDirty();
        markRender();
    }

    public void toggleCollision()
    {
        this.setCanCollide(!this.isSolid);
    }

    public Block getMimicBlock()
    {
        return this._blockToMimic;
    }

    public int getMimicBlockMeta()
    {
        return this.metaToMimic;
    }

    public void setMimicBlock(Block block, int metadata)
    {
        if (this.getMimicBlock() != block || this.metaToMimic != metadata)
        {
            this._blockToMimic = block;
            this.metaToMimic = Math.max(Math.min(metadata, 15), 0);
            if (isServer())
            {
                markDirty();
                sendDescPacket();
            }
            else
            {
                markRender();
            }
        }
    }

    public boolean canRenderSide(ForgeDirection direction)
    {
        return (renderSides & (1 << direction.ordinal())) != 0;
    }

    public void setRenderSide(ForgeDirection direction, boolean isClear)
    {
        if (isClear)
        {
            renderSides = (byte) (renderSides | (1 << direction.ordinal()));
        }
        else
        {
            renderSides = (byte) (renderSides & ~(1 << direction.ordinal()));

        }

        if (!this.worldObj.isRemote)
        {
            sendDescPacket();
        }

        markDirty();
        markRender();
    }

    public void toggleRenderSide(ForgeDirection direction)
    {
        this.setRenderSide(direction, !canRenderSide(direction));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this._blockToMimic = (Block) Block.blockRegistry.getObject(nbt.getString("blockToMimic"));
        this.metaToMimic = nbt.getInteger("metaToMimic");
        this.renderSides = nbt.getByte("renderSides");
        this.isSolid = nbt.getBoolean("isSold");
    }

    /** Writes a tile entity to NBT. */
    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        if (getMimicBlock() != null)
        {
            nbt.setString("blockToMimic", Block.blockRegistry.getNameForObject(this._blockToMimic));
            nbt.setInteger("metaToMimic", this.metaToMimic);
        }
        nbt.setByte("renderSides", renderSides);
        nbt.setBoolean("isSold", this.isSolid);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side)
    {
        if (canRenderSide(ForgeDirection.getOrientation(side)))
        {
            return Blocks.glass.getIcon(0, 0);
        }
        if (getMimicBlock() != null)
        {
            try
            {
                IIcon blockIcon = getMimicBlock().getIcon(side, getMimicBlockMeta());

                if (blockIcon != null)
                {
                    return blockIcon;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean renderStatic(RenderBlocks renderer, Pos pos, int pass)
    {
        Block block = getMimicBlock() != null ? getMimicBlock() : getBlockType();
        BlockWrapper wrapper = new BlockWrapper(block);
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            wrapper.setRenderSide(dir, !canRenderSide(dir));
        }
        boolean rendered = renderer.renderStandardBlock(wrapper, xi(), yi(), zi());
        if (renderSides != 0)
        {
            wrapper = new BlockWrapper(Blocks.glass);
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
            {
                wrapper.setRenderSide(dir, canRenderSide(dir));
            }
            if (renderer.renderStandardBlock(wrapper, xi(), yi(), zi()))
            {
                rendered = true;
            }
        }
        return rendered;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon()
    {
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icon = iconRegister.registerIcon(ICBMClassic.PREFIX + "camouflage");
    }

    @Override
    protected boolean onPlayerRightClick(EntityPlayer player, int side, Pos hit)
    {
        if (player.getHeldItem() != null && player.canPlayerEdit(xi(), yi(), zi(), side, player.getHeldItem()))
        {
            if (player.getHeldItem().getItem() instanceof ItemBlock)
            {
                //TODO add call back global permission system (Friends list)
                //TODO ensure there is a permission flag for editing in global list so its not an (eta all)
                if (owner == null || owner.equals(player.getGameProfile().getId()))
                {

                    Block block = Block.getBlockFromItem(player.getCurrentEquippedItem().getItem());

                    if (block != getMimicBlock() && block != getBlockType())
                    {
                        if (block.isNormalCube() && (block.getRenderType() == 0 || block.getRenderType() == 31))
                        {
                            if (isServer())
                            {
                                setMimicBlock(block, player.getCurrentEquippedItem().getItemDamage());
                                if (Engine.runningAsDev)
                                {
                                    player.addChatComponentMessage(new ChatComponentText("Camo block set to " + block.getUnlocalizedName()));
                                }
                            }
                            return true;
                        }
                        else if (Engine.runningAsDev && isServer())
                        {
                            player.addChatComponentMessage(new ChatComponentText("Not normal cube or invalid render type"));
                        }
                    }
                    else if (Engine.runningAsDev && isServer())
                    {
                        player.addChatComponentMessage(new ChatComponentText("Same block"));
                    }
                }
                else if (Engine.runningAsDev && isServer())
                {
                    player.addChatComponentMessage(new ChatComponentText("No perms"));
                }
            }
            else if (Engine.runningAsDev && isServer())
            {
                player.addChatComponentMessage(new ChatComponentText("Not a block"));
            }
        }
        else if (Engine.runningAsDev && isServer())
        {
            player.addChatComponentMessage(new ChatComponentText("Empty hand or can not edit"));
        }
        return false;
    }

    @Override
    protected boolean onPlayerRightClickWrench(EntityPlayer player, int side, Pos hit)
    {
        if (player.canPlayerEdit(xi(), yi(), zi(), side, player.getHeldItem()))
        {
            //TODO add call back global permission system (Friends list)
            //TODO ensure there is a permission flag for editing in global list so its not an (eta all)
            if (owner == null || owner.equals(player.getGameProfile().getId()))
            {
                if (isServer())
                {
                    if (player.isSneaking())
                    {
                        toggleCollision();
                        player.addChatComponentMessage(new ChatComponentText("Collision set to " + getCanCollide()));
                    }
                    else
                    {
                        toggleRenderSide(ForgeDirection.getOrientation(side));
                        player.addChatComponentMessage(new ChatComponentText("Side set to render: " + canRenderSide(ForgeDirection.getOrientation(side))));
                    }
                }
                return true;
            }
            else if (Engine.runningAsDev && isServer())
            {
                player.addChatComponentMessage(new ChatComponentText("No perms"));
            }
        }
        else if (Engine.runningAsDev && isServer())
        {
            player.addChatComponentMessage(new ChatComponentText("Empty hand or can not edit"));
        }
        return false;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color.
     * Note only called when first determining what to render.
     */
    @Override
    public int getColorMultiplier()
    {
        try
        {
            if (getMimicBlock() != null)
            {
                return getMimicBlock().colorMultiplier(world(), xi(), yi(), xi());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return 16777215;
    }

    @Override
    public Iterable<Cube> getCollisionBoxes(Cube intersect, Entity entity)
    {
        List<Cube> boxes = new ArrayList<>();
        if (getCanCollide())
        {
            boxes.add(getCollisionBounds());
        }
        return boxes;
    }

    @Override
    public boolean shouldSideBeRendered(int side)
    {
        return true;
    }

    @Override
    public void genRecipes(List<IRecipe> recipes)
    {
        recipes.add(new ShapedOreRecipe(new ItemStack(ICBMClassic.blockCamo, 12),
                "WGW", "G G", "WGW",
                'G', Blocks.vine,
                'W', Blocks.wool));
    }
}
