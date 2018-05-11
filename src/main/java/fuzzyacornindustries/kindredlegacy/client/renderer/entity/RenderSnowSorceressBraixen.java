package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerSnowSorceressBraixenWand;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySnowSorceressBraixen;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSnowSorceressBraixen extends RenderLiving<EntitySnowSorceressBraixen>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/snow_sorceress_braixen.png");

	public RenderSnowSorceressBraixen(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerSnowSorceressBraixenWand(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntitySnowSorceressBraixen entity)
    {
        return mainTexture;
    }
}