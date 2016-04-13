package hekacraft;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class TerrainEventHandler
{
	private final int attemptsPerChunk = 10;
	private final int clusterSize = 4;
	private int x,y,z,x1,y1,z1;
	
	@SubscribeEvent
	public void decorateIncense(DecorateBiomeEvent.Post event)
	{		
		//Check towards the center of the chunk. Once per chunk to save computational time and bail if this isn't desert at all.
		if (event.world.getWorldChunkManager().getBiomeGenAt(event.chunkX+8, event.chunkZ+8) instanceof BiomeGenDesert)
		{
			for(int i=0; i<attemptsPerChunk;i++)
			{
				//I have no idea why we're adding eight here. but if you don't, the world crashes.
				//Probably something to do with chunk boundaries, but vanilla does the +8, so yeah.
				x = event.chunkX + event.rand.nextInt(16)+8;
				z = event.chunkZ + event.rand.nextInt(16)+8;
				y = event.rand.nextInt(event.world.getHeightValue(x, z)*2);	
				for(int j=0;j<clusterSize;j++)
				{					
					x1 = x + event.rand.nextInt(8) - event.rand.nextInt(8);
					y1 = y + event.rand.nextInt(4) - event.rand.nextInt(4);
					z1 = z + event.rand.nextInt(8) - event.rand.nextInt(8);

					if(event.world.isAirBlock(x1, y1, z1))
						if(HekaCore.plantIncense.canBlockStay(event.world,x1,y1,z1))
							event.world.setBlock(x1, y1, z1, HekaCore.plantIncense, 4, 2);
				}
			}
		}
	}
}