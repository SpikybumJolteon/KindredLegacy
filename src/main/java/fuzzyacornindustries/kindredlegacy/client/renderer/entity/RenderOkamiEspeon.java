package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import fuzzyacornindustries.kindredlegacy.client.model.mob.tool.ModelOkamiSunGlaive;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiEspeon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOkamiEspeon extends RenderLiving<EntityOkamiEspeon>
{
	private static final Logger LOGGER9 = LogManager.getLogger();

	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon.png");
	private static final ResourceLocation hurtTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon_hurt.png");
	private static final ResourceLocation happyTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon_happy.png");

	private static final ResourceLocation sunGlaiveTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_weapon/sun_glaive.png");

	private ModelOkamiSunGlaive modelSunGlaive;
	private ModelOkamiSunGlaive modelSunGlaiveGlow;

	public RenderOkamiEspeon(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);

		this.modelSunGlaive = new ModelOkamiSunGlaive();
		this.modelSunGlaiveGlow = new ModelOkamiSunGlaive();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOkamiEspeon tamableEntity)
	{
		switch (tamableEntity.getMainTextureType())
		{
		case 0:
			return mainTexture;
		case 1:
			return hurtTexture;
		}

		return happyTexture;
	}

	@Override
	public void doRender(EntityOkamiEspeon entity, double xCoord, double yCoord, double zCoord, float entityYaw, float partialTicks)
	{
		super.doRender(entity, xCoord, yCoord, zCoord, entityYaw, partialTicks);

		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		this.modelSunGlaive.swingProgress = this.getSwingProgress(entity, partialTicks);
		this.modelSunGlaiveGlow.swingProgress = this.getSwingProgress(entity, partialTicks);
		boolean shouldSit = entity.isRiding() && (entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
		this.modelSunGlaive.isRiding = shouldSit;
		this.modelSunGlaiveGlow.isRiding = shouldSit;
		this.modelSunGlaive.isChild = entity.isChild();
		this.modelSunGlaiveGlow.isChild = entity.isChild();

		try
		{
			float yawOffset = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
			float yawHead = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
			float yawHeadOffsetDifference = yawHead - yawOffset;

			if (shouldSit && entity.getRidingEntity() instanceof EntityLivingBase)
			{
				EntityLivingBase entitylivingbase = (EntityLivingBase)entity.getRidingEntity();
				yawOffset = this.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
				float yawRotationDifference = MathHelper.wrapDegrees(yawHeadOffsetDifference);

				if (yawRotationDifference < -85.0F)
				{
					yawRotationDifference = -85.0F;
				}

				if (yawRotationDifference >= 85.0F)
				{
					yawRotationDifference = 85.0F;
				}

				yawOffset = yawHead - yawRotationDifference;

				if (yawRotationDifference * yawRotationDifference > 2500.0F)
				{
					yawOffset += yawRotationDifference * 0.2F;
				}
			}

			float pitchRotation = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
			this.renderLivingAt(entity, xCoord, yCoord, zCoord);
			float yawHandleRotationDifference = this.handleRotationFloat(entity, partialTicks);
			this.applyRotations(entity, yawHandleRotationDifference, yawOffset, partialTicks);
			float modelSize = this.prepareScale(entity, partialTicks);
			float horzVelocity = 0.0F;
			float distanceMoved = 0.0F;

			if (!entity.isRiding())
			{
				horzVelocity = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
				distanceMoved = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);

				if (entity.isChild())
				{
					distanceMoved *= 3.0F;
				}

				if (horzVelocity > 1.0F)
				{
					horzVelocity = 1.0F;
				}
			}

			GlStateManager.enableAlpha();
			this.modelSunGlaive.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.modelSunGlaiveGlow.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.renderSunGlaive(entity, distanceMoved, horzVelocity, yawHandleRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, partialTicks);

			if (this.renderOutlines)
			{
				boolean flag1 = this.setScoreTeamColor(entity);
				GlStateManager.enableColorMaterial();
				GlStateManager.enableOutlineMode(this.getTeamColor(entity));

				if (!this.renderMarker)
				{
					this.renderModel(entity, distanceMoved, horzVelocity, yawHandleRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
				}

				if (!((EntityLivingBase)entity instanceof EntityPlayer))
				{
					this.renderLayers(entity, distanceMoved, horzVelocity, partialTicks, yawHandleRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
				}

				GlStateManager.disableOutlineMode();
				GlStateManager.disableColorMaterial();

				if (flag1)
				{
					this.unsetScoreTeamColor();
				}
			}
			else
			{
				boolean flag = this.setDoRenderBrightness(entity, partialTicks);
				this.renderModel(entity, distanceMoved, horzVelocity, yawHandleRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

				if (flag)
				{
					this.unsetBrightness();
				}

				GlStateManager.depthMask(true);

				this.renderLayers(entity, distanceMoved, horzVelocity, partialTicks, yawHandleRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
			}

			GlStateManager.disableRescaleNormal();
		}
		catch (Exception exception)
		{
			LOGGER9.error((String)"Couldn\'t render entity", (Throwable)exception);
		}

		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.enableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	protected void renderSunGlaive(EntityLivingBase entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float partialTicks)
	{
		this.bindTexture(this.sunGlaiveTexture);

		if (!entity.isInvisible())
		{
			this.modelSunGlaive.render(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}
		else if (!entity.isInvisibleToPlayer(Minecraft.getMinecraft().player))
		{
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
			GL11.glDepthMask(false);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
			this.modelSunGlaive.render(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			GL11.glPopMatrix();
			GL11.glDepthMask(true);
		}
		else
		{
			this.modelSunGlaive.setRotationAngles(distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, entity);
		}

		this.bindTexture(sunGlaiveTexture);
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.blendFunc(1, 1);

		if (entity.isInvisible())
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
		this.modelSunGlaiveGlow.render(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		i = entity.getBrightnessForRender();
		j = i % 65536;
		k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
		this.setLightmap((EntityOkamiEspeon) entity);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
	}

	@Override
	public void renderName(EntityOkamiEspeon tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Okami Espeon"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}
}