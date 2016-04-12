package hekacraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockMalachite extends Block
{
	private final boolean isOre;
	
	public BlockMalachite(boolean isOre)
	{
		super(Material.rock);
		this.isOre = isOre;
		this.setCreativeTab(HekaCore.creativeTab);
		if(this.isOre)
		{
			this.setBlockName("malachiteOre");
			this.setBlockTextureName("hekacraft:hekacraft.block.malachite.ore");
		}
		else
		{
			this.setBlockName("malachiteBlock");
			this.setBlockTextureName("hekacraft:hekacraft.block.malachite");
		}
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		if(this.isOre)
		{
			return HekaCore.itemMalachite;
		}
		else
		{
			return Item.getItemFromBlock(HekaCore.blockMalachite);
		}
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return this.isOre ? 4 : 1;
	}
}
