package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFirecrackerLitten;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFirecrackerLitten extends RenderLiving<EntityFirecrackerLitten>
{
	private static final Logger LOGGER9 = LogManager.getLogger();

	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/firecracker_litten.png");
	private static final ResourceLocation hurtTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/firecracker_litten_hurt.png");
	private static final ResourceLocation happyTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/firecracker_litten_happy.png");

	public RenderFirecrackerLitten(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFirecrackerLitten tamableEntity)
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
	public void renderName(EntityFirecrackerLitten tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Firecracker Litten"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}
}