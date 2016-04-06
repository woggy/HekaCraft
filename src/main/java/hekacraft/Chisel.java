package hekacraft;

import net.minecraft.item.Item;

public class Chisel extends Item
{
	public Chisel()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("chisel");
		this.setTextureName("hekacraft:hekacraft.tool.chisel");
	}
}
	
