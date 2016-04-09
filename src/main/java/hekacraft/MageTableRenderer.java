package hekacraft;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class MageTableRenderer  extends TileEntitySpecialRenderer
{
	private final MageTableModel model;
	
	public MageTableRenderer()
	{
		this.model = new MageTableModel();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        //This is the texture of your block. It's pathed to be the same place as your other blocks here.
        ResourceLocation textures = (new ResourceLocation("hekacraft:textures/blocks/hekacraft.block.mageTable.png")); 
        //the ':' is very important
        //binding the textures
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        //Make sure it's pointed the right direction
        int dir = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
        GL11.glPushMatrix();
        //GL11.glTranslatef(0.5F, 0, 0.5F);
        //This line actually rotates the renderer.
        GL11.glRotatef((dir-1) * (90F), 0F, 1F, 0F);
        //GL11.glTranslatef(-0.5F, 0, -0.5F);
         
        //A reference to your Model file. Again, very important.
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        //Tell it to stop rendering for all the PushMatrix's
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}
}
