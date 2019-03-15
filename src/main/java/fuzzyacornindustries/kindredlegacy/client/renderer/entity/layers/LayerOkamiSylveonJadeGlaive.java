package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerOkamiSylveonJadeGlaive implements LayerRenderer<EntityOkamiSylveon>
{
	private static final ResourceLocation glaiveTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_weapon/jade_glaive.png");
    private final RenderOkamiSylveon entityRenderer;

    public LayerOkamiSylveonJadeGlaive(RenderOkamiSylveon entityRenderer)
    {
        this.entityRenderer = entityRenderer;
    }

    @Override
    public void doRenderLayer(EntityOkamiSylveon entityLivingBase, float distanceMoved, float horzVelocity, float partialTicks, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
    {
        this.entityRenderer.bindTexture(glaiveTexture);
        this.entityRenderer.modelJadeGlaive.render(entityLivingBase, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
        
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}