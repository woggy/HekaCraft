package hekacraft;

import net.minecraft.item.Item;

public class ItemMalachite extends Item
{
	public ItemMalachite()
	{
		this.setMaxStackSize(64);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("malachite");
		this.setTextureName("hekacraft:hekacraft.malachite.item");
	}
}
