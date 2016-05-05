package hekacraft;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockMageTableDummy extends BlockSunTableDummy
{
    public static final int[][] directions = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
    
	public BlockMageTableDummy()
	{
		super();
		this.setBlockName("mageTable");
		this.setBlockTextureName("hekacraft:hekacraft.block.mageTable.break");
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