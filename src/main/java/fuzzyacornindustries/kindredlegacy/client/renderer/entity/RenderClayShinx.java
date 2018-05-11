package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerClayShinxMarkings;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayShinx;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderClayShinx extends RenderLiving<EntityClayShinx>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_shinx.png");

	public RenderClayShinx(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerClayShinxMarkings(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityClayShinx entity)
    {
        return mainTexture;
    }
}