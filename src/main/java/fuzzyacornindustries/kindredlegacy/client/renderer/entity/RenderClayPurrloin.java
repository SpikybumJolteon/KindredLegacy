package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerClayPurrloinMarkings;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayPurrloin;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderClayPurrloin extends RenderLiving<EntityClayPurrloin>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_purrloin.png");

	public RenderClayPurrloin(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerClayPurrloinMarkings(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityClayPurrloin entity)
    {
        return mainTexture;
    }
}