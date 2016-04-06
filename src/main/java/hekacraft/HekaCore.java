package hekacraft;

import java.util.HashMap;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
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
	public static HashMap<String, Scarab> scarabHash;
	public static HashMap<String, ScarabNeck> scarabNeckHash;
	public enum ScarabType
	{
		FAIENCE ("faience", Items.clay_ball, Item.getItemFromBlock(Blocks.glass_pane), 0),
		LAPIS	("lapis", Item.getItemFromBlock(Blocks.glass_pane), Items.dye, 2),
		EMERALD ("emerald", Items.dye, Items.emerald, 1),
		DIAMOND ("diamond", Items.emerald, Items.diamond, 0);
		
		private final String materialName;
		private final ItemStack edgeItem;
		private final ItemStack centerItem;
		ScarabType(String materialName, Item edgeItem, Item centerItem, int lapisPointer)
		{
			this.materialName = materialName;
			this.edgeItem = new ItemStack(edgeItem);
			if (lapisPointer == 1)
				this.edgeItem.setItemDamage(4);
			this.centerItem = new ItemStack(centerItem);
			if (lapisPointer == 2)
				this.centerItem.setItemDamage(4);
		}
	}

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(soakDamage);
    	
    	pesheskef = new Pesheskef();
    	GameRegistry.registerItem(pesheskef, "Pesheskef");

    	scarabHash = new HashMap<String, Scarab>();
    	scarabNeckHash = new HashMap<String, ScarabNeck>();
    	for (ScarabType scarab : ScarabType.values())
    	{
    		scarabHash.put(scarab.materialName, new Scarab(scarab.materialName));
    		scarabNeckHash.put(scarab.materialName, new ScarabNeck(scarab.materialName));
    		GameRegistry.registerItem(scarabHash.get(scarab.materialName),scarab.materialName+"Scarab");
    		GameRegistry.registerItem(scarabNeckHash.get(scarab.materialName), scarab.materialName+"ScarabNeck");
    	}
    	
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	GameRegistry.addRecipe(new ItemStack(pesheskef), "fff", " f ", 'f', new ItemStack(Items.flint));
    	for (ScarabType scarab : ScarabType.values())
    	{
    		GameRegistry.addRecipe(new ItemStack(scarabHash.get(scarab.materialName)), " n ", "ece", "e e", 'n', new ItemStack(Items.gold_nugget), 'e', scarab.edgeItem, 'c', scarab.centerItem);
    		GameRegistry.addRecipe(new ItemStack(scarabNeckHash.get(scarab.materialName)), "xyx", "y y", "xyx", 'x', new ItemStack(scarabHash.get(scarab.materialName)), 'y', new ItemStack(Items.string));
    	}
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
