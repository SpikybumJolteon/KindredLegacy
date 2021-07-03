package fuzzyacornindustries.kindredlegacy.client;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.Rectangle2d;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.InputMappings;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.particles.IParticleData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.EmptyModelData;

/**
 * Miscellaneous client-side utilities
 * 
 * Most referenced from PneumaticCraft
 */
public class ClientUtils 
{
    /**
     * Emit particles from just above the given blockpos, which is generally a machine or similar.
     * Only call this clientside.
     *
     * @param world the world
     * @param pos the block pos
     * @param particle the particle type
     */
    public static void emitParticles(World world, BlockPos pos, IParticleData particle) 
    {
        float xOff = world.rand.nextFloat() * 0.6F + 0.2F;
        float zOff = world.rand.nextFloat() * 0.6F + 0.2F;
        getClientWorld().addParticle(particle,
                pos.getX() + xOff, pos.getY() + 1.2, pos.getZ() + zOff,
                0, 0, 0);
    }

    public static boolean isKeyDown(int keyCode) 
    {
        return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), keyCode);
    }

    public static void openContainerGui(ContainerType<? extends Container> type, ITextComponent displayString) 
    {
        // This windowId = -1 hack is ugly but appears to work...
        ScreenManager.openScreen(type, Minecraft.getInstance(), -1, displayString);
    }

    /**
     * For use where we can't reference Minecraft directly, e.g. packet handling code.
     * @return the client world
     */
    public static World getClientWorld() 
    {
        return Minecraft.getInstance().world;
    }

    public static PlayerEntity getClientPlayer() 
    {
        return Minecraft.getInstance().player;
    }

    public static boolean hasShiftDown() 
    {
        return Screen.hasShiftDown();
    }

    /**
     * Get a TE client-side.  Convenience method for packet handling code, primarily.
     * @return a tile entity or null
     */
    public static TileEntity getClientTE(BlockPos pos) 
    {
        return Minecraft.getInstance().world.getTileEntity(pos);
    }

    /**
     * See AWT Rectangle's intersects() method
     *
     * @param rect
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    public static boolean intersects(Rectangle2d rect, double x, double y, double w, double h) 
    {
        if (rect.getWidth() <= 0 || rect.getHeight() <= 0 || w <= 0 || h <= 0) 
        {
            return false;
        }
        
        double x0 = rect.getX();
        double y0 = rect.getY();
        return (x + w > x0 &&
                y + h > y0 &&
                x < x0 + rect.getWidth() &&
                y < y0 + rect.getHeight());
    }

    public static int getStringWidth(String line) {
        return Minecraft.getInstance().getRenderManager().getFontRenderer().getStringWidth(line);
    }

    public static boolean isGuiOpen() 
    {
        return Minecraft.getInstance().currentScreen != null;
    }

    public static float[] getTextureUV(BlockState state, Direction face) 
    {
        if (state == null) return null;
        IBakedModel model = Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(state);
        List<BakedQuad> quads = model.getQuads(state, face, Minecraft.getInstance().world.rand, EmptyModelData.INSTANCE);
        if (!quads.isEmpty()) {
            TextureAtlasSprite sprite = quads.get(0).func_187508_a();
            return new float[] { sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV() };
        } else {
            return null;
        }
    }

    public static void spawnEntityClientside(Entity e) 
    {
        ((ClientWorld) getClientWorld()).addEntity(e.getEntityId(), e);
    }
}