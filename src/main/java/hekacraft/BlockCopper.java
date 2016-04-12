package hekacraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockCopper extends Block
{
	private final boolean isOre;
	
	public BlockCopper(boolean isOre)
	{
		super(Material.rock);
		this.isOre = isOre;
		this.setCreativeTab(HekaCore.creativeTab);
		if(this.isOre)
		{
			this.setBlockName("copperOre");
			this.setBlockTextureName("hekacraft:hekacraft.block.copper.ore");
		}
		else
		{
			this.setBlockName("copperBlock");
			this.setBlockTextureName("hekacraft:hekacraft.block.copper");
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
			return Item.getItemFromBlock(HekaCore.oreCopper);
		}
		else
		{
			return Item.getItemFromBlock(HekaCore.blockCopper);
		}
	}
}
