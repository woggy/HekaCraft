package hekacraft;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import baubles.api.BaublesApi;

public class TrinketEventHandler
{
	@SubscribeEvent
	public void soakDamage(LivingHurtEvent event)
	{
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
						event.ammount -= soakValue;
					}
			}
		}
	}
	
	@SubscribeEvent
	public void preventDeath(LivingDeathEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			IInventory baubles = BaublesApi.getBaubles(player);
			for (int i=0; i<baubles.getSizeInventory(); i++)
			{
				ItemStack possibauble = baubles.getStackInSlot(i);
				if (possibauble != null)
					if (possibauble.getItem() instanceof WesekhNeck)
					{
						player.setHealth(player.getMaxHealth());
						baubles.setInventorySlotContents(i, null);
						player.addChatMessage(new ChatComponentText(possibauble.getDisplayName() + " shatters, saving you from death."));
						event.setCanceled(true);
					}
			}
		}
	}
}
