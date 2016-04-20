package hekacraft;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiStoneTable extends GuiContainer 
{
	
    private static final ResourceLocation textures = new ResourceLocation("hekacraft:textures/gui/stoneTable.png");
    private GuiButton currentlySelected;
    private StoneTableTileEntity tileEntity;

    public GuiStoneTable(InventoryPlayer player, StoneTableTileEntity tileEntity)
    {
        super(new ContainerStoneTable(player, tileEntity));
        this.xSize=256;
        this.tileEntity = tileEntity;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void initGui()
    {
    	super.initGui();
    	
    	int craft = tileEntity.getCraft();
    	for(int i=0;i<12;i++)
    	{
        	//							 			id, x, y, width, height, text
    		GuiButton currentButton = new GuiButton(i, this.guiLeft+115+20*(i%4), this.guiTop+16+20*(i/4), 20, 20, null);
    		currentButton.enabled = (i != craft);
    		if (!currentButton.enabled)
    		{
    			currentlySelected = currentButton;
    		}
    		buttonList.add(currentButton);
    	}
    }
    
    protected void actionPerformed(GuiButton button)
    {
    	//Ask server to update craft field
    	this.mc.playerController.sendEnchantPacket(this.inventorySlots.windowId, button.id);
    	this.tileEntity.setCraft(button.id);
    	
    	if(currentlySelected != null)
    	{
    		currentlySelected.enabled = true;
    	}
    	currentlySelected = button; 
    	currentlySelected.enabled = false;
    }
    
    @Override
    public void drawScreen(int x, int y, float p_73863_3_)
    {
    	super.drawScreen(x, y, p_73863_3_);
    	for(int i=0;i<12;i++)
    		if(x>(this.guiLeft+115+20*(i%4)) && x < (this.guiLeft+135+20*(i%4)) && y > (this.guiTop+16+20*(i/4)) && y < (this.guiTop+36+20*(i/4)))
    		{
    			this.renderToolTip(new ItemStack(HekaCore.stoneTableBlocks[i]), x, y);
    			break;
    		}
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString(I18n.format("tile.blockStoneTable.name", new Object[0]), 64, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 48, this.ySize - 96 + 3, 4210752);
        
        for(int i=0;i<12;i++)
        	itemRender.renderItemAndEffectIntoGUI(fontRendererObj, this.mc.getTextureManager(), new ItemStack(HekaCore.stoneTableBlocks[i]), 117+20*(i%4), 18+20*(i/4));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(textures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        
        //drawTexturedModalRectangle args: x,y (on screen), x,y (in texture), width & height
        for(int i=0;i<6;i++)
        {
        	if(this.tileEntity.hasInk(i))
        	{
        		this.drawTexturedModalRect(k+23,l+38+i*6,0,166+i*2,this.tileEntity.getInkLevelScaled(i),2);
        	}
        }
    }
}
