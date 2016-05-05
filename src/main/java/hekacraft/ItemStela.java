package hekacraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemStela extends Item 
{
	private boolean grand;
	
	public ItemStela(boolean grand)
	{
		this.grand = grand;
		this.setCreativeTab(HekaCore.creativeTab);
		if(grand)
		{
			this.setUnlocalizedName("grandStela");
			this.setTextureName("hekacraft:hekacraft.block.grandStela.item");			
		}
		else
		{
			this.setUnlocalizedName("stela");
			this.setTextureName("hekacraft:hekacraft.block.stela.item");
		}
	}
	
	public boolean onItemUse(ItemStack stela, EntityPlayer player, World world, int x, int y, int z, int side, float playerX, float playerY, float playerZ)
    {
		System.out.println("foo");
        if (side == 1)
        {
            ++y;
        }

        if (side == 2)
        {
            --z;
        }

        if (side == 3)
        {
            ++z;
        }

        if (side == 4)
        {
            --x;
        }

        if (side == 5)
        {
            ++x;
        }

        if (!player.canPlayerEdit(x, y, z, side, stela))
        {
            return false;
        }
        else if (!HekaCore.stoneTableBlocks[this.grand ? 11 : 10].canPlaceBlockAt(world, x, y, z))
        {
            return false;
        }
        else if (world.isRemote)
        {
            return true;
        }
        else
        {
    		System.out.println("bar");
            int i1 = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 4.0F / 360.0F) + 0.5D) & 3;
            world.setBlock(x, y, z, HekaCore.stoneTableBlocks[this.grand ? 11 : 10], i1, 3);

            --stela.stackSize;
            TileEntitySign tileentitysign = (TileEntitySign)world.getTileEntity(x, y, z);

            if (tileentitysign != null)
            {
                player.func_146100_a(tileentitysign);
            }

    		System.out.println("baz");
            return true;
        }
    }
}
