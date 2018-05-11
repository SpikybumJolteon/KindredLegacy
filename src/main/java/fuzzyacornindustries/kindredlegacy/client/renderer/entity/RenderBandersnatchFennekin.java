package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBandersnatchFennekin;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBandersnatchFennekin extends RenderLiving<EntityBandersnatchFennekin>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/bandersnatch_fennekin.png");
	private static final ResourceLocation temperateTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/bandersnatch_fennekin_temperate.png");

	public RenderBandersnatchFennekin(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBandersnatchFennekin entity)
	{
		switch (entity.getFennekinType())
		{
		case EntityBandersnatchFennekin.snowCoatID:
			return mainTexture;
		}
		
		return temperateTexture;
	}
}