package icbm.contraption;

import icbm.api.ICBM;
import icbm.contraption.block.BlockCamouflage;
import icbm.contraption.block.BlockConcrete;
import icbm.contraption.block.BlockGlassButton;
import icbm.contraption.block.BlockGlassPressurePlate;
import icbm.contraption.block.BlockProcimityDetector;
import icbm.contraption.block.BlockReinforcedGlass;
import icbm.contraption.block.BlockSpikes;
import icbm.contraption.block.ItemBlockConcreate;
import icbm.core.CreativeTabICBM;
import icbm.core.ICBMConfiguration;
import icbm.core.ICBMCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.item.ElectricItemHelper;
import universalelectricity.core.item.ItemElectric;
import calclavia.lib.UniversalRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ICBMContraption.NAME, name = ICBMContraption.NAME, version = ICBM.VERSION, dependencies = "after:AtomicScience", useMetadata = true)
@NetworkMod(channels = ICBMContraption.CHANNEL, clientSideRequired = true, serverSideRequired = false, packetHandler = ContraptionPacketHandler.class)
public class ICBMContraption extends ICBMCore
{
    public static final String NAME = ICBM.NAME + "|Contraption";
    public static final String CHANNEL = ICBM.NAME + "|C";

    @Instance(NAME)
    public static ICBMContraption instance;

    @Metadata(NAME)
    public static ModMetadata metadata;

    @SidedProxy(clientSide = "icbm.contraption.ClientProxy", serverSide = "icbm.contraption.CommonProxy")
    public static CommonProxy proxy;

    // Blocks
    public static Block bBuoLiPan, bBuoLiEnNiu, bYinGanQi, bZha, bYinXing, bNiTu, bBuoLi;

    // Items
    public static Item itYao;
    public static ItemElectric itHuoLaunQi;
    public static ItemElectric itGenZongQi;

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        NetworkRegistry.instance().registerGuiHandler(this, ICBMContraption.proxy);

        ICBMConfiguration.CONFIGURATION.load();

        // Blocks
        bBuoLiPan = new BlockGlassPressurePlate(ICBMConfiguration.CONFIGURATION.getBlock("Glass Pressure Plate", ICBM.BLOCK_ID_PREFIX - 1).getInt());
        bBuoLiEnNiu = new BlockGlassButton(ICBMConfiguration.CONFIGURATION.getBlock("Glass Button", ICBM.BLOCK_ID_PREFIX - 2).getInt());
        bYinGanQi = new BlockProcimityDetector(ICBM.BLOCK_ID_PREFIX - 3);
        bZha = new BlockSpikes(ICBM.BLOCK_ID_PREFIX - 4);
        bYinXing = new BlockCamouflage(ICBM.BLOCK_ID_PREFIX - 5);
        bNiTu = new BlockConcrete(ICBM.BLOCK_ID_PREFIX - 6);
        bBuoLi = new BlockReinforcedGlass(ICBM.BLOCK_ID_PREFIX - 7);

        // ITEMS
        itYao = new ItemAntidote(ICBMConfiguration.CONFIGURATION.getItem("ItemID3", ICBM.ITEM_ID_PREFIX + 2).getInt());
        itHuoLaunQi = new ItemSignalDisrupter(ICBMConfiguration.CONFIGURATION.getItem("ItemID10", ICBM.ITEM_ID_PREFIX + 9).getInt());
        itGenZongQi = new ItemTracker(ICBMConfiguration.CONFIGURATION.getItem("ItemID11", ICBM.ITEM_ID_PREFIX + 10).getInt());

        ICBMConfiguration.CONFIGURATION.save();

        CreativeTabICBM.itemStack = new ItemStack(bYinGanQi);

