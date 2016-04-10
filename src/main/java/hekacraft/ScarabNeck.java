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
		
		if(materialName == "faience")
		{
			armorValue = 1;
		}
		else if(materialName == "lapis")
		{
			armorValue = 2;
		}
		else if(materialName == "emerald")
		{
			armorValue = 3;
		}
		else if(materialName == "diamond")
		{
			armorValue = 4;
		}
		else
		{
			armorValue = 0;
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
