package fuzzyacornindustries.kindredlegacy.client.gui;

import java.io.IOException;
import java.text.DecimalFormat;

import org.lwjgl.input.Keyboard;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiEspeon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.network.PokemonExplorationKitPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class GuiPokemonExplorationKit extends GuiScreen
{
	private final String screenTitle;
	private final ItemStack pokemonExplorationKitItemStack;
	//private GuiTextField textfield;

	private GuiButton buttonUmbreon;
	private GuiButton buttonSylveon;
	private GuiButton buttonEspeon;
	
	public static final int UMBREON_BUTTON_ID = 0;
	public static final int SYLVEON_BUTTON_ID = 1;
	public static final int ESPEON_BUTTON_ID = 2;
	
	public GuiPokemonExplorationKit(ItemStack stack)
	{
		pokemonExplorationKitItemStack = stack;
		screenTitle = "Pokemon Exploration Kit";


	}

	@Override
	public void initGui()
	{
		super.initGui();
		//Keyboard.enableRepeatEvents(true);

		int columnNumber[] = new int[] {(this.width / 2) - 60, this.width / 2, (this.width / 2) + 60};
		
		this.buttonList.add(this.buttonUmbreon = new GuiButton(UMBREON_BUTTON_ID, columnNumber[0] - 28, 45, 56, 20, "Umbreon"));
		this.buttonList.add(this.buttonSylveon = new GuiButton(SYLVEON_BUTTON_ID, columnNumber[1] - 28, 45, 56, 20, "Sylveon"));
		this.buttonList.add(this.buttonEspeon = new GuiButton(ESPEON_BUTTON_ID, columnNumber[2] - 28, 45, 56, 20, "Espeon"));
		
		
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();

		Keyboard.enableRepeatEvents(false);
	}
	/*
	@Override
	protected void keyTyped(char par1, int par2) throws IOException
	{
		if (textfield.textboxKeyTyped(par1, par2))
		{
			if (!textfield.getText().equals(""))
			{
				KindredLegacyMain.instance.networkHelper.sendPacketToServer(new PoketamableNamePacket(Minecraft.getMinecraft().player.getName(), textfield.getText()));
			}
		}
		else
		{
			super.keyTyped(par1, par2);
		}
	}
	 */

    @Override
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        switch (par1GuiButton.id)
        {
        case UMBREON_BUTTON_ID:
        	KindredLegacyMain.instance.networkExplorationKit.sendPacketToServer(new PokemonExplorationKitPacket(Minecraft.getMinecraft().player.getName(), new ItemStack(KindredLegacyItems.OKAMI_UMBREON_SUMMON)));
        	mc.player.closeScreen();
            break;
        case SYLVEON_BUTTON_ID:
        	KindredLegacyMain.instance.networkExplorationKit.sendPacketToServer(new PokemonExplorationKitPacket(Minecraft.getMinecraft().player.getName(), new ItemStack(KindredLegacyItems.OKAMI_SYLVEON_SUMMON)));
        	mc.player.closeScreen();
            break;
        case ESPEON_BUTTON_ID:
        	KindredLegacyMain.instance.networkExplorationKit.sendPacketToServer(new PokemonExplorationKitPacket(Minecraft.getMinecraft().player.getName(), new ItemStack(KindredLegacyItems.OKAMI_ESPEON_SUMMON)));
        	mc.player.closeScreen();
            break;
        }
    }
    
	@Override
	protected void mouseClicked(int par1, int par2, int par3) throws IOException
	{
		super.mouseClicked(par1, par2, par3);

		//this.textfield.mouseClicked(par1, par2, par3);
	}

	@Override
	public void updateScreen()
	{
		//textfield.updateCursorCounter();
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		
		int columnNumber[] = new int[] {(this.width / 2) - 60, this.width / 2, (this.width / 2) + 60};
		
		int y = 20;
		int x = this.width / 2;
		this.drawCenteredString(this.fontRenderer, this.screenTitle, x, y, 0x7778ff);

		DecimalFormat df = new DecimalFormat("#.##");

		y += 15;
		this.drawCenteredString(fontRenderer, ("A grand adventure awaits. Pick a companion to join you!"), x, y, 0xFFFFFF);
		/*
		y += 15;
		this.drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Umbreon"), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Sylveon"), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Espeon"), columnNumber[2], y, 0xd2a1ff);
*/
		y += 33;
		this.drawCenteredString(fontRenderer, ("Hit Points"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiUmbreon.defaultBaseMaxHealth)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiSylveon.defaultBaseMaxHealth)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiEspeon.defaultBaseMaxHealth)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(fontRenderer, ("Natural Armor"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiUmbreon.defaultArmor)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiSylveon.defaultArmor)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiEspeon.defaultArmor)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(fontRenderer, ("Attack Power"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiUmbreon.defaultBaseAttackPower)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiSylveon.defaultBaseAttackPower)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiEspeon.defaultBaseAttackPower)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(fontRenderer, ("Speed"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiUmbreon.defaultBaseSpeed * 100)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiSylveon.defaultBaseSpeed * 100)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, (df.format(EntityOkamiEspeon.defaultBaseSpeed * 100)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(fontRenderer, ("Health Regeneration Rate"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(fontRenderer, ("High"), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, ("Moderate"), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, ("Low"), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(fontRenderer, ("Attack Range"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(fontRenderer, ("Shortest"), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, ("Medium"), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, ("Longest"), columnNumber[2], y, 0xd2a1ff);
		
		
		
		//drawCenteredString(fontRenderer, ("-Block Suffocation Avoidance"), x, y, 0xbe802a);

		/*
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (this.width / 2), 0.0F, 50.0F);
		float var4 = 93.75F;
		GL11.glScalef(-var4, -var4, -var4);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		GL11.glPopMatrix();


		textfield.drawTextBox();*/

		super.drawScreen(par1, par2, par3);
	}
}