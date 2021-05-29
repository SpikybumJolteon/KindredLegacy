package fuzzyacornindustries.kindredlegacy.common.core;

import fuzzyacornindustries.kindredlegacy.common.inventory.container.VerdantPurifierContainer;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers 
{
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Names.MOD_ID);

    public static final RegistryObject<ContainerType<VerdantPurifierContainer>> VERDANT_PURIFIER
            = register("verdant_purifier", VerdantPurifierContainer::new);

    @SuppressWarnings("unchecked")
	private static <C extends Container, T extends ContainerType<C>> RegistryObject<T> register(String name, IContainerFactory<? extends C> f) 
    {
        //noinspection unchecked
        return CONTAINERS.register(name, () -> (T) IForgeContainerType.create(f));
    }
}