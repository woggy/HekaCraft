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
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = HekaCore.MODID, name = HekaCore.MODNAME, version = HekaCore.VERSION, dependencies = "required-after:Baubles")
public class HekaCore
{
    public static final String MODID = "hekacraft";
    public static final String MODNAME = "HekaCraft";
    public static final String VERSION = "0.1";
    
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
		FAIENCE ("faience", new ItemStack(Items.clay_ball), new ItemStack(Item.getItemFromBlock(Blocks.glass_pane))),
		LAPIS	("lapis", new ItemStack(Item.getItemFromBlock(Blocks.glass_pane)), new ItemStack(Items.dye,1,4)),
		EMERALD ("emerald", new ItemStack(Items.dye,1,4), new ItemStack(Items.emerald)),
		DIAMOND ("diamond", new ItemStack(Items.emerald), new ItemStack(Items.diamond));
		
		private final String materialName;
		private final ItemStack edgeItem;
		private final ItemStack centerItem;
		ScarabType(String materialName, ItemStack edgeItem, ItemStack centerItem)
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
    	GameRegistry.addRecipe(new ItemStack(pesheskef), "fff", " f ", 'f', new ItemStack(Items.flint));
    	
    	GameRegistry.addRecipe(new ItemStack(palette), "xrw", "ccc", "ygl",
    													'x', new ItemStack(Items.coal),
    													'r', new ItemStack(Items.dye,1,1),
    													'w', new ItemStack(Items.egg),
    													'c', new ItemStack(Items.clay_ball),
    													'y', new ItemStack(Items.dye,1,11),
    													'g', new ItemStack(Items.dye,1,2),
    													'l', new ItemStack(Items.dye,1,4));
    	GameRegistry.addRecipe(new ItemStack(palette), "xrw", "ccc", "ygl",
														'x', new ItemStack(Items.coal,1,1),
    													'r', new ItemStack(Items.dye,1,1),
    													'w', new ItemStack(Items.egg),
    													'c', new ItemStack(Items.clay_ball),
    													'y', new ItemStack(Items.dye,1,11),
    													'g', new ItemStack(Items.dye,1,2),
    													'l', new ItemStack(Items.dye,1,4));
    	
    	GameRegistry.addRecipe(new ItemStack(chisel), "x ", " y", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(Items.stick));
    	
    	GameRegistry.addRecipe(new ItemStack(itemMageTable), "fbp", "www", 
    													'f', new ItemStack(pesheskef),
    													'b', new ItemStack(Items.bowl),
    													'p', new ItemStack(palette),
    													'w', new ItemStack(Blocks.planks));
    	ClientRegistry.bindTileEntitySpecialRenderer(MageTableTileEntity.class, new MageTableRenderer());
    	GameRegistry.registerTileEntity(MageTableTileEntity.class, "mageTableTileEntity");
    	
    	for (ScarabType scarab : ScarabType.values())
    	{
    		MageTableCraftingManager.getInstance().addRecipe(new ItemStack(scarabHash.get(scarab.materialName)), " n ", "ece", "e e", 'n', new ItemStack(Items.gold_nugget), 'e', scarab.edgeItem, 'c', scarab.centerItem);
    		MageTableCraftingManager.getInstance().addRecipe(new ItemStack(scarabNeckHash.get(scarab.materialName)), "xyx", "y y", "xyx", 'x', new ItemStack(scarabHash.get(scarab.materialName)), 'y', new ItemStack(Items.string));
    	}
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
