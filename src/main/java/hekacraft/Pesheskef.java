package hekacraft;

import net.minecraft.item.ItemShears;

public class Pesheskef extends ItemShears
{
	public Pesheskef()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(50);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("pesheskef");
		this.setTextureName("hekacraft:pesheskef");
	}
}
