package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityForcewindEelektrik;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderForcewindEelektrik extends RenderLiving<EntityForcewindEelektrik>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/forcewind_eelektrik.png");

	public RenderForcewindEelektrik(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityForcewindEelektrik entity)
    {
        return mainTexture;
    }
}