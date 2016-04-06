package hekacraft;

import net.minecraft.item.Item;

public class Scarab extends Item
{
	public Scarab(String materialName)
	{
		this.setMaxStackSize(64);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName(materialName+"Scarab");
		this.setTextureName("hekacraft:hekacraft.scarab."+materialName);
	}
}
