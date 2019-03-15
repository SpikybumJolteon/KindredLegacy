package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuzzyacornindustries.kindredlegacy.client.model.mob.tool.ModelOkamiJadeGlaive;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerOkamiSylveonJadeGlaive;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOkamiSylveon extends RenderLiving<EntityOkamiSylveon>
{
	private static final Logger LOGGER9 = LogManager.getLogger();

	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_sylveon.png");
	private static final ResourceLocation hurtTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_sylveon_hurt.png");
	private static final ResourceLocation happyTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_sylveon_happy.png");

	public ModelOkamiJadeGlaive modelJadeGlaive;

	public RenderOkamiSylveon(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);

		this.modelJadeGlaive = new ModelOkamiJadeGlaive();

        this.addLayer(new LayerOkamiSylveonJadeGlaive(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOkamiSylveon tamableEntity)
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
	public void renderName(EntityOkamiSylveon tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Okami Sylveon"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}
}