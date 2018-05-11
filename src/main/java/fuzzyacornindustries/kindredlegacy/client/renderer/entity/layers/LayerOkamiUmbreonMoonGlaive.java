package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerOkamiUmbreonMoonGlaive implements LayerRenderer<EntityOkamiUmbreon>
{
	private static final ResourceLocation glowTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_weapon/moon_glaive_glows.png");
    private final RenderOkamiUmbreon entityRenderer;

    public LayerOkamiUmbreonMoonGlaive(RenderOkamiUmbreon entityRendererIn)
    {
        this.entityRenderer = entityRendererIn;
    }

    @Override
    public void doRenderLayer(EntityOkamiUmbreon entityLivingBase, float distanceMoved, float horzVelocity, float partialTicks, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
    {
        this.entityRenderer.bindTexture(glowTexture);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(1, 1);

        if (entityLivingBase.isInvisible())
        {
            GlStateManager.depthMask(false);
        }
        else
        {
            GlStateManager.depthMask(true);
        }

        int i = 61680;
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.entityRenderer.modelMoonGlaive.render(entityLivingBase, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
        i = entityLivingBase.getBrightnessForRender();
        j = i % 65536;
        k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
        this.entityRenderer.setLightmap(entityLivingBase);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}