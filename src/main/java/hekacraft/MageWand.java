package hekacraft;

import net.minecraft.item.Item;

public class MageWand extends Item 
{
	public MageWand()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("mageWand");
		this.setTextureName("hekacraft:hekacraft.tool.mageWand");
	}
}
