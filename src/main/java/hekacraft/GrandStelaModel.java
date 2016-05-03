package hekacraft;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GrandStelaModel extends ModelBase
{
  //fields
    ModelRenderer Main_Block;
    ModelRenderer Step7;
    ModelRenderer Step1;
    ModelRenderer Step2;
    ModelRenderer Step3;
    ModelRenderer Step4;
    ModelRenderer Step5;
    ModelRenderer Step6;
  
  public GrandStelaModel()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Main_Block = new ModelRenderer(this, 0, 0);
      Main_Block.addBox(0F, 0F, 0F, 16, 13, 16);
      Main_Block.setRotationPoint(-8F, 11F, -8F);
      Main_Block.setTextureSize(128, 64);
      Main_Block.mirror = true;
      setRotation(Main_Block, 0F, 0F, 0F);
      Step7 = new ModelRenderer(this, 64, 45);
      Step7.addBox(0F, 0F, 0F, 2, 1, 2);
      Step7.setRotationPoint(-1F, -8F, -1F);
      Step7.setTextureSize(128, 64);
      Step7.mirror = true;
      setRotation(Step7, 0F, 0F, 0F);
      Step1 = new ModelRenderer(this, 0, 29);
      Step1.addBox(0F, 0F, 0F, 14, 13, 14);
      Step1.setRotationPoint(-7F, -2F, -7F);
      Step1.setTextureSize(128, 64);
      Step1.mirror = true;
      setRotation(Step1, 0F, 0F, 0F);
      Step2 = new ModelRenderer(this, 64, 0);
      Step2.addBox(0F, 0F, 0F, 12, 1, 12);
      Step2.setRotationPoint(-6F, -3F, -6F);
      Step2.setTextureSize(128, 64);
      Step2.mirror = true;
      setRotation(Step2, 0F, 0F, 0F);
      Step3 = new ModelRenderer(this, 64, 13);
      Step3.addBox(0F, 0F, 0F, 10, 1, 10);
      Step3.setRotationPoint(-5F, -4F, -5F);
      Step3.setTextureSize(128, 64);
      Step3.mirror = true;
      setRotation(Step3, 0F, 0F, 0F);
      Step4 = new ModelRenderer(this, 64, 24);
      Step4.addBox(0F, 0F, 0F, 8, 1, 8);
      Step4.setRotationPoint(-4F, -5F, -4F);
      Step4.setTextureSize(128, 64);
      Step4.mirror = true;
      setRotation(Step4, 0F, 0F, 0F);
      Step5 = new ModelRenderer(this, 64, 33);
      Step5.addBox(0F, 0F, 0F, 6, 1, 6);
      Step5.setRotationPoint(-3F, -6F, -3F);
      Step5.setTextureSize(128, 64);
      Step5.mirror = true;
      setRotation(Step5, 0F, 0F, 0F);
      Step6 = new ModelRenderer(this, 64, 40);
      Step6.addBox(0F, 0F, 0F, 4, 1, 4);
      Step6.setRotationPoint(-2F, -7F, -2F);
      Step6.setTextureSize(128, 64);
      Step6.mirror = true;
      setRotation(Step6, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Main_Block.render(f5);
    Step7.render(f5);
    Step1.render(f5);
    Step2.render(f5);
    Step3.render(f5);
    Step4.render(f5);
    Step5.render(f5);
    Step6.render(f5);
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
