package hekacraft;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class StoneTableRecipeHandler extends codechicken.nei.recipe.ShapedRecipeHandler
{
	private static List<IRecipe> recipes = new ArrayList<IRecipe>();
	
	//item slot offsets for the GUI.
	private static int[][] xOffsets = {{43},{43,43},{43,25,61},{25,61,25,61},{25,43,61,25,61},{25,43,61,25,43,61}};
	private static int[][] yOffsets = {{6},{6,42},{6,42,42},{6,6,42,42},{6,6,6,42,42},{6,6,6,42,42,42}};
	
	static
	{
		for (int i=0;i<SlotStoneTable.pigments.length;i++)
		{
			int pigmentDensity = SlotStoneTable.amounts[i] >> 3;
			int color = SlotStoneTable.amounts[i] & 7;
			recipes.add(new ShapedOreRecipe(new ItemStack(HekaCore.pigment,pigmentDensity,color), "   ", " p ", "   ", 'p', SlotStoneTable.pigments[i])); 
		}
		
		for (int i=0;i<StoneTableTileEntity.inkRatios.length;i++)
		{
			int[] pigments = StoneTableTileEntity.inkRatios[i];
			int numPigments = 0;
			for(int j=0;j<pigments.length;j++)
				if(pigments[j] > 0)
					numPigments++;
			Object[] ingredients = new Object[5+numPigments*2];
			switch(numPigments)
			{
				case 1:
					ingredients[0] = " a ";
					ingredients[2] = "   ";
					break;
				case 2:
					ingredients[0] = " a ";
					ingredients[2] = " b ";
					break;
				case 3:
					ingredients[0] = " a ";
					ingredients[2] = "b c";
					break;
				case 4:
					ingredients[0] = "a b";
					ingredients[2] = "c d";
					break;
				case 5:
					ingredients[0] = "abc";
					ingredients[2] = "d e";
					break;
				case 6:
					ingredients[0] = "abc";
					ingredients[2] = "def";
					break;
				default:
					ingredients[0] = "   ";
					ingredients[2] = "   ";
			}
			ingredients[1] = " s ";
			ingredients[3] = new Character('s');
			ingredients[4] = "stone";
			int k = 0;
			for(int j=0;j<pigments.length;j++)
				if(pigments[j] > 0)
				{
					ingredients[5+2*k] = new Character((char)('a'+k));
					ingredients[5+2*k+1] = new ItemStack(HekaCore.pigment,pigments[j],j);
					k++;
				}
			
			if(i==10)
				recipes.add(new ShapedOreRecipe(new ItemStack(HekaCore.itemStela), ingredients));
			else if(i==11)
				recipes.add(new ShapedOreRecipe(new ItemStack(HekaCore.itemGrandStela), ingredients));
			else
				recipes.add(new ShapedOreRecipe(new ItemStack(HekaCore.stoneTableBlocks[i]), ingredients));
		}
	}
	
	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiContainer.class;
	}
	
    @Override
    public String getGuiTexture()
    {
        return "hekacraft:textures/gui/stoneTableNEI.png";
    }
	
    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("tile.blockStoneTable.name");
    }
    
    public boolean hasOverlay(GuiContainer gui, Container container, int recipe)
    {
    	return false;
    }
	
    public void loadTransferRects()
    {
    	transferRects.add(new RecipeTransferRect(new Rectangle(84,23,24,18), "stoneTable"));
    }
    
    //recipe is used to differentiate between upper and lower displays on the page.
    public void drawExtras(int recipe)
    {
    	PositionedStack output = arecipes.get(recipe).getResult();
    	if(output == null)
    		return;
    	
    	int blockIndex = -1;
    	for(int i=0;i<HekaCore.stoneTableBlocks.length;i++)
    	{
    		if(output.contains(HekaCore.itemStela))
    		{
    			blockIndex=10;
    			break;
    		}
    		if(output.contains(HekaCore.itemGrandStela))
    		{
    			blockIndex=11;
    			break;
    		}
    		if(output.contains(Item.getItemFromBlock(HekaCore.stoneTableBlocks[i])))
    		{
    			blockIndex=i;
    			break;
    		}
    	}
    	if(blockIndex == -1)
    		return;

		int numPigments = 0;
		int[] pigments = new int[6];
		for(int i=0;i<StoneTableTileEntity.inkRatios[blockIndex].length;i++)
			if(StoneTableTileEntity.inkRatios[blockIndex][i] > 0)
			{
				pigments[numPigments] = StoneTableTileEntity.inkRatios[blockIndex][i];
				numPigments++;
			}
    	FontRenderer fontRendererObj = GuiContainerManager.getFontRenderer(new ItemStack(HekaCore.pigment));
		for(int i=0;i<numPigments;i++)
		{
			int x = xOffsets[numPigments-1][i];
			int y = yOffsets[numPigments-1][i];
			String number = Integer.toString(pigments[i]);

	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glDisable(GL11.GL_BLEND);
	        fontRendererObj.drawStringWithShadow(number, x + 19 - 2 - fontRendererObj.getStringWidth(number), y + 6 + 3, 16777215);
	        GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
    }
    
    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("stoneTable") && getClass() == StoneTableRecipeHandler.class) {
            for (IRecipe irecipe : StoneTableRecipeHandler.recipes) {
                CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes)
                    recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
                else if (irecipe instanceof ShapedOreRecipe)
                    recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);

                if (recipe == null)
                    continue;

                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (IRecipe irecipe : StoneTableRecipeHandler.recipes) {
            if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result)) {
                CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes)
                    recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
                else if (irecipe instanceof ShapedOreRecipe)
                    recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);

                if (recipe == null)
                    continue;

                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (IRecipe irecipe : StoneTableRecipeHandler.recipes) {
            CachedShapedRecipe recipe = null;
            if (irecipe instanceof ShapedRecipes)
                recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
            else if (irecipe instanceof ShapedOreRecipe)
                recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);

            if (recipe == null || !recipe.contains(recipe.ingredients, ingredient.getItem()))
                continue;

            recipe.computeVisuals();
            if (recipe.contains(recipe.ingredients, ingredient)) {
                recipe.setIngredientPermutation(recipe.ingredients, ingredient);
                arecipes.add(recipe);
            }
        }
    }
}
