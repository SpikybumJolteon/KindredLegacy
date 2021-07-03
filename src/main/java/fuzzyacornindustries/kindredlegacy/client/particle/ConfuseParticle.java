package fuzzyacornindustries.kindredlegacy.client.particle;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.systems.RenderSystem;

import fuzzyacornindustries.kindredlegacy.common.particle.ConfuseParticleData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ConfuseParticle extends SpriteTexturedParticle
{
	//private final IAnimatedSprite sprite;
	private final double startPosX;
	private final double startPosY;
	private final double startPosZ;	

	private ConfuseParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float scale, IAnimatedSprite sprite) 
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.posX = xCoordIn;
		this.posY = yCoordIn;
		this.posZ = zCoordIn;
		this.startPosX = this.posX;
		this.startPosY = this.posY;
		this.startPosZ = this.posZ;
		//this.sprite = sprite;
		//this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
		this.maxAge = (int) (48.0D / (Math.random() * 0.8D + 0.2D));

		final float ALPHA_VALUE = 0.99F;
		this.particleAlpha = ALPHA_VALUE;

		motionX = xSpeedIn;
		motionY = ySpeedIn;
		motionZ = zSpeedIn;
	}

	/**
	 * call once per tick to update the Particle position, calculate collisions, remove when max lifetime is reached, etc
	 */
	@Override
	public void tick()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.age++ >= this.maxAge || !world.isAirBlock(new BlockPos(posX, posY, posZ)) || onGround) 
		{
			this.setExpired();
		} 
		else 
		{
			float f = (float)this.age / (float)this.maxAge;
			float f1 = -f + f * f * 2.0F;
			float f2 = 1.0F - f1;
			this.posX = this.startPosX + this.motionX * (double)f2;
			this.posY = this.startPosY + this.motionY * (double)f2 + (double)(1.0F - f);
			this.posZ = this.startPosZ + this.motionZ * (double)f2;
		}
	}

	@Override
	public IParticleRenderType getRenderType() 
	{
		return CONFUSE_PARTICLE_RENDER;
	}

	@Override
	public void move(double x, double y, double z) 
	{
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}

	@Override
	public float getScale(float p_217561_1_) 
	{
		float f = ((float)this.age + p_217561_1_) / (float)this.maxAge;
		f = 1.0F - f;
		f = f * f;
		f = 1.0F - f;
		return this.particleScale * f;
	}

	@Override
	public int getBrightnessForRender(float partialTick) 
	{
		int i = super.getBrightnessForRender(partialTick);
		float f = (float)this.age / (float)this.maxAge;
		f = f * f;
		f = f * f;
		int j = i & 255;
		int k = i >> 16 & 255;
		k = k + (int)(f * 15.0F * 16.0F);
		if (k > 240) {
			k = 240;
		}

		return j | k << 16;
	}

	public static class Factory implements IParticleFactory<ConfuseParticleData> 
	{
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite spriteSet) 
		{
			this.spriteSet = spriteSet;
		}

		@Nullable
		@Override
		public Particle makeParticle(ConfuseParticleData typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) 
		{
			ConfuseParticle confusionParticle = new ConfuseParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 1F, spriteSet);
			confusionParticle.selectSpriteRandomly(this.spriteSet);
			return confusionParticle;
		}
	}

	@SuppressWarnings("deprecation")
	private static final IParticleRenderType CONFUSE_PARTICLE_RENDER = new IParticleRenderType() 
	{
		@Override
		public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) 
		{
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			RenderSystem.alphaFunc(GL11.GL_GREATER, 0.003921569F);
			RenderSystem.disableLighting();

			textureManager.bindTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE);
			textureManager.getTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE).setBlurMipmap(true, false);
			bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
		}

		@Override
		public void finishRender(Tessellator tessellator) 
		{
			tessellator.draw();

			Minecraft.getInstance().textureManager.getTexture(AtlasTexture.LOCATION_PARTICLES_TEXTURE).restoreLastBlurMipmap();
			RenderSystem.alphaFunc(GL11.GL_GREATER, 0.1F);
			RenderSystem.disableBlend();
			RenderSystem.depthMask(true);
		}

		@Override
		public String toString() 
		{
			return "kindredlegacy:confuse_particle";
		}
	};
}