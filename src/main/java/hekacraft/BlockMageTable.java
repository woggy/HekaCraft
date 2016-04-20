package hekacraft;

import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMageTable extends BlockSunTable implements ITileEntityProvider
{    
	public BlockMageTable()
	{
		super();
		this.setBlockName("blockMageTable");
		this.setBlockTextureName("hekacraft:hekacraft.block.mageTable.item");		
	}
	
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new MageTableTileEntity();
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
    
	public Item getItemDropped(int meta, Random foo, int bar)
	{
		return HekaCore.itemMageTable;
	}
}