        // -- Registering Blocks
        GameRegistry.registerBlock(bBuoLiPan, "bBuoLiPan");
        GameRegistry.registerBlock(bBuoLiEnNiu, "bBuoLiEnNiu");
        GameRegistry.registerBlock(bYinGanQi, "bYinGanQi");
        GameRegistry.registerBlock(bYinXing, "bYinXing");
        GameRegistry.registerBlock(bBuoLi, "bBuoLi");
        GameRegistry.registerBlock(bZha, ItemblockSpikes.class, "bZha");
        GameRegistry.registerBlock(bNiTu, ItemBlockConcreate.class, "bNiTu");

        ICBMContraption.proxy.preInit();
    }

    @EventHandler
    public void load(FMLInitializationEvent evt)
    {
        super.init(evt);
        ICBMCore.setModMetadata(NAME, metadata);
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);

        /** Add all Recipes */
        // Spikes
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bZha, 6), new Object[] { "CCC", "BBB", 'C', Block.cactus, 'B', UniversalRecipe.SECONDARY_METAL.get() }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bZha, 6), new Object[] { "CCC", "BBB", 'C', Block.cactus, 'B', Item.ingotIron }));
        GameRegistry.addRecipe(new ItemStack(bZha, 1, 1), new Object[] { "E", "S", 'E', ICBMCore.itemPoisonPowder, 'S', bZha });
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bZha, 1, 2), new Object[] { "E", "S", 'E', "dustSulfur", 'S', bZha }));

        // Camouflage
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bYinXing, 12), new Object[] { "WGW", "GCG", "WGW", 'C', UniversalRecipe.CIRCUIT_T1.get(), 'G', Block.glass, 'W', new ItemStack(Block.cloth, 1, OreDictionary.WILDCARD_VALUE) }));

        // Tracker
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itGenZongQi), new Object[] { " Z ", "SBS", "SCS", 'Z', Item.compass, 'C', UniversalRecipe.CIRCUIT_T1.get(), 'B', UniversalRecipe.BATTERY.get(), 'S', UniversalRecipe.PRIMARY_METAL.get() }));

        // Glass Pressure Plate
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ICBMContraption.bBuoLiPan, 1, 0), new Object[] { "##", '#', Block.glass }));

        // Glass Button
        GameRegistry.addRecipe(new ItemStack(bBuoLiEnNiu, 2), new Object[] { "G", "G", 'G', Block.glass });

        // Proximity Detector
        GameRegistry.addRecipe(new ShapedOreRecipe(bYinGanQi, new Object[] { "SSS", "S?S", "SSS", 'S', UniversalRecipe.PRIMARY_METAL.get(), '?', ElectricItemHelper.getUncharged(itGenZongQi) }));

        // Signal Disrupter
        GameRegistry.addRecipe(new ShapedOreRecipe(itHuoLaunQi, new Object[] { "WWW", "SCS", "SSS", 'S', UniversalRecipe.PRIMARY_METAL.get(), 'C', UniversalRecipe.CIRCUIT_T1.get(), 'W', UniversalRecipe.WIRE.get() }));

        // Antidote
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itYao, 6), new Object[] { "@@@", "@@@", "@@@", '@', Item.pumpkinSeeds }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itYao), new Object[] { "@@@", "@@@", "@@@", '@', Item.seeds }));

        // Concrete
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bNiTu, 8, 0), new Object[] { "SGS", "GWG", "SGS", 'G', Block.gravel, 'S', Block.sand, 'W', Item.bucketWater }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bNiTu, 8, 1), new Object[] { "COC", "OCO", "COC", 'C', new ItemStack(bNiTu, 1, 0), 'O', Block.obsidian }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bNiTu, 8, 2), new Object[] { "COC", "OCO", "COC", 'C', new ItemStack(bNiTu, 1, 1), 'O', UniversalRecipe.PRIMARY_METAL.get() }));

        // Reinforced Glass
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bBuoLi, 8), new Object[] { "IGI", "GIG", "IGI", 'G', Block.glass, 'I', Item.ingotIron }));

        ICBMContraption.proxy.init();
    }

    @Override
    protected String getChannel()
    {
        return CHANNEL;
    }
}
