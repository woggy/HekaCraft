package hekacraft;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Stela extends StoneTableBlock implements ITileEntityProvider
{
	private boolean grand;
	private static int bonemealRadius = 3;
	
	public Stela(int i)
	{
		super(i);
		this.setCreativeTab(null);
		this.setBlockTextureName(this.getTextureName()+".break");
		//If the numbering changes, this will have to as well.
		grand = (i == 11);
	}
	
	public TileEntity createNewTileEntity(World world, int meta)
	{
		if(grand)
		{
			return new GrandStelaTileEntity();
		}
		else
		{
		return new StelaTileEntity();
		}
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return grand;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName());
    }	
	
	//This method checks if other block exists. 
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
	    if(this.grand && world.isAirBlock(i, j+1, k))
	    {
	        world.setBlockToAir(i, j, k);
	        world.removeTileEntity(i, j, k);
	    }
	}	

    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
    	if(this.grand && !world.getBlock(x, y+1, z).isReplaceable(world, x, y+1, z))
			return false;
        return (World.doesBlockHaveSolidTopSurface(world, x, y-1, z) && world.getBlock(x, y, z).isReplaceable(world, x, y, z));
    }

    public void onBlockAdded(World world, int x, int y, int z) 
    {
    	if(this.grand)
    	{
    		world.setBlock(x, y+1, z, HekaCore.stelaDummy);
    	}
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float playerX, float playerY, float playerZ)
    {
    	if(this.grand)
    	{
    		ItemStack heldItem = player.inventory.getCurrentItem();
    		if(heldItem != null && heldItem.getItem() == Items.water_bucket)
    		{
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
    			Stela.doBonemeal(world, x, y, z, player);
        		return true;
    		}
    	}
    	return false;
    }
    
    public static void doBonemeal(World world, int x, int y, int z, EntityPlayer player)
    {
    	for(int i=-bonemealRadius;i<=bonemealRadius;i++)
    	{
    		for(int j=-bonemealRadius;j<=bonemealRadius;j++)
    		{
    			Block block = world.getBlock(x+i, y, z+j);

    	        if (block instanceof IGrowable)
    	        {
    	            IGrowable igrowable = (IGrowable)block;

    	            if (igrowable.func_149851_a(world, x+i, y, z+j, world.isRemote))
    	            {
    	                if (!world.isRemote)
    	                {
    	                    if (igrowable.func_149852_a(world, world.rand, x+i, y, z+j))
    	                    {
    	                        igrowable.func_149853_b(world, world.rand, x+i, y, z+j);
    	                    }
    	                    world.playAuxSFX(2005, x+i, y, z+j, 0);
    	                }
    	            }
    	        }
    		}
    	}
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		if(this.grand)
			return HekaCore.itemGrandStela;
		else
			return HekaCore.itemStela;
	}
}
