package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerClayLuxioMarkings;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayLuxio;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderClayLuxio extends RenderLiving<EntityClayLuxio>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_luxio.png");

	public RenderClayLuxio(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerClayLuxioMarkings(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityClayLuxio entity)
    {
        return mainTexture;
    }
}