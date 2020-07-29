package fuzzyacornindustries.kindredlegacy.client.model.particle;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ConfuseParticle extends SpriteTexturedParticle
{
	public ConfuseParticle(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
	{
		super(world, x, y, z, velocityX, velocityY, velocityZ);

		//this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
		this.maxAge = (int) (16.0D / (Math.random() * 0.8D + 0.2D));

		final float ALPHA_VALUE = 0.99F;
		this.particleAlpha = ALPHA_VALUE;

		motionX = velocityX;
		motionY = velocityY;
		motionZ = velocityZ;
	}
	
	/**
	 * call once per tick to update the Particle position, calculate collisions, remove when max lifetime is reached, etc
	 */
	@Override
	public void tick()
	{
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		move(motionX, motionY, motionZ);

		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
		BlockState iblockstate = this.world.getBlockState(blockpos);
		Material material = iblockstate.getMaterial();

		if (material.isSolid())
		{
			double d1 = (double)(MathHelper.floor(this.posY) + 1);

			if (this.posY < d1)
			{
				this.setExpired();
			}
		}

		if (this.maxAge-- <= 0) 
		{
			this.setExpired();
		}
	}

	public IParticleRenderType getRenderType() 
	{
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
	public void move(double x, double y, double z) 
	{
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}

	public float getScale(float p_217561_1_) 
	{
		float f = ((float)this.age + p_217561_1_) / (float)this.maxAge;
		f = 1.0F - f;
		f = f * f;
		f = 1.0F - f;
		return this.particleScale * f;
	}

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

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> 
	{
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite spriteSet) 
		{
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) 
		{
			ConfuseParticle confusionParticle = new ConfuseParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			confusionParticle.selectSpriteRandomly(this.spriteSet);
			return confusionParticle;
		}
	}
}