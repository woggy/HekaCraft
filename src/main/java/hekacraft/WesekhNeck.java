package hekacraft;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WesekhNeck extends Item implements IBauble 
{	
	public WesekhNeck()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("wesekhNeck");
		this.setTextureName("hekacraft:hekacraft.necklace.wesekh");
	}
	
	public BaubleType getBaubleType(ItemStack itemstack)
	{
		return BaubleType.AMULET;
	}
	
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
	}
	
	public void onEquipped(ItemStack itemstack, EntityLivingBase player)
	{	
	}
	
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
	{
	}

	public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
	{
		return true;
	}
	
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
	{
		return true;
	}
}
