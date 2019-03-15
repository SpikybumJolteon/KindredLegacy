package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelAhriNinetalesOrb;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerVastayaNinetalesOrb;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityVastayaNinetales;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVastayaNinetales extends RenderLiving<EntityVastayaNinetales>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/vastaya_ninetales.png");
	private static final ResourceLocation hurtTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/vastaya_ninetales_hurt.png");
	private static final ResourceLocation happyTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/vastaya_ninetales_happy.png");

	public ModelAhriNinetalesOrb modelAhriNinetalesOrb;
	public ModelAhriNinetalesOrb modelAhriNinetalesOrbGlow;

	public RenderVastayaNinetales(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);

		this.modelAhriNinetalesOrb = new ModelAhriNinetalesOrb();
		this.modelAhriNinetalesOrbGlow = new ModelAhriNinetalesOrb();
		
        this.addLayer(new LayerVastayaNinetalesOrb(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityVastayaNinetales tamableEntity)
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
	public void renderName(EntityVastayaNinetales tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Vastaya Ninetales"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}
}