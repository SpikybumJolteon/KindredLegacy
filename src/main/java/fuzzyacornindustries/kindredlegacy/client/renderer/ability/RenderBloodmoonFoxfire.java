package fuzzyacornindustries.kindredlegacy.client.renderer.ability;

import fuzzyacornindustries.kindredlegacy.entity.ability.EntityBloodmoonFoxfire;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBloodmoonFoxfire extends RenderLiving<EntityBloodmoonFoxfire>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/abilities/bloodmoon_foxfire.png");

	public RenderBloodmoonFoxfire(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityBloodmoonFoxfire entity)
    {
        return mainTexture;
    }
}