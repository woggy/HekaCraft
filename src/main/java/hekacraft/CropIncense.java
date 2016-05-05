package hekacraft;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item; 
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class CropIncense extends Block implements IShearable
{
	private IIcon[] textures;
	//Higher number = slower growing.
	private final int growthRate = 10;
	
	public CropIncense()
	{
		super(Material.plants);
		this.setBlockName("plantIncense");
		this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
		this.setBlockTextureName("hekacraft:hekacraft.plant.incense");
		this.setTickRandomly(true);
	}

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x,
            int y, int z) {
        return null;
    }
    
	public int getRenderType()
	{
		return 1;	//Use the "crossed squares" renderer.
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta > 4)
        {
            meta = 4;
        }

        return this.textures[meta];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.textures = new IIcon[5];

        for (int i = 0; i < this.textures.length; ++i)
        {
            this.textures[i] = register.registerIcon(this.getTextureName() + "." + i);
        }
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
    	int stage = world.getBlockMetadata(x, y, z);
    	
    	//Fully grown
    	if(stage == 4)
    	{
    		return;
    	}
    	
    	//Don't grow at night or in the shade.
    	if(world.getBlockLightValue(x, y+1, z) < 9)
    	{
    		return;
    	}
    	
    	//Slow down growth so it doesn't shoot up like a weed.
    	if(random.nextInt(growthRate) != 0)
    	{
    		return;
    	}
    	
    	world.setBlockMetadataWithNotify(x, y, z, stage+1, 2);
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
    	if(!canBlockStay(world, x, y, z))
    	{
    		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
    		world.setBlockToAir(x, y, z);
    	}	
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
    	Block soil = world.getBlock(x, y-1, z);
    	if(world.getFullBlockLightValue(x, y, z) >=8)
    		if(world.canBlockSeeTheSky(x, y, z))
    			if(soil != null)
    				if(soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, (SeedsIncense)HekaCore.seedsIncense))
    					return true;
    	return false;
    }
	
	public Item getItemDropped(int meta, Random foo, int bar)
	{
		return null;
	}
    
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) 
    { 
    	return true;
    }
    
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
    	if(item != null)
    	{
    		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
    		if(world.getBlockMetadata(x, y, z) != 4)
    		{
    			ret.add(new ItemStack(HekaCore.seedsIncense));
    		}
    		else 
    		{
    			if(item.getItem() instanceof GoldPesheskef)
    			{
    				//Golden Pesheskef
    				Random rand = new Random();
    				if (rand.nextInt(5) == 0)
    					ret.add(new ItemStack(HekaCore.seedsIncense));
    			}
    			if(!(item.getItem() instanceof Pesheskef))
	    		{
	    			//Vanilla shears, or another mod's that inherits
	        		((World) world).setBlockToAir(x,y,z);
	    		}
    			//Regardless of what you sheared it with, get some tasty!
        		ret.add(new ItemStack(HekaCore.fruitIncense));
    		}
    		return ret;
    	}
    	//Default 
    	return null;
    }
}
