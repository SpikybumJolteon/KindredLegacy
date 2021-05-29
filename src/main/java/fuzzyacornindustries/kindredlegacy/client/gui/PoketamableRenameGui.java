package fuzzyacornindustries.kindredlegacy.client.gui;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.gui.ChatFormatting;

import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.common.network.PoketamableNameMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

public class PoketamableRenameGui extends Screen
{
	private final String screenTitle;
	private final ItemStack poketamableItemStack;
	private TextFieldWidget textfield;

	private double currentMaxHealth;
	private double health;
	private float attackStrength;
	private float speed;

	private int regenLevel;

	private boolean hasFireImmunityEssence;
	private boolean hasDrowningImmunityEssence;
	private boolean hasFallImmunityEssence;
	private boolean hasBlockSuffocationAvoidanceEssence;
	private boolean hasToxinImmunityEssence;

	public PoketamableRenameGui(ItemStack stack) 
	{
		super(new StringTextComponent("Poketamable Information"));
		poketamableItemStack = stack;
		screenTitle = "Poketamable Information";

		currentMaxHealth = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getFloat("maxhealth") != 0) ? stack.getTag().getCompound("pokemonmd").getFloat("maxhealth") : 0F;
		health = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getFloat("health") != 0) ? stack.getOrCreateChildTag("pokemonmd").getFloat("health") : 0F;
		attackStrength = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getFloat("attackpower") != 0) ? stack.getOrCreateChildTag("pokemonmd").getFloat("attackpower") : 0F;
		speed = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getFloat("speed") != 0) ? stack.getOrCreateChildTag("pokemonmd").getFloat("speed") : 0F;

		regenLevel = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getInt("regenlevel") != 0) ? stack.getOrCreateChildTag("pokemonmd").getInt("regenlevel") : 0;

		hasFireImmunityEssence = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getInt("fireimmunity") == 1) ? true : false;
		hasDrowningImmunityEssence = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getInt("drowningimmunity") == 1) ? true : false;
		hasFallImmunityEssence = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getInt("fallimmunity") == 1) ? true : false;
		hasBlockSuffocationAvoidanceEssence = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getInt("blocksuffocationavoidance") == 1) ? true : false;
		hasToxinImmunityEssence = (stack.getTag() != null && stack.getOrCreateChildTag("pokemonmd").getInt("toxinimmunity") == 1) ? true : false;
	}

	@Override
	public void init()
	{
		super.init();
		this.minecraft.keyboardListener.enableRepeatEvents(true);

		this.textfield = new TextFieldWidget(this.font, this.width / 2 - 75, 60, 150, 20, "poketamableName");
		this.textfield.setTextColor(-1);
		this.textfield.setMaxStringLength(30);
		this.textfield.setFocused2(true);
		this.children.add(this.textfield);
		this.setFocusedDefault(this.textfield);
		textfield.setText(poketamableItemStack.getDisplayName().getUnformattedComponentText());
	}

	@Override
    public void removed() 
	{
        super.removed();
        this.minecraft.keyboardListener.enableRepeatEvents(false);
    }

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers)
	{
		//this.textfield.func_212955_f() && 
		//System.out.println( "Key Press In Text Field: " + Boolean.toString(textfield.keyPressed(keyCode, scanCode, modifiers)));
/*
		if (textfield.keyPressed(keyCode, scanCode, modifiers))
		{
			if (!textfield.getText().equals(""))
			{
				KindredLegacyMain.network.sendToServer(new PoketamableNameMessage(Minecraft.getInstance().player.getName().getString(), textfield.getText()));
				
				//KindredLegacyMain.network.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), new PoketamableNameMessage(textfield.getText()));
			}
		}*/

		if (!textfield.keyPressed(keyCode, scanCode, modifiers)) 
		{
            // escape was pressed, close screen
            if (keyCode == 256) 
            {
                onClose();
                return true;
            }
            // enter was pressed
            if (keyCode == 257) 
            {
                if (!textfield.getText().equals("")) 
                {
                	//KindredLegacy.network.sendToServer(new PoketamableNameMessage(Minecraft.getInstance().player.getName().getString(), textfield.getText().toString()));
                	NetworkHandler.sendToServer(new PoketamableNameMessage(Minecraft.getInstance().player.getName().getString(), textfield.getText().toString()));
                }
                
                onClose();
                return true;
            }
        }
		
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if (super.mouseClicked(mouseX, mouseY, mouseButton)) 
		{
            return true;
        }
		
        return this.textfield.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void tick()
	{
		textfield.tick();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground();

		int x = this.width / 2;
		this.drawCenteredString(this.font, this.screenTitle, x, 40, 0x7778ff);
		
		DecimalFormat df = new DecimalFormat("#.##");

		int y = 100;
		drawCenteredString(font, (ChatFormatting.BOLD + "Health "
				+ ChatFormatting.RESET + df.format(health) + " / " + df.format(currentMaxHealth)), x, y, 0xFFFFFF);
		y += 12;
		drawCenteredString(font, (ChatFormatting.BOLD + "Attack Power "
				+ ChatFormatting.RESET + df.format(attackStrength)), x, y, 0xFFFFFF);
		y += 12;
		drawCenteredString(font, (ChatFormatting.BOLD + "Speed "
				+ ChatFormatting.RESET + df.format(speed * 100F)), x, y, 0xFFFFFF);
		y += 12;
		drawCenteredString(font, (ChatFormatting.BOLD + "Regen Level "
				+ ChatFormatting.RESET + df.format(regenLevel)), x, y, 0xFFFFFF);
		y += 12;

		if(this.hasFireImmunityEssence || this.hasDrowningImmunityEssence || this.hasFallImmunityEssence || this.hasBlockSuffocationAvoidanceEssence || this.hasToxinImmunityEssence) 
		{	
			y += 12;
			drawCenteredString(font, (ChatFormatting.BOLD + "Essences Possessed"), x, y, 0xFFFFFF);

			y += 12;
			
			if(this.hasBlockSuffocationAvoidanceEssence)
			{
				drawCenteredString(font, ("-Block Suffocation Avoidance"), x, y, 0xbe802a);
			}
			else
			{
				drawCenteredString(font, ("-Block Suffocation Avoidance"), x, y, 0x545454);
			}

			y += 12;
			
			if(this.hasDrowningImmunityEssence)
			{
				drawCenteredString(font, ("-Drowning Immunity"), x, y, 0x449bff);
			}
			else
			{
				drawCenteredString(font, ("-Drowning Immunity"), x, y, 0x545454);
			}

			y += 12;
			
			if(this.hasFallImmunityEssence)
			{
				drawCenteredString(font, ("-Fall Damage Immunity"), x, y, 0xe324ff);
			}
			else
			{
				drawCenteredString(font, ("-Fall Damage Immunity"), x, y, 0x545454);
			}

			y += 12;
			
			if(this.hasFireImmunityEssence)
			{
				drawCenteredString(font, ("-Fire Immunity"), x, y, 0xffbb00);
			}
			else
			{
				drawCenteredString(font, ("-Fire Immunity"), x, y, 0x545454);
			}

			y += 12;
			
			if(this.hasToxinImmunityEssence)
			{
				drawCenteredString(font, ("-Poison & Wither Immunity"), x, y, 0x59cd01);
			}
			else
			{
				drawCenteredString(font, ("-Poison & Wither Immunity"), x, y, 0x545454);
			}
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) (this.width / 2), 0.0F, 50.0F);
		float var4 = 93.75F;
		GL11.glScalef(-var4, -var4, -var4);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		GL11.glPopMatrix();
		this.textfield.render(mouseX, mouseY, partialTicks);//render(mouseX, mouseY, partialTicks);
		super.render(mouseX, mouseY, partialTicks);
	}
}