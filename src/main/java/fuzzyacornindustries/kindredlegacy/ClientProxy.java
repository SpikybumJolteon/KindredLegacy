package fuzzyacornindustries.kindredlegacy;

import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelAhriNinetales;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelArmoredLuxray;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelArmoredShinx;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelBandersnatchFennekin;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelClayCommanderDelcatty;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelClayEspurr;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelClayPurrloin;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelClayShinx;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelClaySkitty;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelDemonVulpix;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelFeywoodAbsol;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelFirecrackerLitten;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelForcewindEelektrik;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelFoxcraftFennekin;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelInfestedDeerling;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelMuBuneary;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelOkamiEspeon;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelSnowSorceressBraixen;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelSubstituteDoll;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelSwordieMienshao;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelVoorstMightyena;
import fuzzyacornindustries.kindredlegacy.client.model.mob.ModelZerglingNincada;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderArmoredLuxray;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderArmoredShinx;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderBandersnatchFennekin;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderClayCommanderDelcatty;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderClayEspurr;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderClayPurrloin;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderClayShinx;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderClaySkitty;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderCracklingNincada;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderDemonVulpix;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderFeywoodAbsol;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderFirecrackerLitten;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderForcewindEelektrik;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderFoxcraftFennekin;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderInfestedDeerling;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderMuBuneary;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderOkamiEspeon;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderRaptorZerglingNincada;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderSnowSorceressBraixen;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderSubstituteDoll;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderSwordieMienshao;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderVastayaNinetales;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderVoorstMightyena;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RenderZerglingNincada;
import fuzzyacornindustries.kindredlegacy.client.renderer.projectile.RenderFireworkMissile;
import fuzzyacornindustries.kindredlegacy.client.renderer.projectile.RenderHunterBolt;
import fuzzyacornindustries.kindredlegacy.client.renderer.projectile.RenderVastayaFireball;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityArmoredLuxray;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityArmoredShinx;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBandersnatchFennekin;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayCommanderDelcatty;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayEspurr;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayPurrloin;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayShinx;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClaySkitty;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityCracklingNincada;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityDemonVulpix;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityForcewindEelektrik;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityInfestedDeerling;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityMuBuneary;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityRaptorZerglingNincada;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySnowSorceressBraixen;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySwordieMienshao;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityVoorstMightyena;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityZerglingNincada;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFeywoodAbsol;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFirecrackerLitten;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFoxcraftFennekin;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiEspeon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityVastayaNinetales;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityFireworkMissile;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityHunterBolt;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityVastayaFireball;
import fuzzyacornindustries.kindredlegacy.gui.GuiPoketamableRename;
import fuzzyacornindustries.kindredlegacy.handler.TextureStitcherFX;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ClientProxy extends CommonProxy
{
	private Timer mcTimer;

	@Override
	public void preInit() 
	{
		super.preInit();

		/*
		 * Mobs
		 */
		RenderingRegistry.registerEntityRenderingHandler(EntityArmoredLuxray.class, new IRenderFactory<EntityArmoredLuxray>()
		{
			@Override
			public Render<? super EntityArmoredLuxray> createRenderFor(RenderManager manager)
			{
				return new RenderArmoredLuxray(manager, new ModelArmoredLuxray(), 0.4F);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityArmoredShinx.class, new IRenderFactory<EntityArmoredShinx>()
		{
			@Override
			public Render<? super EntityArmoredShinx> createRenderFor(RenderManager manager)
			{
				return new RenderArmoredShinx(manager, new ModelArmoredShinx(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityBandersnatchFennekin.class, new IRenderFactory<EntityBandersnatchFennekin>()
		{
			@Override
			public Render<? super EntityBandersnatchFennekin> createRenderFor(RenderManager manager)
			{
				return new RenderBandersnatchFennekin(manager, new ModelBandersnatchFennekin(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityClayCommanderDelcatty.class, new IRenderFactory<EntityClayCommanderDelcatty>()
		{
			@Override
			public Render<? super EntityClayCommanderDelcatty> createRenderFor(RenderManager manager)
			{
				return new RenderClayCommanderDelcatty(manager, new ModelClayCommanderDelcatty(), 0.5F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityClayEspurr.class, new IRenderFactory<EntityClayEspurr>()
		{
			@Override
			public Render<? super EntityClayEspurr> createRenderFor(RenderManager manager)
			{
				return new RenderClayEspurr(manager, new ModelClayEspurr(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityClayPurrloin.class, new IRenderFactory<EntityClayPurrloin>()
		{
			@Override
			public Render<? super EntityClayPurrloin> createRenderFor(RenderManager manager)
			{
				return new RenderClayPurrloin(manager, new ModelClayPurrloin(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityClayShinx.class, new IRenderFactory<EntityClayShinx>()
		{
			@Override
			public Render<? super EntityClayShinx> createRenderFor(RenderManager manager)
			{
				return new RenderClayShinx(manager, new ModelClayShinx(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityClaySkitty.class, new IRenderFactory<EntityClaySkitty>()
		{
			@Override
			public Render<? super EntityClaySkitty> createRenderFor(RenderManager manager)
			{
				return new RenderClaySkitty(manager, new ModelClaySkitty(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityCracklingNincada.class, new IRenderFactory<EntityCracklingNincada>()
		{
			@Override
			public Render<? super EntityCracklingNincada> createRenderFor(RenderManager manager)
			{
				return new RenderCracklingNincada(manager, new ModelZerglingNincada(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityDemonVulpix.class, new IRenderFactory<EntityDemonVulpix>()
		{
			@Override
			public Render<? super EntityDemonVulpix> createRenderFor(RenderManager manager)
			{
				return new RenderDemonVulpix(manager, new ModelDemonVulpix(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityFeywoodAbsol.class, new IRenderFactory<EntityFeywoodAbsol>()
		{
			@Override
			public Render<? super EntityFeywoodAbsol> createRenderFor(RenderManager manager)
			{
				return new RenderFeywoodAbsol(manager, new ModelFeywoodAbsol(), 0.3F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityFirecrackerLitten.class, new IRenderFactory<EntityFirecrackerLitten>()
		{
			@Override
			public Render<? super EntityFirecrackerLitten> createRenderFor(RenderManager manager)
			{
				return new RenderFirecrackerLitten(manager, new ModelFirecrackerLitten(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityForcewindEelektrik.class, new IRenderFactory<EntityForcewindEelektrik>()
		{
			@Override
			public Render<? super EntityForcewindEelektrik> createRenderFor(RenderManager manager)
			{
				return new RenderForcewindEelektrik(manager, new ModelForcewindEelektrik(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityFoxcraftFennekin.class, new IRenderFactory<EntityFoxcraftFennekin>()
		{
			@Override
			public Render<? super EntityFoxcraftFennekin> createRenderFor(RenderManager manager)
			{
				return new RenderFoxcraftFennekin(manager, new ModelFoxcraftFennekin(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityInfestedDeerling.class, new IRenderFactory<EntityInfestedDeerling>()
		{
			@Override
			public Render<? super EntityInfestedDeerling> createRenderFor(RenderManager manager)
			{
				return new RenderInfestedDeerling(manager, new ModelInfestedDeerling(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityMuBuneary.class, new IRenderFactory<EntityMuBuneary>()
		{
			@Override
			public Render<? super EntityMuBuneary> createRenderFor(RenderManager manager)
			{
				return new RenderMuBuneary(manager, new ModelMuBuneary(), 0.3F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityOkamiEspeon.class, new IRenderFactory<EntityOkamiEspeon>()
		{
			@Override
			public Render<? super EntityOkamiEspeon> createRenderFor(RenderManager manager)
			{
				return new RenderOkamiEspeon(manager, new ModelOkamiEspeon(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityOkamiSylveon.class, new IRenderFactory<EntityOkamiSylveon>()
		{
			@Override
			public Render<? super EntityOkamiSylveon> createRenderFor(RenderManager manager)
			{
				return new RenderOkamiSylveon(manager, new ModelOkamiSylveon(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityOkamiUmbreon.class, new IRenderFactory<EntityOkamiUmbreon>()
		{
			@Override
			public Render<? super EntityOkamiUmbreon> createRenderFor(RenderManager manager)
			{
				return new RenderOkamiUmbreon(manager, new ModelOkamiUmbreon(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityRaptorZerglingNincada.class, new IRenderFactory<EntityRaptorZerglingNincada>()
		{
			@Override
			public Render<? super EntityRaptorZerglingNincada> createRenderFor(RenderManager manager)
			{
				return new RenderRaptorZerglingNincada(manager, new ModelZerglingNincada(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntitySnowSorceressBraixen.class, new IRenderFactory<EntitySnowSorceressBraixen>()
		{
			@Override
			public Render<? super EntitySnowSorceressBraixen> createRenderFor(RenderManager manager)
			{
				return new RenderSnowSorceressBraixen(manager, new ModelSnowSorceressBraixen(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntitySwordieMienshao.class, new IRenderFactory<EntitySwordieMienshao>()
		{
			@Override
			public Render<? super EntitySwordieMienshao> createRenderFor(RenderManager manager)
			{
				return new RenderSwordieMienshao(manager, new ModelSwordieMienshao(), 0.2F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityVastayaNinetales.class, new IRenderFactory<EntityVastayaNinetales>()
		{
			@Override
			public Render<? super EntityVastayaNinetales> createRenderFor(RenderManager manager)
			{
				return new RenderVastayaNinetales(manager, new ModelAhriNinetales(), 0.3F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityVoorstMightyena.class, new IRenderFactory<EntityVoorstMightyena>()
		{
			@Override
			public Render<? super EntityVoorstMightyena> createRenderFor(RenderManager manager)
			{
				return new RenderVoorstMightyena(manager, new ModelVoorstMightyena(), 0.5F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityZerglingNincada.class, new IRenderFactory<EntityZerglingNincada>()
		{
			@Override
			public Render<? super EntityZerglingNincada> createRenderFor(RenderManager manager)
			{
				return new RenderZerglingNincada(manager, new ModelZerglingNincada(), 0.2F);
			}
		});
		
		/*
		 * Abilities
		 */
		RenderingRegistry.registerEntityRenderingHandler(EntitySubstituteDoll.class, new IRenderFactory<EntitySubstituteDoll>()
		{
			@Override
			public Render<? super EntitySubstituteDoll> createRenderFor(RenderManager manager)
			{
				return new RenderSubstituteDoll(manager, new ModelSubstituteDoll(), 0.4F);
			}
		});

		/*
		 * Projectiles
		 */
		RenderingRegistry.registerEntityRenderingHandler(EntityFireworkMissile.class, new IRenderFactory() 
		{
			@Override
			public Render createRenderFor(RenderManager manager) 
			{
				return new RenderFireworkMissile(manager);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityHunterBolt.class, new IRenderFactory() 
		{
			@Override
			public Render createRenderFor(RenderManager manager) 
			{
				return new RenderHunterBolt(manager);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityVastayaFireball.class, new IRenderFactory() 
		{
			@Override
			public Render createRenderFor(RenderManager manager) 
			{
				return new RenderVastayaFireball(manager);
			}
		});

		MinecraftForge.EVENT_BUS.register(new TextureStitcherFX());
	}

	@Override
	public void registerRenderers() {}

	public void initTimer() 
	{
		mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), KindredLegacyMain.fTimer);
	}

	public float getPartialTick() 
	{
		return mcTimer.renderPartialTicks;
	}

	@Override
	public World getWorldClient() 
	{
		return FMLClientHandler.instance().getWorldClient();
	}

	@Override
	public void displayPoketamableRenameGui(ItemStack itemStack)
	{
		FMLClientHandler.instance().getClient().displayGuiScreen(new GuiPoketamableRename(itemStack));
	}
	
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
}