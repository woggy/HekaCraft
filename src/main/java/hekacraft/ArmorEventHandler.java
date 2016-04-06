package hekacraft;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import baubles.api.BaublesApi;

public class ArmorEventHandler
{
	@SubscribeEvent
	public void soakDamage(LivingHurtEvent event)
	{
		//System.out.println("Took " + event.ammount + " damage from " + event.source);
		if (event.entity instanceof EntityPlayer)
		{
			IInventory baubles = BaublesApi.getBaubles((EntityPlayer) event.entity);
			for (int i=0; i<baubles.getSizeInventory(); i++)
			{
				ItemStack possibauble = baubles.getStackInSlot(i);
				if (possibauble != null)
					if (possibauble.getItem() instanceof ScarabNeck)
					{
						int soakValue = ((ScarabNeck) possibauble.getItem()).getArmorValue(); 
						//System.out.println("Wearing a " + possibauble.getItem().getUnlocalizedName() + " necklace! Damage reduced by " + soakValue);
						event.ammount -= soakValue;
					}
			}
		}
	}
}
