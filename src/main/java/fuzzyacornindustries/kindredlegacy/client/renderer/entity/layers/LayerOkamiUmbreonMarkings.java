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
public class LayerOkamiUmbreonMarkings implements LayerRenderer<EntityOkamiUmbreon>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_umbreon_glows.png");
	private static final ResourceLocation hurtTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_umbreon_hurt_glows.png");
	private static final ResourceLocation happyTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_umbreon_happy_glows.png");
	
    private final RenderOkamiUmbreon entityRenderer;

    public LayerOkamiUmbreonMarkings(RenderOkamiUmbreon entityRenderer)
    {
        this.entityRenderer = entityRenderer;
    }

    @Override
    public void doRenderLayer(EntityOkamiUmbreon entityLivingBase, float distanceMoved, float horzVelocity, float partialTicks, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float scale)
    {
		switch (entityLivingBase.getMainTextureType())
		{
		case 0:
	        this.entityRenderer.bindTexture(mainTexture);
	        break;
		case 1:
	        this.entityRenderer.bindTexture(hurtTexture);
	        break;
		case 2:
	        this.entityRenderer.bindTexture(happyTexture);
	        break;
		}
		
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