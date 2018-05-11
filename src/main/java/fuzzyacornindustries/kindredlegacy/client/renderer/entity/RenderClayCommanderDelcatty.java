package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layers.LayerClayCommanderDelcattyMarkings;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayCommanderDelcatty;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderClayCommanderDelcatty extends RenderLiving<EntityClayCommanderDelcatty>
{
	private static final ResourceLocation mainTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_commander_delcatty.png");

	public RenderClayCommanderDelcatty(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
        this.addLayer(new LayerClayCommanderDelcattyMarkings(this));
    }
	
	@Override
    protected ResourceLocation getEntityTexture(EntityClayCommanderDelcatty entity)
    {
        return mainTexture;
    }
}