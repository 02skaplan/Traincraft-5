package train.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TCOreDictionaryHandler
{
    public static boolean itemStackMatches(ItemStack item1, ItemStack item2)
    {
        if (item1 == null || item2 == null) return false;

        // Direct match for exact ItemStacks (including wildcards)
        boolean isExactItemMatch = item1.getItem() == item2.getItem();
        if (isExactItemMatch && (item1.getItemDamage() == item2.getItemDamage() || item2.getItemDamage() == OreDictionary.WILDCARD_VALUE))
        {
            return true;
        }

        // Fetch all variants for each stack using your exact getOreVariants

        List<ItemStack> variants2 = getOreVariants(item2);

        //List<ItemStack> variants1 = getOreVariants(item1);

        // Match if **any variant of item1 is the same ItemStack as any variant of item2**

        for (ItemStack v2 : variants2)
        {
            if (item1.getItem() == v2.getItem())
            {
                if (v2.getItemDamage() == OreDictionary.WILDCARD_VALUE && item2.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                {
                    return true;
                }

                if (item1.getItemDamage() == v2.getItemDamage())
                {
                    return true;
                }
            }
        }

        //if (variants2.contains(item1))
        //{
        //    return true;
        //}

        // Handle Water Bucket Variants
        if (waterbucket.contains(item1) && waterbucket.contains(item2))
        {
            return true;
        }



        return false;
    }

    public final static ArrayList<ItemStack> waterbucket = waterContainers();

    private static ArrayList<ItemStack> waterContainers()
    {
        ArrayList<ItemStack> containers = new ArrayList<ItemStack>();
        for (FluidContainerRegistry.FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
        {
            if(data.fluid.fluid == FluidRegistry.WATER){
                containers.add(data.filledContainer);
            }
        }
        return containers;
    }

    private static HashMap<ItemStack, List<ItemStack>> oreDictionaryCache = new HashMap<>();

    public static List<ItemStack> getOreVariants(ItemStack stack)
    {
        if (oreDictionaryCache.containsKey(stack)) {
            return oreDictionaryCache.get(stack);
        }

        List<ItemStack> result = new ArrayList<>();

        int[] oreIDs = OreDictionary.getOreIDs(stack);

        if (oreIDs.length == 0) {
            result.add(stack);
            oreDictionaryCache.put(stack, result);
            return result;
        }

        for (int id : oreIDs) {
            for (ItemStack oreStack : OreDictionary.getOres(OreDictionary.getOreName(id))) {
                result.add(oreStack.copy());
            }
        }

        oreDictionaryCache.put(stack, result);
        return result;
    }
}
