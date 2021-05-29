package fuzzyacornindustries.kindredlegacy.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;

import fuzzyacornindustries.kindredlegacy.common.inventory.container.VerdantPurifierContainer;
import fuzzyacornindustries.kindredlegacy.common.tileentity.VerdantPurifierTileEntity;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class VerdantPurifierGui extends ContainerScreen<VerdantPurifierContainer>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Names.MOD_ID + ":textures/gui/verdant_purifier_gui.png");
	private final VerdantPurifierTileEntity tileEntity;

	public static final int cookIconWidth = 32;
	public static final int cookIconHeight = 32;
	public static final int cookIconStartX = 176;
	public static final int cookIconStartY = 0;
	public static final int cookIconDrawX = 72;
	public static final int cookIconDrawY = 26;

	public VerdantPurifierGui(VerdantPurifierContainer container, PlayerInventory inv, ITextComponent displayString) 
	{
		super(container, inv, displayString);
		this.tileEntity = (VerdantPurifierTileEntity) container.te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) 
	{
		String s = tileEntity.getDisplayName().getUnformattedComponentText();
		font.drawString(s, xSize / 2 - font.getStringWidth(s) / 2, 6, 4210752);

		font.drawString(playerInventory.getDisplayName().getUnformattedComponentText(), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindGuiTexture();
		this.blit(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);

		// Draw progress indicator
		int k = tileEntity.getProgressionScale();

//		Log.info("Get GUI Progression Scale " + tileEntity.getProgressionScale());

		this.blit(guiLeft + cookIconDrawX, guiTop + cookIconDrawY, 
				cookIconStartX, cookIconStartY, 
				k, cookIconHeight);
	}

	void bindGuiTexture() 
	{
		ResourceLocation guiTexture = getGuiTexture();
		if (guiTexture != null) {
			minecraft.getTextureManager().bindTexture(guiTexture);
			RenderSystem.enableTexture();
		}
	}

	protected ResourceLocation getGuiTexture()
	{
		return TEXTURE;
	}
}