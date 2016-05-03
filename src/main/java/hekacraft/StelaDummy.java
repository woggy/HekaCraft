package hekacraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class StelaDummy extends Block
{
	public StelaDummy()
	{
		super(Material.rock);
		this.setBlockName("stelaDummy");
		this.setBlockTextureName("hekacraft:hekacraft.block.grandStela.item");
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 0);
	}    
	
	//This method checks if other block exists. 
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
	    if(world.isAirBlock(i, j-1, k))
	    {
	        world.setBlockToAir(i, j, k);
	    }
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	        return false;
	}
	
	@Override
	public boolean isOpaqueCube(){
	        return false;
	}
	
	public Item getItemDropped(int meta, Random foo, int bar)
	{
		//hardcoded to drop the Grand Stela. Ugly, but functional.
		return Item.getItemFromBlock(HekaCore.stoneTableBlocks[11]);
	}

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float playerX, float playerY, float playerZ)
    {
    	ItemStack heldItem = player.inventory.getCurrentItem();
    		if(heldItem != null && heldItem.getItem() == Items.water_bucket)
    		{
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
    			Stela.doBonemeal(world, x, y-1, z, player);
        		return true;
    		}
    	return false;
    }
}