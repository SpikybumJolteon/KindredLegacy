package fuzzyacornindustries.kindredlegacy.client.gui;

import java.text.DecimalFormat;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiEspeonEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiSylveonEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiUmbreonEntity;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.common.network.PokemonExplorationKitMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

public class PokemonExplorationKitGui extends Screen
{
	private final String screenTitle;
	
	public static final int UMBREON_BUTTON_ID = 0;
	public static final int SYLVEON_BUTTON_ID = 1;
	public static final int ESPEON_BUTTON_ID = 2;

	public PokemonExplorationKitGui()
	{
		super(new StringTextComponent("Pokemon Exploration Kit"));
		screenTitle = "Pokemon Exploration Kit";
	}

	@Override
	public void init()
	{
		super.init();
		//Keyboard.enableRepeatEvents(true);

		int columnNumber[] = new int[] {(this.width / 2) - 60, this.width / 2, (this.width / 2) + 60};

		//this.buttonSign = this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, I18n.format("book.signButton"), (p_214201_1_) -> {
		this.addButton(new Button(columnNumber[0] - 28, 45, 56, 20, "Umbreon", (iPressible) -> {
			NetworkHandler.sendToServer(new PokemonExplorationKitMessage(Minecraft.getInstance().player.getName().getString(), new ItemStack(ModItems.OKAMI_UMBREON_SUMMON.get())));
			this.minecraft.displayGuiScreen((Screen)null);
		}));
		this.addButton(new Button(columnNumber[1] - 28, 45, 56, 20, "Sylveon", (iPressible) -> {
			NetworkHandler.sendToServer(new PokemonExplorationKitMessage(Minecraft.getInstance().player.getName().getString(), new ItemStack(ModItems.OKAMI_SYLVEON_SUMMON.get())));
			this.minecraft.displayGuiScreen((Screen)null);
		}));
		this.addButton(new Button(columnNumber[2] - 28, 45, 56, 20, "Espeon", (iPressible) -> {
			NetworkHandler.sendToServer(new PokemonExplorationKitMessage(Minecraft.getInstance().player.getName().getString(), new ItemStack(ModItems.OKAMI_ESPEON_SUMMON.get())));
			this.minecraft.displayGuiScreen((Screen)null);
		}));
	}

	@Override
	public void removed() 
	{
		this.minecraft.keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground();

		int columnNumber[] = new int[] {(this.width / 2) - 60, this.width / 2, (this.width / 2) + 60};

		int y = 20;
		int x = this.width / 2;
		this.drawCenteredString(this.font, this.screenTitle, x, y, 0x7778ff);

		DecimalFormat df = new DecimalFormat("#.##");

		y += 15;
		this.drawCenteredString(font, ("A grand adventure awaits. Pick a companion to join you!"), x, y, 0xFFFFFF);
		/*
		y += 15;
		this.drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Umbreon"), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Sylveon"), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Espeon"), columnNumber[2], y, 0xd2a1ff);
		 */
		y += 33;
		this.drawCenteredString(font, ("Hit Points"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(font, (df.format(OkamiUmbreonEntity.defaultBaseMaxHealth)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(font, (df.format(OkamiSylveonEntity.defaultBaseMaxHealth)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(font, (df.format(OkamiEspeonEntity.defaultBaseMaxHealth)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(font, ("Natural Armor"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(font, (df.format(OkamiUmbreonEntity.defaultArmor)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(font, (df.format(OkamiSylveonEntity.defaultArmor)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(font, (df.format(OkamiEspeonEntity.defaultArmor)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(font, ("Attack Power"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(font, (df.format(OkamiUmbreonEntity.defaultBaseAttackPower)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(font, (df.format(OkamiSylveonEntity.defaultBaseAttackPower)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(font, (df.format(OkamiEspeonEntity.defaultBaseAttackPower)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(font, ("Speed"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(font, (df.format(OkamiUmbreonEntity.defaultBaseSpeed * 100)), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(font, (df.format(OkamiSylveonEntity.defaultBaseSpeed * 100)), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(font, (df.format(OkamiEspeonEntity.defaultBaseSpeed * 100)), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(font, ("Health Regeneration Rate"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(font, ("High"), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(font, ("Moderate"), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(font, ("Low"), columnNumber[2], y, 0xd2a1ff);

		y += 15;
		this.drawCenteredString(font, ("Attack Range"), columnNumber[1], y, 0xFFFFFF);
		y += 12;
		this.drawCenteredString(font, ("Shortest"), columnNumber[0], y, 0xfffc00);
		this.drawCenteredString(font, ("Medium"), columnNumber[1], y, 0xffa1cd);
		this.drawCenteredString(font, ("Longest"), columnNumber[2], y, 0xd2a1ff);

		super.render(mouseX, mouseY, partialTicks);
	}
}