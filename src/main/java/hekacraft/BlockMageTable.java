package hekacraft;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMageTable extends BlockWorkbench 
{
    public static final int[][] directions = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
    @SideOnly(Side.CLIENT)
    private IIcon tableTop;
    @SideOnly(Side.CLIENT)
    private IIcon tableBottom;
    @SideOnly(Side.CLIENT)
    private IIcon tableShort;
    
	public BlockMageTable()
	{
		super();
		this.setHardness(2.5F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("blockMageTable");
		this.setBlockTextureName("hekacraft:hekacraft.block.mageTable");	
	}
	
    public static int getDirection(int meta)
    {
        return meta & 3;
    }
	
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        switch(side)
        {
        	case 0: 
        		return this.tableBottom;
        	case 1:	
        		return this.tableTop;
        	case 2:
        	case 4:
        		if(getDirection(meta) % 2 == 0)
        			return this.tableShort;
        		else
        			return this.blockIcon;
        	case 3:
        	case 5:
        		if(getDirection(meta) % 2 == 0)
        			return this.blockIcon;
        		else
        			return this.tableShort;
        	default:	//shouldn't ever happen.
        		return this.blockIcon;		
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName() + ".long");
        this.tableTop = register.registerIcon(this.getTextureName() + ".top");
        this.tableBottom = register.registerIcon(this.getTextureName() + ".bottom");
        this.tableShort = register.registerIcon(this.getTextureName() + ".short");
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
    
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int meta = world.getBlockMetadata(x, y, z);
        int i = getDirection(meta);

        if ((meta & 8) != 0)
        {
            if (world.getBlock(x - directions[i][0], y, z - directions[i][1]) != this)
            {
            	world.setBlockToAir(x, y, z);
            }
        }
        else if (world.getBlock(x + directions[i][0], y, z + directions[i][1]) != this)
        {
            world.setBlockToAir(x, y, z);

            if (!world.isRemote)
            {
                this.dropBlockAsItem(world, x, y, z, meta, 0);
            }
        }
    }
    public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_)
    {
        return (meta & 8) != 0 ? Item.getItemById(0) : HekaCore.itemMageTable;
    }
}
