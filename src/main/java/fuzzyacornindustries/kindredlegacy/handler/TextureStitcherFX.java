package fuzzyacornindustries.kindredlegacy.handler;

import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Based on tutorial by The Grey Ghost in MinecraftByExample tutorial.
 * Inserts custom particle texture into the blocks+items texture sheet.
 */
public class TextureStitcherFX
{
	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event) 
	{
		ResourceLocation confuseTexture = new ResourceLocation(ModInfo.MOD_ID + ":" +"particles/confuse_particle");
		event.getMap().registerSprite(confuseTexture);
	}
}