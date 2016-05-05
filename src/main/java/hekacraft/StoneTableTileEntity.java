package hekacraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class StoneTableTileEntity extends TileEntity implements IInventory
{
	private ItemStack[] contents = new ItemStack[3];
	private int craft = -1;
	private int[] inkReserves = new int[6];
	public static int[][] inkRatios = {{16,0,0,0,0,0},{0,16,0,0,0,0},{0,0,16,0,0,0},{0,0,0,16,0,0},
									   {0,0,0,0,16,0},{0,0,0,0,0,16},{0,0,12,0,2,2},{4,4,0,4,4,0},
									   {4,4,4,0,4,0}, {0,8,0,8,0,0}, {2,1,1,1,2,1}, {2,1,1,1,2,1}};
	
	@Override
	public int getSizeInventory()
	{
		return this.contents.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return this.contents[i];
	}

	@Override
    public ItemStack decrStackSize(int i, int num)
    {
        if (this.contents[i] != null)
        {
            ItemStack itemstack;

            if (this.contents[i].stackSize <= num)
            {
                itemstack = this.contents[i];
                this.contents[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.contents[i].splitStack(num);

                if (this.contents[i].stackSize == 0)
                {
                    this.contents[i] = null;
                }
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        if (this.contents[i] != null)
        {
            ItemStack itemstack = this.contents[i];
            this.contents[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
    public void setInventorySlotContents(int i, ItemStack stack)
    {
        this.contents[i] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
	public String getInventoryName() 
	{
		return "tile.blockStoneTable.name";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}    
	
	public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        readSyncDataFromNBT(tag);
        
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        this.contents = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.contents.length)
            {
                this.contents[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        writeSyncDataToNBT(tag);
        
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.contents.length; ++i)
        {
            if (this.contents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tag.setTag("Items", nbttaglist);
    }
    
    public void readSyncDataFromNBT(NBTTagCompound tag)
    {
        this.craft = tag.getInteger("Craft");
        
        for(int i=0;i<6;i++)
        {
        	this.inkReserves[i] = tag.getInteger("Ink"+i);
        }
    }
    
    public void writeSyncDataToNBT(NBTTagCompound tag)
    {
        tag.setInteger("Craft",craft);
        
        for(int i=0;i<6;i++)
        {
        	tag.setInteger("Ink"+i, inkReserves[i]);
        }
    }
	
	public Packet getDescriptionPacket()
	{
		NBTTagCompound data = new NBTTagCompound();
		this.writeSyncDataToNBT(data);
		return new S35PacketUpdateTileEntity(this.xCoord,this.yCoord,this.zCoord,1,data);
	}
	
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
    	readSyncDataFromNBT(pkt.func_148857_g());
    }

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : 
        	player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public void openInventory()
	{		
	}

	@Override
	public void closeInventory()
	{	
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) 
	{
		return false;
	}

	public int getCraft() 
	{
		return craft;
	}

	public void setCraft(int craft)
	{
		this.craft = craft;
		this.markDirty();
	}
    
    public void updateOutput()
    {
    	if(craft != -1)
    	{
    		boolean flag = true;
    		if (contents[1] == null)
    			flag = false;
    		for(int i=0;i<6;i++)
    		{
    			if(inkReserves[i] < inkRatios[craft][i])
    				flag = false;
    		}
    		if(flag)
    			if(craft == 10)
        			this.setInventorySlotContents(2, new ItemStack(HekaCore.itemStela));
    			else if (craft == 11)
        			this.setInventorySlotContents(2, new ItemStack(HekaCore.itemGrandStela));
    			else
    				this.setInventorySlotContents(2, new ItemStack(HekaCore.stoneTableBlocks[craft]));
    		else
    			this.setInventorySlotContents(2, null);
    	}
	}
	
	public int getInkLevel(int color)
	{
		return inkReserves[color];
	}

	public void setInkLevel(int color, int level)
	{
		inkReserves[color] = level;
	}
	
	public boolean hasInk(int i)
	{
		return inkReserves[i] > 0;
	}
	
	public int getInkLevelScaled(int i)
	{
		return inkReserves[i] / 16;
	}
}
