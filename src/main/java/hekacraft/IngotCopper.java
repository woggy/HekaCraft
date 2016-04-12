package hekacraft;

import net.minecraft.item.Item;

public class IngotCopper extends Item
{
	public IngotCopper()
	{
		this.setMaxStackSize(64);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("copper");
		this.setTextureName("hekacraft:hekacraft.copper.ingot");
	}
}
