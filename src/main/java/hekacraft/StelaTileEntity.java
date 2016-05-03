package hekacraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class StelaTileEntity extends TileEntity
{ 	
	//TODO - make these like signs. method calls are empty stubs for now.
	
	public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        readSyncDataFromNBT(tag);
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        writeSyncDataToNBT(tag);
    }
    
    public void readSyncDataFromNBT(NBTTagCompound tag)
    {
        
    }
    
    public void writeSyncDataToNBT(NBTTagCompound tag)
    {
        
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
}
