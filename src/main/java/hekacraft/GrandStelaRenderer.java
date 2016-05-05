package hekacraft;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GrandStelaRenderer  extends TileEntitySpecialRenderer
{
	private final GrandStelaModel model;
	private String modelTexture;
	
	public GrandStelaRenderer(String modelTexture)
	{
		this.model = new GrandStelaModel();
		this.modelTexture = modelTexture;
	}
	
	public void renderTileEntityAt(GrandStelaTileEntity te, double x, double y, double z, float scale)
	{
		
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        //This is the texture of your block. It's pathed to be the same place as your other blocks here.
        ResourceLocation textures = (new ResourceLocation("hekacraft:textures/blocks/hekacraft.block." + modelTexture + ".png")); 
        //the ':' is very important
        //binding the textures
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
         
        //A reference to your Model file. Again, very important.
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        
        //Let's make some text!
        FontRenderer fontrenderer = this.func_147498_b();
        float f1 = 0.6666667F;
        float f3 = 0.0125F * f1;
        GL11.glTranslatef(0.0F, 0.5F * f1 - 0.625F, 0.0F);
        GL11.glScalef(f3, -f3, f3);
        GL11.glDepthMask(false);
        byte b0 = 0;

        for (int j=0;j<4;j++)
        {
	        //This is such a large number because it's already been scaled.
	        GL11.glTranslatef(0.0F, 0.0F, 55.0F);	
	        GL11.glNormal3f((j==1 ? -1.0F*f3 : j==3 ? 1.0F*f3 : 0.0F), 0.0F, (j==0 ? -1.0F*f3 : j==2 ? 1.0F*f3 : 0.0F));
	        for (int i = 0; i < te.signText.length; ++i)
	        {
	            String s = te.signText[i];
	            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, i * 10 - te.signText.length * 5, b0);
	        }
	        GL11.glTranslatef(0.0F, 0.0F, -55.0F);	
	        GL11.glRotatef(90F, 0F, 1F, 0F);
        }
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
        
	}    
	
	//so we only have to handle the recast once
	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        this.renderTileEntityAt((GrandStelaTileEntity)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }
}
