package hekacraft;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler 
{
	public static enum Types
	{
		MAGE_TABLE,
		STONE_TABLE
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (Types.values()[ID])
		{
			case MAGE_TABLE:
				return new ContainerMageTable(player.inventory, world, x, y, z);
			case STONE_TABLE:
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if(tileEntity instanceof StoneTableTileEntity)
				{
					return new ContainerStoneTable(player.inventory, (StoneTableTileEntity) tileEntity);
				}
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (Types.values()[ID])
		{
			case MAGE_TABLE:
				return new GuiMageTable(player.inventory, world, x, y, z);
			case STONE_TABLE:
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if(tileEntity instanceof StoneTableTileEntity)
				{
					return new GuiStoneTable(player.inventory, (StoneTableTileEntity) tileEntity);
				}
			default:
				return null;
		}
	}

}
