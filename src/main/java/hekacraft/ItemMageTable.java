package hekacraft;

public class ItemMageTable extends ItemSunTable
{
    public ItemMageTable()
    {
    	super();
    	this.setUnlocalizedName("itemMageTable");
        this.setTextureName("hekacraft:hekacraft.block.mageTable.item");
        this.realBlock = HekaCore.blockMageTable;
        this.dummyBlock = HekaCore.blockMageTableDummy;
    }
}