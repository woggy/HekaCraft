package hekacraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerStoneTable extends Container
{
    private StoneTableTileEntity tileStoneTable;

    public ContainerStoneTable(InventoryPlayer inventory, StoneTableTileEntity tileEntity)
    {
        this.tileStoneTable = tileEntity;
        this.addSlotToContainer(new SlotStoneTable(tileEntity, 0, 30, 18));
        this.addSlotToContainer(new SlotStoneTable(tileEntity, 1, 66, 18));
        this.addSlotToContainer(new SlotStoneTable(tileEntity, 2, 222, 35));
        
        //Player inventory
        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 48 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 48 + i * 18, 142));
        }
        
        this.tileStoneTable.updateOutput();
    }
    
    //Despite the name, this is usable for any "player clicked a button" event. Fires server-side.
    public boolean enchantItem(EntityPlayer player, int button)
    {
    	this.tileStoneTable.setCraft(button);
    	this.tileStoneTable.updateOutput();
    	return true;
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return this.tileStoneTable.isUseableByPlayer(p_75145_1_);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 0 && p_82846_2_ != 1)
            {
            	if(((Slot) this.inventorySlots.get(0)).isItemValid(itemstack))
            	{
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
            	}
            	else if(((Slot) this.inventorySlots.get(1)).isItemValid(itemstack))
            	{
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
            	}
            	else if (p_82846_2_ >= 3 && p_82846_2_ < 30)
	            {
	                if (!this.mergeItemStack(itemstack1, 30, 39, false))
	                {
	                    return null;
	                }
	            }
	            else if (p_82846_2_ >= 30 && p_82846_2_ < 39)
	            {
	                if (!this.mergeItemStack(itemstack1, 3, 30, false))
	                {
	                    return null;
	                }
	            }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }
        return itemstack;
    }
}
