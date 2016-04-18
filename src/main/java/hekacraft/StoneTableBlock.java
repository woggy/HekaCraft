package hekacraft;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class StoneTableBlock extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon topTexture;
    
	static String[] blockNames = {	"redColumn","yellowColumn","greenColumn","blueColumn",
									"blackColumn","whiteColumn","plantColumn","djedCapital",
									"hathorCapital","lotusCapital","stela","grandStela"};	
	private int index;
	
	public StoneTableBlock(int i)
	{
		super(Material.rock);
		this.index = i;
		this.setCreativeTab(HekaCore.creativeTab);
		this.setBlockName(blockNames[i]);
		this.setBlockTextureName("hekacraft:hekacraft.block." + blockNames[i]);
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 0);
	}   
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.topTexture : (side == 0 ? this.topTexture : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName() + ".side");
        this.topTexture = register.registerIcon(this.getTextureName() + ".top");
    }
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(HekaCore.stoneTableBlocks[index]);
	}
}
