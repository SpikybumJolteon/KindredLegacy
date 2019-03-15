package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerOkamiUmbreonMoonGlaive implements LayerRenderer<EntityOkamiUmbreon>
{
	private static final ResourceLocation glaiveTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_weapon/moon_glaive.png");
    private final RenderOkamiUmbreon entityRenderer;

    public LayerOkamiUmbreonMoonGlaive(RenderOkamiUmbreon entityRenderer)
    {
        this.entityRenderer = entityRenderer;
    }

    @Override
    public void doRenderLayer(EntityOkamiUmbreon entityLivingBase, float distanceMoved, float horzVelocity, float partialTicks, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
    {
        this.entityRenderer.bindTexture(glaiveTexture);
        this.entityRenderer.modelMoonGlaive.render(entityLivingBase, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
        
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}