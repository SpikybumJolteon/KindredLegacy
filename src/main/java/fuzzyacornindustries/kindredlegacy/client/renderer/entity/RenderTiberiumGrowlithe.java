package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerTiberiumGrowlitheCrystals;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityTiberiumGrowlithe;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTiberiumGrowlithe extends RenderLiving<EntityTiberiumGrowlithe>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tiberium_growlithe.png");

	public RenderTiberiumGrowlithe(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerTiberiumGrowlitheCrystals(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityTiberiumGrowlithe entity)
    {
        return mainTexture;
    }
}