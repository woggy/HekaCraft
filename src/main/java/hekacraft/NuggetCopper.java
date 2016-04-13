package hekacraft;

import net.minecraft.item.Item;

public class NuggetCopper extends Item
{
	public NuggetCopper()
	{
		this.setMaxStackSize(64);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("copperNugget");
		this.setTextureName("hekacraft:hekacraft.copper.nugget");
	}
}
