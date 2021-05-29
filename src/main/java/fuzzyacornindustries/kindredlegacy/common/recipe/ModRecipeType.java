package fuzzyacornindustries.kindredlegacy.common.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.stream.Stream;

import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.common.network.PacketClearRecipeCache;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IFutureReloadListener;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Recipe Type code logic based on PneumaticCraft
 **/
public class ModRecipeType<T extends ModRecipe> implements IRecipeType<T> 
{
    private static final List<ModRecipeType<? extends ModRecipe>> types = new ArrayList<>();

    public static final ModRecipeType<VerdantPurifyingRecipe> VERDANT_PURIFYING
            = registerType(ModRecipeTypes.VERDANT_PURIFYING);
    
    private final Map<ResourceLocation, T> cachedRecipes = new HashMap<>();
    private final ResourceLocation registryName;
    private static CacheReloadListener cacheReloadListener;

    private static <T extends ModRecipe> ModRecipeType<T> registerType(String name) 
    {
    	ModRecipeType<T> type = new ModRecipeType<>(name);
        types.add(type);
        return type;
    }

    static void registerRecipeTypes(IForgeRegistry<IRecipeSerializer<?>> registry) 
    {
        types.forEach(type -> Registry.register(Registry.RECIPE_TYPE, type.registryName, type));
    }

    private ModRecipeType(String name) 
    {
        this.registryName = new ResourceLocation(Names.MOD_ID, name);;
    }

    public static CacheReloadListener getCacheReloadListener() 
    {
        if (cacheReloadListener == null) 
        {
            cacheReloadListener = new CacheReloadListener();
        }
        return cacheReloadListener;
    }

    @Override
    public String toString() 
    {
        return registryName.toString();
    }

    public static void clearCachedRecipes() 
    {
        types.forEach(type -> type.cachedRecipes.clear());
    }

    public Map<ResourceLocation, T> getRecipes(World world) 
    {
        if (world == null) 
        {
            // we should pretty much always have a world, but here's a fallback
            world = ServerLifecycleHooks.getCurrentServer().getWorld(DimensionType.OVERWORLD);
            if (world == null) return Collections.emptyMap();
        }

        if (cachedRecipes.isEmpty()) 
        {
            RecipeManager recipeManager = world.getRecipeManager();
            List<T> recipes = recipeManager.getRecipes(this, ModRecipe.DummyIInventory.getInstance(), world);
            recipes.forEach(recipe -> cachedRecipes.put(recipe.getId(), recipe));
        }

        return cachedRecipes;
    }

    public Stream<T> stream(World world) 
    {
        return getRecipes(world).values().stream();
    }

    public T findFirst(World world, Predicate<T> predicate) 
    {
        return stream(world).filter(predicate).findFirst().orElse(null);
    }

    public T getRecipe(World world, ResourceLocation recipeId) 
    {
        return getRecipes(world).get(recipeId);
    }

    public static class CacheReloadListener implements IFutureReloadListener 
    {
        @Override
        public CompletableFuture<Void> reload(IStage stage, IResourceManager resourceManager, IProfiler preparationsProfiler, IProfiler reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
            return CompletableFuture.runAsync(() -> {
                clearCachedRecipes();
                
                NetworkHandler.sendToAll(new PacketClearRecipeCache());
            }, gameExecutor).thenCompose(stage::markCompleteAwaitingOthers);
        }
    }
}