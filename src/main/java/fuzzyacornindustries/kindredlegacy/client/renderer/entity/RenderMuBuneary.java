package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerMuBunearyMarkings;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityMuBuneary;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMuBuneary extends RenderLiving<EntityMuBuneary>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/mu_buneary.png");

	public RenderMuBuneary(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerMuBunearyMarkings(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityMuBuneary entity)
    {
        return mainTexture;
    }
}