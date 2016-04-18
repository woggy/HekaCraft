package hekacraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStoneTable extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon topTexture;
    @SideOnly(Side.CLIENT)
    private IIcon frontTexture;
    
	public BlockStoneTable()
	{
		super(Material.wood);
		this.setHardness(2.5F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("blockStoneTable");
		this.setBlockTextureName("hekacraft:hekacraft.block.stoneTable");
		this.setCreativeTab(HekaCore.creativeTab);
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.topTexture : (side == 0 ? Blocks.planks.getBlockTextureFromSide(side) : (side != 2 && side != 4 ? this.blockIcon : this.frontTexture));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName() + ".side");
        this.topTexture = register.registerIcon(this.getTextureName() + ".top");
        this.frontTexture = register.registerIcon(this.getTextureName() + ".front");
    }
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) 
	{
		return new StoneTableTileEntity();
	}
	
    public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int p_149727_6_, float playerX, float playerY, float playerZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	player.openGui(HekaCore.instance, GuiHandler.Types.STONE_TABLE.ordinal(), world, blockX, blockY, blockZ);
            return true;
        }
    }

}
