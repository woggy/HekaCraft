package hekacraft;

import codechicken.nei.NEIServerUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class MageTableRecipeHandler extends codechicken.nei.recipe.ShapedRecipeHandler
{
	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiMageTable.class;
	}
	
	@Override
	public String getOverlayIdentifier()
	{
		return "mageTable";
	}
	
//Technically not necessary, for now. Uncomment to use something other than the Crafting Table texture.
//    @Override
//    public String getGuiTexture()
//    {
//        return "hekacraft:textures/gui/mageTable.png";
//    }
	
    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("tile.blockMageTable.name");
    }
	
    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("crafting") && getClass() == MageTableRecipeHandler.class) {
            for (IRecipe irecipe : MageTableCraftingManager.getInstance().getRecipeList()) {
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
        for (IRecipe irecipe : MageTableCraftingManager.getInstance().getRecipeList()) {
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
    	System.out.println("loadUsageRecipes");
        for (IRecipe irecipe : MageTableCraftingManager.getInstance().getRecipeList()) {
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
