package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderSnowSorceressBraixen;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySnowSorceressBraixen;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSnowSorceressBraixenWand implements LayerRenderer<EntitySnowSorceressBraixen>
{
	private static final ResourceLocation glowTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/snow_sorceress_braixen_glows.png");
    private final RenderSnowSorceressBraixen entityRenderer;

    public LayerSnowSorceressBraixenWand(RenderSnowSorceressBraixen entityRenderer)
    {
        this.entityRenderer = entityRenderer;
    }

    @Override
    public void doRenderLayer(EntitySnowSorceressBraixen entityLivingBase, float distanceMoved, float horzVelocity, float partialTicks, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float scale)
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
        this.entityRenderer.getMainModel().render(entityLivingBase, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, scale);
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