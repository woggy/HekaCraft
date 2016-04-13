package hekacraft;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class SeedsIncense extends ItemSeeds implements IPlantable
{	
	public SeedsIncense(Block crops, Block soil)
	{
		super(crops,soil);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("seedsIncense");
		this.setCreativeTab(HekaCore.creativeTab);
		this.setTextureName("hekacraft:hekacraft.plant.incense.seeds");
	}
	
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        return EnumPlantType.Desert;
    }
}
