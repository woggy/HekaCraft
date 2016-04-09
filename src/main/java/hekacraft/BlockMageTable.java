package hekacraft;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMageTable extends BlockWorkbench implements ITileEntityProvider
{
    public static final int[][] directions = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
    
	public BlockMageTable()
	{
		super();
		this.setHardness(2.5F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("blockMageTable");
		this.setBlockTextureName("hekacraft:hekacraft.block.mageTable.item");		
	}
	
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new MageTableTileEntity();
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
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
	    if(world.isAirBlock(i+directions[meta][0], j, k+directions[meta][1]))
	    {
	        world.setBlockToAir(i, j, k);
	        world.removeTileEntity(i, j, k);
	    }
	}
	
	public Item getItemDropped(int meta, Random foo, int bar)
	{
		return HekaCore.itemMageTable;
	}
}
