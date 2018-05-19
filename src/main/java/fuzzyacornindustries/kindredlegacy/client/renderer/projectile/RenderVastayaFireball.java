package fuzzyacornindustries.kindredlegacy.client.renderer.projectile;

import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityVastayaFireball;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVastayaFireball extends Render<EntityVastayaFireball>
{
    private final float scale;

    public RenderVastayaFireball(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.scale = 0.8F;
    }

    @Override
    public void doRender(EntityVastayaFireball entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(this.scale, this.scale, this.scale);
        TextureAtlasSprite textureatlassprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.FIRE_CHARGE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
       
        // Seems to determine what percentage of texture
        // fills up the square area dedicated to particle
        float texturePortionXStart = 0F;
        float texturePortionXEnd = 1F;
        float texturePortionYStart = 0F;
        float texturePortionYEnd = 1F;
        
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        buffer.pos(-0.5D, -0.25D, 0.0D).tex((double)texturePortionXStart, (double)texturePortionYEnd).normal(0.0F, 1.0F, 0.0F).endVertex();
        buffer.pos(0.5D, -0.25D, 0.0D).tex((double)texturePortionXEnd, (double)texturePortionYEnd).normal(0.0F, 1.0F, 0.0F).endVertex();
        buffer.pos(0.5D, 0.75D, 0.0D).tex((double)texturePortionXEnd, (double)texturePortionYStart).normal(0.0F, 1.0F, 0.0F).endVertex();
        buffer.pos(-0.5D, 0.75D, 0.0D).tex((double)texturePortionXStart, (double)texturePortionYStart).normal(0.0F, 1.0F, 0.0F).endVertex();
        
        tessellator.draw();

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	@Override
	protected ResourceLocation getEntityTexture(EntityVastayaFireball fireballEntity)
	{
		int textureNumber = fireballEntity.getCurrentTexture();

		return new ResourceLocation(ModInfo.MOD_ID + ":" + "textures/particles/vastaya_flame/vastaya_flame" + textureNumber + ".png");
	}
}