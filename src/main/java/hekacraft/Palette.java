package hekacraft;

import net.minecraft.item.Item;

public class Palette extends Item
{
	public Palette()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("palette");
		this.setTextureName("hekacraft:hekacraft.tool.palette");
	}
}
	
