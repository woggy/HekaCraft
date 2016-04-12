package hekacraft;

import java.util.HashMap;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = HekaCore.MODID, name = HekaCore.MODNAME, version = HekaCore.VERSION, dependencies = "required-after:Baubles")
public class HekaCore
{
    public static final String MODID = "hekacraft";
    public static final String MODNAME = "@NAME@";
    public static final String VERSION = "@VERSION@";
    
    @Instance(value = HekaCore.MODID)
    public static HekaCore instance;

    public static final CreativeTabs creativeTab = new CreativeTabs("HekaCraft")
    {
    	@Override
    	public Item getTabIconItem()
    	{
    		return Item.getItemFromBlock(Blocks.sandstone);
    	}
    };
    
    ArmorEventHandler soakDamage = new ArmorEventHandler();
    
    public static Item pesheskef;
    public static Item palette;
    public static Item chisel;
	public static HashMap<String, Scarab> scarabHash;
	public static HashMap<String, ScarabNeck> scarabNeckHash;
	public enum ScarabType
	{
		FAIENCE ("faience", "clay", "paneGlass"),
		LAPIS	("lapis", "paneGlass", "dyeBlue"),
		EMERALD ("emerald", "dyeBlue", "gemEmerald"),
		DIAMOND ("diamond", "gemEmerald", "gemDiamond");
		
		private final String materialName;
		private final String edgeItem;
		private final String centerItem;
		ScarabType(String materialName, String edgeItem, String centerItem)
		{
			this.materialName = materialName;
			this.edgeItem = edgeItem;
			this.centerItem = centerItem;
		}
	}
	
	public static Item itemMageTable;
	public static Block blockMageTable;
	public static Block blockMageTableDummy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(soakDamage);
    	NetworkRegistry.INSTANCE.registerGuiHandler(HekaCore.instance, new GuiHandler());
    	
    	pesheskef = new Pesheskef();
    	GameRegistry.registerItem(pesheskef, "Pesheskef");
    	palette = new Palette();
    	GameRegistry.registerItem(palette, "ScribePalette");
    	chisel = new Chisel();
    	GameRegistry.registerItem(chisel, "Chisel");
    	

    	scarabHash = new HashMap<String, Scarab>();
    	scarabNeckHash = new HashMap<String, ScarabNeck>();
    	for (ScarabType scarab : ScarabType.values())
    	{
    		scarabHash.put(scarab.materialName, new Scarab(scarab.materialName));
    		scarabNeckHash.put(scarab.materialName, new ScarabNeck(scarab.materialName));
    		GameRegistry.registerItem(scarabHash.get(scarab.materialName),scarab.materialName+"Scarab");
    		GameRegistry.registerItem(scarabNeckHash.get(scarab.materialName), scarab.materialName+"ScarabNeck");
    	}

    	itemMageTable = new ItemMageTable();
    	GameRegistry.registerItem(itemMageTable, "ItemMageTable");
    	blockMageTable = new BlockMageTable();
    	GameRegistry.registerBlock(blockMageTable, "BlockMageTable");
    	blockMageTableDummy = new BlockMageTableDummy();
    	GameRegistry.registerBlock(blockMageTableDummy, "BlockMageTableDummy"); 	
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	//Forge doesn't have this registered, despite being a vanilla item. Done here so that ScarabType can pass consistent strings.
    	OreDictionary.registerOre("clay", Items.clay_ball);
    	
    	GameRegistry.addRecipe(new ItemStack(pesheskef), "fff", " f ", 'f', new ItemStack(Items.flint));
    	
    	//Wildcard usage allows this to pick up both coal and charcoal.
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(palette), "xrw", "ccc", "ygl",
    													'x', new ItemStack(Items.coal,1,OreDictionary.WILDCARD_VALUE),
    													'r', "dyeRed",
    													'w', new ItemStack(Items.egg),
    													'c', "clay",
    													'y', "dyeYellow",
    													'g', "dyeGreen",
    													'l', "dyeBlue"));

    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(chisel), "x ", " y", 'x', "ingotIron", 'y', "stickWood"));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemMageTable), "fbp", "www", 
    													'f', new ItemStack(pesheskef),
    													'b', new ItemStack(Items.bowl),
    													'p', new ItemStack(palette),
    													'w', "plankWood"));
    	ClientRegistry.bindTileEntitySpecialRenderer(MageTableTileEntity.class, new MageTableRenderer());
    	GameRegistry.registerTileEntity(MageTableTileEntity.class, "mageTableTileEntity");
    	
    	for (ScarabType scarab : ScarabType.values())
    	{
    		MageTableCraftingManager.getInstance().addRecipe(new ShapedOreRecipe(new ItemStack(scarabHash.get(scarab.materialName)), " n ", "ece", "e e", 'n', "nuggetGold", 'e', scarab.edgeItem, 'c', scarab.centerItem));
    		MageTableCraftingManager.getInstance().addRecipe(new ShapedOreRecipe(new ItemStack(scarabNeckHash.get(scarab.materialName)), "xyx", "y y", "xyx", 'x', new ItemStack(scarabHash.get(scarab.materialName)), 'y', new ItemStack(Items.string)));
    	}
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
