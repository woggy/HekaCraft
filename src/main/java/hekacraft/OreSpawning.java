package hekacraft;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class OreSpawning implements IWorldGenerator
{
	private final int veinsPerChunk = 10;
	private final int numberOfBlocks = 10;
	private final int oreRatio = 2;	//How many copper per malachite
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		//only in the overworld.
		if(world.provider.dimensionId == 0)
		{
			int oreX;
			int oreY;
			int oreZ;
			
			for(int i=0; i<this.veinsPerChunk;i++)
			{
				oreX = chunkX*16 + random.nextInt(16);
				oreY = 40 + random.nextInt(24);
				oreZ = chunkZ*16 + random.nextInt(16);
				
				//This logic copied wholesale, barring names, from net.minecraft.world.gen.feature.WorldGenMinable
		        float f = random.nextFloat() * (float)Math.PI;
		        double d0 = (double)((float)(oreX + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
		        double d1 = (double)((float)(oreX + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
		        double d2 = (double)((float)(oreZ + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
		        double d3 = (double)((float)(oreZ + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
		        double d4 = (double)(oreY + random.nextInt(3) - 2);
		        double d5 = (double)(oreY + random.nextInt(3) - 2);

		        for (int l = 0; l <= this.numberOfBlocks; ++l)
		        {
		            double d6 = d0 + (d1 - d0) * (double)l / (double)this.numberOfBlocks;
		            double d7 = d4 + (d5 - d4) * (double)l / (double)this.numberOfBlocks;
		            double d8 = d2 + (d3 - d2) * (double)l / (double)this.numberOfBlocks;
		            double d9 = random.nextDouble() * (double)this.numberOfBlocks / 16.0D;
		            double d10 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
		            double d11 = (double)(MathHelper.sin((float)l * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
		            int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
		            int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
		            int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
		            int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
		            int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
		            int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);

		            for (int k2 = i1; k2 <= l1; ++k2)
		            {
		                double d12 = ((double)k2 + 0.5D - d6) / (d10 / 2.0D);

		                if (d12 * d12 < 1.0D)
		                {
		                    for (int l2 = j1; l2 <= i2; ++l2)
		                    {
		                        double d13 = ((double)l2 + 0.5D - d7) / (d11 / 2.0D);

		                        if (d12 * d12 + d13 * d13 < 1.0D)
		                        {
		                            for (int i3 = k1; i3 <= j2; ++i3)
		                            {
		                                double d14 = ((double)i3 + 0.5D - d8) / (d10 / 2.0D);

		                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.getBlock(k2, l2, i3).isReplaceableOreGen(world, k2, l2, i3, Blocks.stone))
		                                {
		                                	//this is the only part that changed.
		                                    if (random.nextInt(oreRatio+1) == 0)
		                                    {
		                                    	world.setBlock(k2, l2, i3, HekaCore.oreMalachite, 0, 2);
		                                    }
		                                    else
		                                    {
		                                    	world.setBlock(k2, l2, i3, HekaCore.oreCopper, 0, 2);
		                                    }
		                                }
		                            }
		                        }
		                    }
		                }
		            }
		        }
		        //end copy
			}
		}
	}

}
