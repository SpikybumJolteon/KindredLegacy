package fuzzyacornindustries.kindredlegacy.handler;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.client.gui.GuiVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.client.gui.GuiVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.container.ContainerAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.container.ContainerVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.container.ContainerVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == ModInfo.GUI_VESPENE_CONDENSER)
			return new ContainerVespeneCondenser(player.inventory, (TileEntityVespeneCondenser)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == ModInfo.GUI_ALCHEMENISIZER)
			return new ContainerAlchemenisizer(player.inventory, (TileEntityAlchemenisizer)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == ModInfo.GUI_VERDANT_PURIFIER)
			return new ContainerVerdantPurifier(player.inventory, (TileEntityVerdantPurifier)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ModInfo.GUI_VESPENE_CONDENSER)
			return new GuiVespeneCondenser(player.inventory, (TileEntityVespeneCondenser)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == ModInfo.GUI_ALCHEMENISIZER)
			return new GuiAlchemenisizer(player.inventory, (TileEntityAlchemenisizer)world.getTileEntity(new BlockPos(x,y,z)));
		else if(ID == ModInfo.GUI_VERDANT_PURIFIER)
			return new GuiVerdantPurifier(player.inventory, (TileEntityVerdantPurifier)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
}