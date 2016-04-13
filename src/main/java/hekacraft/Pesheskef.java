package hekacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class Pesheskef extends ItemShears
{
	public Pesheskef()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(50);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("pesheskef");
		this.setTextureName("hekacraft:hekacraft.tool.pesheskef");
	}
	
    public boolean onItemUse(ItemStack tool, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int side, float playerX, float playerY, float playerZ)
    {
    	if(world.isRemote)
    		return true;
    	
    	Block block = world.getBlock(blockX, blockY, blockZ);
    	if (block == HekaCore.plantIncense)
    	{
    		//Get the stuff we sheared off, and spawn it into the world.
            ArrayList<ItemStack> drops = ((IShearable)block).onSheared(tool, player.worldObj, blockX, blockY, blockZ,
                    EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, tool));
            Random rand = new Random();

            for(ItemStack stack : drops)
            {
                float f = 0.7F;
                double d  = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                EntityItem entityitem = new EntityItem(player.worldObj, (double)blockX + d, (double)blockY + d1, (double)blockZ + d2, stack);
                entityitem.delayBeforeCanPickup = 10;
                player.worldObj.spawnEntityInWorld(entityitem);
            }

            tool.damageItem(1, player);
            
            //If we sheared a full-grown plant, trim it back
            //Otherwise, remove it entirely.
            if(world.getBlockMetadata(blockX, blockY, blockZ) == 4)
            {
            	world.setBlockMetadataWithNotify(blockX, blockY, blockZ, 2, 2);
            }
            else
            {
            	world.setBlockToAir(blockX, blockY, blockZ);
            }
        	return true;
    	}
    	return false;
    }
}
