package hekacraft;

import net.minecraft.item.Item;

public class FruitIncense extends Item
{
	public FruitIncense()
	{
		this.setMaxStackSize(64);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("fruitIncense");
		this.setTextureName("hekacraft:hekacraft.plant.incense.fruit");
	}
}
