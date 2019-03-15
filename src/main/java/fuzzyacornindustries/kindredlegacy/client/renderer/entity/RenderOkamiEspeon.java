package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuzzyacornindustries.kindredlegacy.client.model.mob.tool.ModelOkamiSunGlaive;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerOkamiEspeonSunGlaive;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiEspeon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOkamiEspeon extends RenderLiving<EntityOkamiEspeon>
{
	private static final Logger LOGGER9 = LogManager.getLogger();

	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon.png");
	private static final ResourceLocation hurtTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon_hurt.png");
	private static final ResourceLocation happyTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon_happy.png");

	public ModelOkamiSunGlaive modelSunGlaive;
	public ModelOkamiSunGlaive modelSunGlaiveGlow;

	public RenderOkamiEspeon(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);

		this.modelSunGlaive = new ModelOkamiSunGlaive();
		this.modelSunGlaiveGlow = new ModelOkamiSunGlaive();

        this.addLayer(new LayerOkamiEspeonSunGlaive(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOkamiEspeon tamableEntity)
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
	public void renderName(EntityOkamiEspeon tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Okami Espeon"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}
}