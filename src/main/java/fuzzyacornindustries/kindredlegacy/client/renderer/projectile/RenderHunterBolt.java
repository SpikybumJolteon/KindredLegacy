package fuzzyacornindustries.kindredlegacy.client.renderer.projectile;

import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityHunterBolt;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHunterBolt extends RenderArrow<EntityHunterBolt> 
{
	public static final ResourceLocation hunterbolt = new ResourceLocation(ModInfo.MOD_ID, "textures/projectiles/hunter_bolt.png");

	public RenderHunterBolt(RenderManager rm) 
	{
		super(rm);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHunterBolt entity) 
	{
		return hunterbolt;
	}
}