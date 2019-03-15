package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelAhriNinetalesOrb;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerBloodmoonNinetalesOrb;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerVastayaNinetalesOrb;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBloodmoonNinetales;
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
public class RenderBloodmoonNinetales extends RenderLiving<EntityBloodmoonNinetales>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/bloodmoon_ninetales.png");

	public ModelAhriNinetalesOrb modelAhriNinetalesOrb;
	public ModelAhriNinetalesOrb modelAhriNinetalesOrbGlow;
	
	public RenderBloodmoonNinetales(RenderManager renderManager, ModelBase modelBase, float shadowSize)
	{
		super(renderManager, modelBase, shadowSize);

		this.modelAhriNinetalesOrb = new ModelAhriNinetalesOrb();
		this.modelAhriNinetalesOrbGlow = new ModelAhriNinetalesOrb();
		
        this.addLayer(new LayerBloodmoonNinetalesOrb(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBloodmoonNinetales tamableEntity)
	{
		return mainTexture;
	}
}