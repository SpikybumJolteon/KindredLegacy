package fuzzyacornindustries.kindredlegacy.client.gui;

import fuzzyacornindustries.kindredlegacy.container.ContainerVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiVespeneCondenser extends GuiContainer
{
	private static final ResourceLocation TEXTURE = 
			new ResourceLocation(ModInfo.MOD_ID + ":textures/gui/vespene_condenser_gui.png");
    private final InventoryPlayer inventoryPlayer;
    private final TileEntityVespeneCondenser tileVespeneCondenser;

	public static final int cookIconWidth = 26;
	public static final int cookIconHeight = 17;
	public static final int cookIconStartX = 176;
	public static final int cookIconStartY = 0;
	public static final int cookIconDrawX = 75;
	public static final int cookIconDrawY = 36;

    public GuiVespeneCondenser(InventoryPlayer parInventoryPlayer, TileEntityVespeneCondenser parInventoryVespeneCondenser)
    {
        super(new ContainerVespeneCondenser(parInventoryPlayer, parInventoryVespeneCondenser));
        inventoryPlayer = parInventoryPlayer;
        tileVespeneCondenser = parInventoryVespeneCondenser;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = tileVespeneCondenser.getDisplayName().getUnformattedText();
        fontRenderer.drawString(s, xSize / 2 - fontRenderer.getStringWidth(s) / 2, 6, 4210752);

        fontRenderer.drawString(inventoryPlayer.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);
    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);

        // Draw progress indicator
		int k = getProgressLevel(cookIconWidth);
		this.drawTexturedModalRect(guiLeft + cookIconDrawX, guiTop + cookIconDrawY, 
				cookIconStartX, cookIconStartY, 
				k, cookIconHeight);
    }

    private int getProgressLevel(int progressIndicatorPixelWidth)
    {
        int ticksCondensingItemSoFar = tileVespeneCondenser.getField(2); 
        int ticksPerItem = tileVespeneCondenser.getField(3);
        return ticksPerItem != 0 && ticksCondensingItemSoFar != 0 ? ticksCondensingItemSoFar * progressIndicatorPixelWidth / ticksPerItem : 0;
    }
 }