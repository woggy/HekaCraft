package hekacraft;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import net.minecraft.item.ItemStack;

public class NEIHekaCraftConfig implements IConfigureNEI
{
	public String getName()
	{
		return "HekaCraft";
	}

	public String getVersion()
	{
		return "0.1";
	}

	public void loadConfig()
	{
		MageTableRecipeHandler mageTable = new MageTableRecipeHandler();
		API.registerRecipeHandler(mageTable);
		API.registerUsageHandler(mageTable);
		
		API.registerGuiOverlay(GuiMageTable.class, "mageTable");
		API.registerGuiOverlayHandler(GuiMageTable.class, new DefaultOverlayHandler(), "mageTable");
		
		API.hideItem(new ItemStack(HekaCore.blockMageTableDummy));
	}

}
