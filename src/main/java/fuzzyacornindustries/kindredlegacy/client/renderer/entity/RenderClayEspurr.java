package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerClayEspurrMarkings;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayEspurr;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderClayEspurr extends RenderLiving<EntityClayEspurr>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_espurr.png");

	public RenderClayEspurr(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerClayEspurrMarkings(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityClayEspurr entity)
    {
        return mainTexture;
    }
}