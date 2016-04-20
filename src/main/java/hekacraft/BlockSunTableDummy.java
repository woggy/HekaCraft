package hekacraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSunTableDummy extends Block
{
    public static final int[][] directions = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
    
	public BlockSunTableDummy()
	{
		super(Material.wood);
		this.setHardness(2.5F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("sunTable");
		this.setBlockTextureName("hekacraft:hekacraft.block.sunTable.item");
	}    
	
	//This method checks if other block exists. 
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
		int meta = world.getBlockMetadata(i, j, k);
	    if(world.isAirBlock(i-directions[meta][0], j, k-directions[meta][1]))
	    {
	        world.setBlockToAir(i, j, k);
	        world.removeTileEntity(i, j, k);
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
		return HekaCore.itemSunTable;
	}
}