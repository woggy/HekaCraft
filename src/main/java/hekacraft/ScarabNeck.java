package hekacraft;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ScarabNeck extends Item implements IBauble 
{
	private int armorValue;
	
	public ScarabNeck(String materialName)
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName(materialName+"ScarabNeck");
		this.setTextureName("hekacraft:hekacraft.necklace.scarab."+materialName);
		
		switch(materialName)
		{
		case "faience": armorValue = 1;
						break;
		case "lapis":	armorValue = 2;
						break;
		case "emerald":	armorValue = 3;
						break;
		case "diamond":	armorValue = 4;
						break;
		default:		armorValue = 0;
						break;
		}
	}
	
	public int getArmorValue()
	{
		return armorValue;
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
