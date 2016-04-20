package hekacraft;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SunTableModel extends ModelBase
{
  //fields
    ModelRenderer Tabletop;
    ModelRenderer InnerTable;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer LongBar1;
    ModelRenderer LongBar2;
    ModelRenderer ShortBar1;
    ModelRenderer ShortBar2;
  
  public SunTableModel()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Tabletop = new ModelRenderer(this, 0, 0);
      Tabletop.addBox(0F, 0F, 0F, 32, 5, 16);
      Tabletop.setRotationPoint(-8F, 8F, -8F);
      Tabletop.setTextureSize(128, 64);
      Tabletop.mirror = true;
      setRotation(Tabletop, 0F, 0F, 0F);
      InnerTable = new ModelRenderer(this, 0, 21);
      InnerTable.addBox(0F, 0F, 0F, 30, 1, 14);
      InnerTable.setRotationPoint(-7F, 13F, -7F);
      InnerTable.setTextureSize(128, 64);
      InnerTable.mirror = true;
      setRotation(InnerTable, 0F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 0, 36);
      Leg1.addBox(0F, 0F, 0F, 3, 10, 3);
      Leg1.setRotationPoint(20F, 14F, -4F);
      Leg1.setTextureSize(128, 64);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 1.570796F, 0F);
      Leg2 = new ModelRenderer(this, 0, 36);
      Leg2.addBox(0F, 0F, 0F, 3, 10, 3);
      Leg2.setRotationPoint(20F, 14F, 4F);
      Leg2.setTextureSize(128, 64);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Leg3 = new ModelRenderer(this, 0, 36);
      Leg3.addBox(0F, 0F, 0F, 3, 10, 3);
      Leg3.setRotationPoint(-4F, 14F, 4F);
      Leg3.setTextureSize(128, 64);
      Leg3.mirror = true;
      setRotation(Leg3, 0F, 4.712389F, 0F);
      Leg4 = new ModelRenderer(this, 0, 36);
      Leg4.addBox(0F, 0F, 0F, 3, 10, 3);
      Leg4.setRotationPoint(-4F, 14F, -4F);
      Leg4.setTextureSize(128, 64);
      Leg4.mirror = true;
      setRotation(Leg4, 0F, 3.141593F, 0F);
      LongBar1 = new ModelRenderer(this, 12, 36);
      LongBar1.addBox(0F, 0F, 0F, 24, 1, 1);
      LongBar1.setRotationPoint(-4F, 17F, -6F);
      LongBar1.setTextureSize(128, 64);
      LongBar1.mirror = true;
      setRotation(LongBar1, 0F, 0F, 0F);
      LongBar2 = new ModelRenderer(this, 12, 36);
      LongBar2.addBox(0F, 0F, 0F, 24, 1, 1);
      LongBar2.setRotationPoint(-4F, 17F, 5F);
      LongBar2.setTextureSize(128, 64);
      LongBar2.mirror = true;
      setRotation(LongBar2, 0F, 0F, 0F);
      ShortBar1 = new ModelRenderer(this, 12, 38);
      ShortBar1.addBox(0F, 0F, 0F, 1, 1, 8);
      ShortBar1.setRotationPoint(21F, 17F, -4F);
      ShortBar1.setTextureSize(128, 64);
      ShortBar1.mirror = true;
      setRotation(ShortBar1, 0F, 0F, 0F);
      ShortBar2 = new ModelRenderer(this, 12, 38);
      ShortBar2.addBox(0F, 0F, 0F, 1, 1, 8);
      ShortBar2.setRotationPoint(-6F, 17F, -4F);
      ShortBar2.setTextureSize(128, 64);
      ShortBar2.mirror = true;
      setRotation(ShortBar2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Tabletop.render(f5);
    InnerTable.render(f5);
    Leg1.render(f5);
    Leg2.render(f5);
    Leg3.render(f5);
    Leg4.render(f5);
    LongBar1.render(f5);
    LongBar2.render(f5);
    ShortBar1.render(f5);
    ShortBar2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
