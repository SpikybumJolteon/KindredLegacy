package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerCracklingNincadaGlows;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityCracklingNincada;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCracklingNincada extends RenderLiving<EntityCracklingNincada>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/crackling_nincada.png");

	public RenderCracklingNincada(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerCracklingNincadaGlows(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityCracklingNincada entity)
    {
        return mainTexture;
    }
}