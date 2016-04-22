package hekacraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotStoneTable extends Slot
{	
	public static ItemStack[] pigments = { 	new ItemStack(Item.getItemFromBlock(Blocks.stained_hardened_clay),1,14),
											new ItemStack(Item.getItemFromBlock(Blocks.stained_hardened_clay),1,4),
											new ItemStack(HekaCore.itemMalachite),
											new ItemStack(Items.dye,1,4),
											new ItemStack(Items.coal),
											new ItemStack(Items.coal,1,1),
											new ItemStack(Items.egg),
											new ItemStack(Items.dye,1,15)};
	
	//								color + (amount << mask)
	public static int[] amounts = { 0 + (16 << 3),
									1 + (16 << 3),
									2 + (8 << 3),
									3 + (8 << 3),
									4 + (6 << 3),
									4 + (6 << 3),
									5 + (2 << 3),
									5 + (6 << 3)};
	
	public SlotStoneTable(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

    public boolean isItemValid(ItemStack stack)
    {
    	switch(this.getSlotIndex())
    	{
    	case 0:
    		if(isInk(stack) != -1)
    			return true;
    		else
    			return false;
    	case 1:
    		if(stack.getItem() == Item.getItemFromBlock(Blocks.stone))
    			return true;
    		else
    			return false;
    	case 2:
    		return false;
    	}
    	return (this.getSlotIndex() != 2);
    }   
    
    public void onSlotChanged()
    {
    	int index = this.getSlotIndex();
    	StoneTableTileEntity inv = (StoneTableTileEntity)inventory;
    	ItemStack items = inventory.getStackInSlot(index);
    	if(index == 0)
    	{
    		//Inkwell
    		int color = isInk(items);
			if (color != -1)
			{
				int pigmentDensity = color >> 3;
				color &= 7;
				System.out.println("Color: " + color + " Density: " + pigmentDensity);
				int currentLevel = inv.getInkLevel(color);
				if((currentLevel + items.stackSize * pigmentDensity) <= 1024)
				{
					inv.setInkLevel(color,(currentLevel + items.stackSize*pigmentDensity));
					inv.decrStackSize(index, items.stackSize);
				}
				else
				{
					int splitNum = (1024 - currentLevel) / pigmentDensity;
					inv.setInkLevel(color,(currentLevel + splitNum * pigmentDensity));
					inv.decrStackSize(index, splitNum);
				}	
			}
    	}
		inv.updateOutput();
		inv.markDirty();
		return;
    }
    
    public void onPickupFromSlot(EntityPlayer player, ItemStack items)
    {
    	if(this.getSlotIndex() == 2)
    	{
        	StoneTableTileEntity inv = (StoneTableTileEntity)inventory;
        	inv.decrStackSize(1, 1);
			for(int i=0;i<6;i++)
			{
    			inv.setInkLevel(i,inv.getInkLevel(i)-StoneTableTileEntity.inkRatios[inv.getCraft()][i]);
			}
    	}
        this.onSlotChanged();
    }
    
    public int isInk(ItemStack item)
    {
    	if(item == null)
    		return -1;
    	for (int i=0; i<SlotStoneTable.pigments.length;i++)
    	{
    		if(item.isItemEqual(SlotStoneTable.pigments[i]))
    		{
    			return amounts[i];
    		}
    	};
    	return -1;
    }
}
