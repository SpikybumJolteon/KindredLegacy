package fuzzyacornindustries.kindredlegacy.gui;

import java.io.IOException;
import java.text.DecimalFormat;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.gui.ChatFormatting;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemPoketamableSummon;
import fuzzyacornindustries.kindredlegacy.network.PoketamableNamePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.ItemStack;

/*
 * This class is heavily referenced from atomicstrykers'
 * Pet Bat modification.
 */
public class GuiPoketamableRename extends GuiScreen
{
	private final String screenTitle;
	private final ItemStack poketamableItemStack;
	private GuiTextField textfield;

	private double maxHealth;
	private double health;
	private float attackStrength;
	private float speed;

	private int regenLevel;

	private boolean hasFireImmunityEssence;
	private boolean hasDrowningImmunityEssence;
	private boolean hasFallImmunityEssence;
	private boolean hasBlockSuffocationAvoidanceEssence;
	private boolean hasToxinImmunityEssence;
	private boolean hasSpaceSurvivabilityEssence;

	public GuiPoketamableRename(ItemStack stack)
	{
		poketamableItemStack = stack;
		screenTitle = "Poketamable Information";

		maxHealth = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getFloat("maxhealth") != 0) ? stack.getTagCompound().getCompoundTag("pokemonmd").getFloat("maxhealth") : 0F;
		health = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("display").getString("Name") != "") ? stack.getTagCompound().getCompoundTag("pokemonmd").getFloat("health") : 0F;
		attackStrength = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getFloat("attackpower") != 0) ? stack.getTagCompound().getCompoundTag("pokemonmd").getFloat("attackpower") : 0F;
		speed = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getFloat("speed") != 0) ? stack.getTagCompound().getCompoundTag("pokemonmd").getFloat("speed") : 0F;

		regenLevel = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("regenlevel") != 0) ? stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("regenlevel") : 0;

		hasFireImmunityEssence = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("fireimmunity") == 1) ? true : false;
		hasDrowningImmunityEssence = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("drowningimmunity") == 1) ? true : false;
		hasFallImmunityEssence = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("fallimmunity") == 1) ? true : false;
		hasBlockSuffocationAvoidanceEssence = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("blocksuffocationavoidance") == 1) ? true : false;
		hasToxinImmunityEssence = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("toxinimmunity") == 1) ? true : false;
		hasSpaceSurvivabilityEssence = (stack.getTagCompound() != null && stack.getTagCompound().getCompoundTag("pokemonmd").getInteger("spacesurvivability") == 1) ? true : false;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		Keyboard.enableRepeatEvents(true);

		textfield = new GuiTextField(0, fontRenderer, this.width / 2 - 75, 60, 150, 20);
		textfield.setTextColor(-1);
		textfield.setMaxStringLength(30);
		textfield.setFocused(true);
		textfield.setText(ItemPoketamableSummon.getPoketamableNameFromItemStack(poketamableItemStack));
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();

		Keyboard.enableRepeatEvents(false);
	}

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

	@Override
	protected void mouseClicked(int par1, int par2, int par3) throws IOException
	{
		super.mouseClicked(par1, par2, par3);

		this.textfield.mouseClicked(par1, par2, par3);
	}

	@Override
	public void updateScreen()
	{
		textfield.updateCursorCounter();
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();

		int x = this.width / 2;
		this.drawCenteredString(this.fontRenderer, this.screenTitle, x, 40, 0x7778ff);

		DecimalFormat df = new DecimalFormat("#.##");

		int y = 100;
		drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Health "
				+ ChatFormatting.RESET + df.format(health) + " / " + df.format(maxHealth)), x, y, 0xFFFFFF);
		y += 12;
		drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Attack Power "
				+ ChatFormatting.RESET + df.format(attackStrength)), x, y, 0xFFFFFF);
		y += 12;
		drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Speed "
				+ ChatFormatting.RESET + df.format(speed * 100F)), x, y, 0xFFFFFF);
		y += 12;
		drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Regen Level "
				+ ChatFormatting.RESET + df.format(regenLevel)), x, y, 0xFFFFFF);
		y += 12;

		if(this.hasFireImmunityEssence || this.hasDrowningImmunityEssence || this.hasFallImmunityEssence || this.hasBlockSuffocationAvoidanceEssence || this.hasToxinImmunityEssence) 
		{	
			y += 12;
			drawCenteredString(fontRenderer, (ChatFormatting.BOLD + "Essences Possessed"), x, y, 0xFFFFFF);

			if(this.hasBlockSuffocationAvoidanceEssence)
			{
				y += 12;
				drawCenteredString(fontRenderer, ("-Block Suffocation Avoidance"), x, y, 0xbe802a);
			}

			if(this.hasDrowningImmunityEssence)
			{
				y += 12;
				drawCenteredString(fontRenderer, ("-Drowning Immunity"), x, y, 0x449bff);
			}

			if(this.hasFallImmunityEssence)
			{
				y += 12;
				drawCenteredString(fontRenderer, ("-Fall Damage Immunity"), x, y, 0xe324ff);
			}

			if(this.hasFireImmunityEssence)
			{
				y += 12;
				drawCenteredString(fontRenderer, ("-Fire Immunity"), x, y, 0xffbb00);
			}

			if(this.hasToxinImmunityEssence)
			{
				y += 12;
				drawCenteredString(fontRenderer, ("-Poison & Wither Immunity"), x, y, 0x59cd01);
			}

			if(this.hasSpaceSurvivabilityEssence)
			{
				y += 12;
				drawCenteredString(fontRenderer, ("-Galacticraft Space Survivability"), x, y, 0xc9f6ff);
			}
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) (this.width / 2), 0.0F, 50.0F);
		float var4 = 93.75F;
		GL11.glScalef(-var4, -var4, -var4);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		GL11.glPopMatrix();
		textfield.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}
}