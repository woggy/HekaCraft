package hekacraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMageTableDummy extends Block implements ITileEntityProvider
{
    public static final int[][] directions = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
    
	public BlockMageTableDummy()
	{
		super(Material.wood);
		this.setHardness(2.5F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("mageTable");
		this.setBlockTextureName("hekacraft:hekacraft.block.mageTable.item");
	}    
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int p_149727_6_, float playerX, float playerY, float playerZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	player.openGui(HekaCore.instance, GuiHandler.Types.MAGE_TABLE.ordinal(), world, blockX, blockY, blockZ);
            return true;
        }
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
	
	public TileEntity createNewTileEntity(World world, int meta) {
	        return new MageTableDummyTileEntity();
	}
	
	public Item getItemDropped(int meta, Random foo, int bar)
	{
		return HekaCore.itemMageTable;
	}
}