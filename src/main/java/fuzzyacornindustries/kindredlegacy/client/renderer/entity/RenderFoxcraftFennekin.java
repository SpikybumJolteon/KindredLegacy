package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFoxcraftFennekin;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFoxcraftFennekin extends RenderLiving<EntityFoxcraftFennekin>
{
	private static final Logger LOGGER9 = LogManager.getLogger();

	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/foxcraft_fennekin.png");
	private static final ResourceLocation hurtTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/foxcraft_fennekin_hurt.png");
	private static final ResourceLocation happyTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/foxcraft_fennekin_happy.png");

	public RenderFoxcraftFennekin(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFoxcraftFennekin tamableEntity)
	{
		switch (tamableEntity.getMainTextureType())
		{
		case 0:
			return mainTexture;
		case 1:
			return hurtTexture;
		}

		return happyTexture;
	}

	@Override
	public void renderName(EntityFoxcraftFennekin tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Foxcraft Fennekin"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}
}