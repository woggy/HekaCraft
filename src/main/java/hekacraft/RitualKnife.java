package hekacraft;

import net.minecraft.item.ItemSword;

public class RitualKnife extends ItemSword 
{
	public RitualKnife(ToolMaterial material)
    {
		super(material);
		this.setCreativeTab(HekaCore.creativeTab);
		this.setUnlocalizedName("ritualKnife");
		this.setTextureName("hekacraft:hekacraft.tool.ritualKnife");
    }
}