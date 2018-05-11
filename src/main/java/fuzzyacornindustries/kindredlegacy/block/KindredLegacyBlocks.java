package fuzzyacornindustries.kindredlegacy.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class KindredLegacyBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	// MATERIALS TAB
	public static final Block PACKED_AURUM_DUST = new BlockBase("packed_aurum_dust", Material.SAND);

	// FARM BLOCKS
	public static final Block ORANIAN_BERRY_PLANT = new BlockOranianBerry("oranian_berry_plant");
	public static final Block PECHITA_BERRY_PLANT = new BlockOranianBerry("pechita_berry_plant");
}