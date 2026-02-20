/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 *
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core.handlers;

import codechicken.lib.inventory.ItemKey;
import com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.ItemFluidContainer;
import net.minecraftforge.oredict.OreDictionary;
import train.common.Traincraft;
import train.common.api.*;
import train.common.entity.rollingStock.freight.*;
import train.common.enums.CargoItemFilter;
import train.common.items.ItemBlockOreTC;
import train.common.items.ItemTCRail;
import train.common.utils.devutils.DebugUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ItemHandler
{
	public static final Set<train.common.slots.util.ItemKey> BANNED_ITEMS = new HashSet<>();

	public static void parseBannedItems(String[] entries) {

		for (String line : entries)
		{
			if (line == null)
			{
				continue;
			}

			line = line.trim();
			if (line.isEmpty())
			{
				continue;
			}

			String modid = "minecraft";
			String name;
			String metaPart = null;

			String[] split = line.split(":");

			if (split.length == 1) {
				name = split[0];
			} else if (split.length == 2) {
				modid = split[0];
				name  = split[1];
			} else {
				modid = split[0];
				name  = split[1];
				metaPart = split[2];
			}

			Item item = GameRegistry.findItem(modid, name);
			if (item == null)
			{
				continue;
			}

			// No meta → wildcard
			if (metaPart == null) {
				BANNED_ITEMS.add(new train.common.slots.util.ItemKey(item, -1));
				continue;
			}

			// Range: 0-15
			if (metaPart.contains("-")) {
				String[] r = metaPart.split("-");
				int start = Integer.parseInt(r[0]);
				int end   = Integer.parseInt(r[1]);

				for (int i = start; i <= end; i++) {
					BANNED_ITEMS.add(new train.common.slots.util.ItemKey(item, i));
				}
				continue;
			}

			// List: 1,2,3,5
			if (metaPart.contains(",")) {
				for (String s : metaPart.split(",")) {
					BANNED_ITEMS.add(new train.common.slots.util.ItemKey(item, Integer.parseInt(s)));
				}
				continue;
			}

			// Single meta
			BANNED_ITEMS.add(new train.common.slots.util.ItemKey(item, Integer.parseInt(metaPart)));
		}

		ConfigHandler.ROLLINGSTOCK_INVENTORY_BLACKLIST_RAW = null;
	}


	public static boolean handleItems(Entity entity, ItemStack itemstack) {
		if (itemstack != null) {
			if (entity instanceof Freight) {
				return handleFreight(entity, itemstack);
			} else if (entity instanceof DieselTrain) {
				return false;
			} else if (entity instanceof ElectricTrain) {
				return false;
			} else if (entity instanceof SteamTrain) {
				return false;
			} else if (entity instanceof Tender) {
				return false;
			} else {
				return false;
			}
		}
		return false;
	}

	public static boolean isBanned(ItemStack stack)
	{
		for (train.common.slots.util.ItemKey key : BANNED_ITEMS)
		{
			if (key.matches(stack)) {
				return true;
			}
		}
		return false;
	}

	public static boolean handleFreight(Entity entity, ItemStack itemstack)
	{
		if(itemstack == null)
		{
			return false;
		}

		Block block = Block.getBlockFromItem(itemstack.getItem());
		if (block == null)
		{
			return false;
		}

		if (isBanned(itemstack))
		{
			return false;
		}

		if (entity instanceof AbstractStandardFreightCar)
		{
			AbstractStandardFreightCar car = (AbstractStandardFreightCar) entity;
			int oreDicID;
			switch (car.GetCargoFilterCategory())
			{
				case WOOD_PRODUCTS:
					oreDicID = OreDictionary.getOreID(itemstack);
					return oreDicID == OreDictionary.getOreID("plankWood")
							|| oreDicID == OreDictionary.getOreID("logWood")
							|| oreDicID == OreDictionary.getOreID("slabWood")
							|| oreDicID == OreDictionary.getOreID("stairWood")
							|| oreDicID == OreDictionary.getOreID("woodRubber")
							|| oreDicID == OreDictionary.getOreID("drywallFinished")
							|| itemstack.getItem() == Item.getItemFromBlock(Blocks.ladder)
							|| itemstack.getItem() == Item.getItemFromBlock(Blocks.fence)
							|| itemstack.getItem() == Item.getItemFromBlock(Blocks.fence_gate)
							|| oreDicID == OreDictionary.getOreID("woodRubber");
				case LOG_WOOD:
					oreDicID = OreDictionary.getOreID(itemstack);
					return oreDicID == OreDictionary.getOreID("logWood");
				case ASSEMBLED_TRAIN_TRACK:
					return block instanceof BlockRailBase || itemstack.getItem() instanceof ItemTCRail;
				case GRAIN:
					Item item = itemstack.getItem();
					if (item == Items.wheat
							|| item == Items.wheat_seeds
							|| item == Items.melon_seeds
							|| item == Items.pumpkin_seeds
							|| item instanceof ItemSeeds)
					{
						return true;
					}
					return cropStuff(itemstack);
				case OPAQUE_BLOCKS:
					return block.isOpaqueCube();
				case WOOD_CHIPS:
					return powderWood(itemstack);
				case ROCK_MATERIAL:
					return block.getMaterial() == Material.rock;
				case ICE_MATERIAL:
					return block.getMaterial() == Material.ice || block.getMaterial() == Material.packedIce;
				case ORE:
					return oreBlocks(itemstack);
				case INGOT:
					return ingotItems(itemstack);
				case REDSTONE:
					return OreDictionary.getOres("dustRedstone").contains(itemstack);
				case FOOD:
					return itemstack.getItem() instanceof ItemFood;
				default:
					if (ConfigHandler.ROLLINGSTOCK_INVENTORY_BAN_OPEN_FLUID_CONTAINERS == false)
					{
						return true;
					}
					// Allowing Lava Buckets since not alot of cars for this
					if ((itemstack.getItem() == Items.lava_bucket) == false
							&& FluidContainerRegistry.isFilledContainer(itemstack))
					{
						return false;
					}
					return true;
			}

		}
		return true;
	}

	private static boolean oreCoal(ItemStack itemstack){
	    String[] names = new String[] { "cropPotato"};
	    for (String name: names){
	        if (OreDictionary.getOreID(name) == OreDictionary.getOreID(itemstack)) {
	            return true;
            }
        }
	    return false;
    }

	private static boolean powderWood(ItemStack itemstack){
		String[] names = new String[] { "dustWood", "pulpWood"};
		for (String name: names){
			if (OreDictionary.getOreID(name) == OreDictionary.getOreID(itemstack)) {
				return true;
			}
		}
		return false;
	}


	private static boolean cropStuff(ItemStack itemstack) {
		String[] names = new String[] { "cropCorn", "cropRice", "seedRice", "seedCorn", "listAllseed" };
		for (String name: names) {
			if (OreDictionary.getOreID(name) == OreDictionary.getOreID(itemstack)) {
				return true;
			}
		}
		return false;
	}

	private static LinkedList<String> OREDICT_ORES = null;

	public static boolean oreBlocks(ItemStack i){
		if (OREDICT_ORES==null){
			OREDICT_ORES = new LinkedList<>();
		}
		if (!OREDICT_ORES.contains(i.getUnlocalizedName())){
			for(Integer ore : OreDictionary.getOreIDs(i)) {
				if (OreDictionary.getOreName(ore).toLowerCase().startsWith("ore")){
					OREDICT_ORES.add(i.getUnlocalizedName());
					return true;
				}
			}
		}
		return OREDICT_ORES.contains(i.getUnlocalizedName());
	}

	private static LinkedList<String> OREDICT_INGOTS = null;

	public static boolean ingotItems(ItemStack i){
		if (OREDICT_INGOTS==null){
			OREDICT_INGOTS = new LinkedList<>();
		}
		if (!OREDICT_INGOTS.contains(i.getUnlocalizedName())){
			for(Integer ore : OreDictionary.getOreIDs(i)) {
				if (OreDictionary.getOreName(ore).toLowerCase().startsWith("ingot")){
					OREDICT_INGOTS.add(i.getUnlocalizedName());
					return true;
				}
			}
		}
		return OREDICT_INGOTS.contains(i.getUnlocalizedName());
	}
}
