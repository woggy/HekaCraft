package hekacraft;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import net.minecraft.item.ItemStack;

public class NEIHekaCraftConfig implements IConfigureNEI
{
	public String getName()
	{
		return HekaCore.MODNAME;
	}

	public String getVersion()
	{
		return HekaCore.VERSION;
	}

	public void loadConfig()
	{
		MageTableRecipeHandler mageTable = new MageTableRecipeHandler();
		API.registerRecipeHandler(mageTable);
		API.registerUsageHandler(mageTable);
		
		API.registerGuiOverlay(GuiMageTable.class, "mageTable");
		API.registerGuiOverlayHandler(GuiMageTable.class, new DefaultOverlayHandler(), "mageTable");
		
		StoneTableRecipeHandler stoneTable = new StoneTableRecipeHandler();
		API.registerRecipeHandler(stoneTable);
		API.registerUsageHandler(stoneTable);

		API.hideItem(new ItemStack(HekaCore.blockMageTable));
		API.hideItem(new ItemStack(HekaCore.blockSunTable));
		API.hideItem(new ItemStack(HekaCore.blockMageTableDummy));
		API.hideItem(new ItemStack(HekaCore.blockSunTableDummy));
		API.hideItem(new ItemStack(HekaCore.stelaDummy));
	}

}
