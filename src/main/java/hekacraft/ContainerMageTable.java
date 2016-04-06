package hekacraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public class ContainerMageTable extends ContainerWorkbench
{
	private int x;
	private int y;
	private int z;
	private World world;
	
	public ContainerMageTable(InventoryPlayer inventory, World world, int x, int y, int z)
	{
		super(inventory, world, x, y, z);
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return player.getDistanceSq((double)this.x + 0.5D, (double)this.y + 0.5D, (double)this.z + 0.5D) <= 64.0D;
    }
	
	@Override
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
        this.craftResult.setInventorySlotContents(0, MageTableCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
    }
}
