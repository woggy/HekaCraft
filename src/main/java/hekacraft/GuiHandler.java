package hekacraft;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler 
{
	public static enum Types
	{
		MAGE_TABLE
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (Types.values()[ID])
		{
			case MAGE_TABLE:
				return new ContainerMageTable(player.inventory, world, x, y, z);
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
			default:
				return null;
		}
	}

}
