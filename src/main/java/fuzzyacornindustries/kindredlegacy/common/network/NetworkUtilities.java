package fuzzyacornindustries.kindredlegacy.common.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedBoolean;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedDouble;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedEnum;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedFloat;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedFluidStack;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedInt;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedItemHandler;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedItemStack;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField.SyncedString;
import fuzzyacornindustries.kindredlegacy.lib.Log;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Based on PneumaticCraft's code
 **/
public class NetworkUtilities 
{
    /**
     * Get a list of all the synced fields for a syncable object
     *
     * @param syncable the object whose fields we are extracting
     * @param searchedAnnotation the annotation type to search for
     * @return a list of all the fields annotated with the given type
     */
    public static List<SyncedField<?>> getSyncedFields(Object syncable, Class<? extends Annotation> searchedAnnotation) 
    {
        List<SyncedField<?>> syncedFields = new ArrayList<>();
        Class<?> examinedClass = syncable.getClass();
        while (examinedClass != null) 
        {
            for (Field field : examinedClass.getDeclaredFields()) 
            {
                if (field.getAnnotation(searchedAnnotation) != null) 
                {
                    syncedFields.addAll(getSyncedFieldsForField(field, syncable, searchedAnnotation));
                }
            }
            examinedClass = examinedClass.getSuperclass();
        }
        // record how the field was found - later on, SyncedField methods can use this information
        // e.g. GuiSynced data can be sent much more often than DescSynced data
        syncedFields.forEach(field -> field.setAnnotation(searchedAnnotation));
        return syncedFields;
    }

    private static List<SyncedField<?>> getSyncedFieldsForField(Field field, Object te, Class<? extends Annotation> searchedAnnotation) 
    {
        boolean isLazy = field.getAnnotation(LazySynced.class) != null;
        List<SyncedField<?>> syncedFields = new ArrayList<>();
        SyncedField<?> syncedField = getSyncedFieldForField(field, te);
        if (syncedField != null) 
        {
            syncedFields.add(syncedField.setLazy(isLazy));
        } else 
        {
            Object o;
            try 
            {
                int filteredIndex = field.getAnnotation(FilteredSynced.class) != null ? field.getAnnotation(FilteredSynced.class).index() : -1;
                field.setAccessible(true);
                o = field.get(te);
                if (o instanceof int[]) 
                {
                    int[] array = (int[]) o;
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedInt(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } else 
                    {
                        for (int i = 0; i < array.length; i++) 
                        {
                            syncedFields.add(new SyncedInt(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o instanceof float[]) 
                {
                    float[] array = (float[]) o;
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedFloat(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } 
                    else 
                    {
                        for (int i = 0; i < array.length; i++) {
                            syncedFields.add(new SyncedFloat(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o instanceof double[]) 
                {
                    double[] array = (double[]) o;
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedDouble(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } 
                    else 
                    {
                        for (int i = 0; i < array.length; i++) 
                        {
                            syncedFields.add(new SyncedDouble(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o instanceof boolean[]) 
                {
                    boolean[] array = (boolean[]) o;
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedBoolean(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } else 
                    {
                        for (int i = 0; i < array.length; i++) 
                        {
                            syncedFields.add(new SyncedBoolean(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o instanceof String[]) {
                    String[] array = (String[]) o;
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedString(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } 
                    else 
                    {
                        for (int i = 0; i < array.length; i++) 
                        {
                            syncedFields.add(new SyncedString(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o.getClass().isArray() && o.getClass().getComponentType().isEnum()) 
                {
                    Object[] enumArray = (Object[]) o;
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedEnum(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } 
                    else 
                    {
                        for (int i = 0; i < enumArray.length; i++) 
                        {
                            syncedFields.add(new SyncedEnum(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o instanceof ItemStack[]) 
                {
                    ItemStack[] array = (ItemStack[]) o;
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedItemStack(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } 
                    else 
                    {
                        for (int i = 0; i < array.length; i++) 
                        {
                            syncedFields.add(new SyncedItemStack(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o instanceof FluidStack[]) 
                {
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedFluidStack(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } 
                    else 
                    {
                        FluidStack[] array = (FluidStack[]) o;
                        for (int i = 0; i < array.length; i++) 
                        {
                            syncedFields.add(new SyncedFluidStack(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                    return syncedFields;
                }
                if (o instanceof ItemStackHandler[]) 
                {
                    if (filteredIndex >= 0) 
                    {
                        syncedFields.add(new SyncedItemStack(te, field).setArrayIndex(filteredIndex).setLazy(isLazy));
                    } 
                    else 
                    {
                        ItemStackHandler[] array = (ItemStackHandler[]) o;
                        for (int i = 0; i < array.length; i++) 
                        {
                            syncedFields.add(new SyncedItemStack(te, field).setArrayIndex(i).setLazy(isLazy));
                        }
                    }
                }
                if (field.getType().isArray()) 
                {
                    Object[] array = (Object[]) o;
                    for (Object obj : array) 
                    {
                        syncedFields.addAll(getSyncedFields(obj, searchedAnnotation));
                    }
                } 
                else 
                {
                    syncedFields.addAll(getSyncedFields(o, searchedAnnotation));
                }
                if (syncedFields.size() > 0) return syncedFields;
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            Log.warning("Field " + field + " didn't produce any syncable fields!");
        }
        return syncedFields;
    }

    private static SyncedField<?> getSyncedFieldForField(Field field, Object te) 
    {
        if (int.class.isAssignableFrom(field.getType())) return new SyncedInt(te, field);
        if (float.class.isAssignableFrom(field.getType())) return new SyncedFloat(te, field);
        if (double.class.isAssignableFrom(field.getType())) return new SyncedDouble(te, field);
        if (boolean.class.isAssignableFrom(field.getType())) return new SyncedBoolean(te, field);
        if (String.class.isAssignableFrom(field.getType())) return new SyncedString(te, field);
        if (field.getType().isEnum()) return new SyncedEnum(te, field);
        if (ItemStack.class.isAssignableFrom(field.getType())) return new SyncedItemStack(te, field);
        if (FluidStack.class.isAssignableFrom(field.getType())) return new SyncedFluidStack(te, field);
        if (IItemHandlerModifiable.class.isAssignableFrom(field.getType())) return new SyncedItemHandler(te, field);
        return null;
    }
}