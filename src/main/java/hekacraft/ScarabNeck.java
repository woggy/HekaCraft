package hekacraft;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ScarabNeck extends Item implements IBauble 
{
	//private static int armor_value;
	
	public ScarabNeck(String materialName)
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName(materialName+"ScarabNeck");
		this.setTextureName("hekacraft:"+materialName+"ScarabNeck");
		
//		switch(materialName)
//		{
//		case "faience": armor_value = 1; 
//						break;
//		case "lapis":	armor_value = 2;
//						break;
//		case "emerald":	armor_value = 3;
//						break;
//		case "diamond":	armor_value = 4;
//						break;
//		default:		armor_value = 0;
//						break;
//		}
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
