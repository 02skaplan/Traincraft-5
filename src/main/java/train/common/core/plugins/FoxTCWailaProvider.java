package train.common.core.plugins;

import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.EntityRegistry;
import mcp.mobius.waila.api.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import train.common.api.AbstractTrains;
import train.common.api.EntityBogie;
import train.common.library.track.EnumTracks;
import train.common.library.track.ITrackDefinition;
import train.common.tile.ITileTCRail;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * FoxTC WAILA provider.
 *
 * Handles both:
 * - Blocks / TileEntities: rails, gag rails, machines, etc.
 * - Entities: rollingstock, locomotives, tenders, cars, etc.
 */
public class FoxTCWailaProvider implements IWailaDataProvider, IWailaEntityProvider {

    private static final String CONFIG_RAIL_INFO = "foxtc.railInfo";
    private static final String CONFIG_RAIL_DEBUG = "foxtc.railDebug";
    private static final String CONFIG_STOCK_INFO = "foxtc.stockInfo";
    private static final String CONFIG_STOCK_DEBUG = "foxtc.stockDebug";

    // ---------------------------------------------------------------------
    // Block / TileEntity WAILA NBT
    // ---------------------------------------------------------------------

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        if (tag == null) {
            tag = new NBTTagCompound();
        }

        if (te == null) {
            return tag;
        }

        tag.setInteger("foxtc.x", x);
        tag.setInteger("foxtc.y", y);
        tag.setInteger("foxtc.z", z);
        tag.setString("foxtc.tileClass", te.getClass().getSimpleName());

        if (isClassNamed(te, "TileTCRail"))
        {
            Object railType = firstNonNull(
                    callNoArg(te, "getRailType"),
                    callNoArg(te, "getType"),
                    getField(te, "type"),
                    getField(te, "trackType")
            );

            TileTCRail tileTCRail = (TileTCRail) te;

            if (railType != null) {
                tag.setString("foxtc.trackType", StatCollector.translateToLocal(EnumTracks.GetTrackByLabel(tileTCRail.getType()).getItem().item.getUnlocalizedName() + ".name"));
            }

            Object route = firstNonNull(
                    callNoArg(te, "getCurrentRoute"),
                    callNoArg(te, "getSwitchRoute"),
                    getField(te, "currentRoute"),
                    getField(te, "switchRoute")
            );

            if (route != null) {
                tag.setString("foxtc.route", String.valueOf(route));
            }

            Object facing = firstNonNull(
                    callNoArg(te, "getFacing"),
                    callNoArg(te, "getDirection"),
                    getField(te, "facing"),
                    getField(te, "direction")
            );

            if (facing != null) {
                tag.setString("foxtc.facing", String.valueOf(facing));
            }
        }

        if (isClassNamed(te, "TileTCRailGag")) {
            Object originX = firstNonNull(callNoArg(te, "getOriginX"), getField(te, "originX"));
            Object originY = firstNonNull(callNoArg(te, "getOriginY"), getField(te, "originY"));
            Object originZ = firstNonNull(callNoArg(te, "getOriginZ"), getField(te, "originZ"));

            if (originX != null) {
                tag.setString("foxtc.originX", String.valueOf(originX));
            }

            if (originY != null) {
                tag.setString("foxtc.originY", String.valueOf(originY));
            }

            if (originZ != null) {
                tag.setString("foxtc.originZ", String.valueOf(originZ));
            }

            TileTCRailGag gagRail = (TileTCRailGag) te;



            if (gagRail != null) {
                tag.setString("foxtc.gagType", StatCollector.translateToLocal(EnumTracks.GetTrackByLabel(gagRail.getType()).getItem().item.getUnlocalizedName() + ".name"));
            }
        }

        return tag;
    }

    // ---------------------------------------------------------------------
    // Entity WAILA NBT
    // ---------------------------------------------------------------------

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, Entity entity, NBTTagCompound tag, World world) {
        if (tag == null) {
            tag = new NBTTagCompound();
        }

        if (entity == null) {
            return tag;
        }

        AbstractTrains stock;

        if (entity instanceof EntityBogie)
        {
            stock = ((EntityBogie)entity).entityMainTrain;
            tag.setString("foxtc.bogienameoverride", stock.getCommandSenderName());
        }
        else
        {
            stock = (AbstractTrains) entity;
        }

        tag.setString("foxtc.entityClass", stock.getClass().getSimpleName());
        tag.setInteger("foxtc.entityId", stock.getEntityId());

        Object type = firstNonNull(
                callNoArg(stock, "getTrainType"),
                callNoArg(stock, "type"),
                getField(stock, "type")
        );

        tag.setString("foxtc.note", stock.getTrainNote());

        if (type != null) {
            tag.setString("foxtc.stockType", String.valueOf(type));
        }

        Object owner = firstNonNull(
                callNoArg(stock, "getOwner"),
                callNoArg(stock, "getOwnerName"),
                getField(stock, "owner"),
                getField(stock, "ownerName")
        );

        if (owner != null) {
            tag.setString("foxtc.owner", stock.getTransportOwner());
        }

        return tag;
    }

    // ---------------------------------------------------------------------
    // Block / TileEntity WAILA display
    // ---------------------------------------------------------------------

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity te = accessor.getTileEntity();

        if (te instanceof ITileTCRail)
        {
            ITileTCRail tcrail = (ITileTCRail) te;
            ITrackDefinition trackDefinition =  EnumTracks.getRawTracksList().get(tcrail.getType());
            if (trackDefinition != null)
            {
                ItemStack stack = new ItemStack(trackDefinition.getItem().item);
                if (stack != null) {
                    return stack.copy();
                }
            }
        }

        return null; // keep WAILA default
    }

    @Override
    public List getWailaHead(ItemStack itemStack, List currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity te = accessor.getTileEntity();

        if (te == null) {
            return currenttip;
        }

        if (isClassNamed(te, "TileTCRail")) {
            currenttip.clear();
            //currenttip.add(EnumChatFormatting.GOLD + "FoxTC Rail");
            return currenttip;
        }

        if (isClassNamed(te, "TileTCRailGag")) {
            currenttip.clear();
            //currenttip.add(EnumChatFormatting.GOLD + "FoxTC Rail Part");
            return currenttip;
        }

        return currenttip;
    }

    @Override
    public List getWailaBody(ItemStack itemStack, List currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (!config.getConfig(CONFIG_RAIL_INFO, true)) {
            return currenttip;
        }

        TileEntity te = accessor.getTileEntity();

        if (te == null) {
            return currenttip;
        }

        NBTTagCompound tag = accessor.getNBTData();

        if (isClassNamed(te, "TileTCRail")) {
            addMainRailInfo(currenttip, tag, config);
            return currenttip;
        }

        if (isClassNamed(te, "TileTCRailGag")) {
            addGagRailInfo(currenttip, tag, config);
            return currenttip;
        }

        return currenttip;
    }

    @Override
    public List getWailaTail(ItemStack itemStack, List currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity te = accessor.getTileEntity();

        if (te == null) {
            return currenttip;
        }

        if (te instanceof ITileTCRail)
        {
            currenttip.clear();
            currenttip.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + "Fox-Traincraft");
        }

        return currenttip;
    }

    // ---------------------------------------------------------------------
    // Entity WAILA display
    // ---------------------------------------------------------------------

    @Override
    public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List getWailaHead(Entity entity, List currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        if (entity == null) {
            return currenttip;
        }

        currenttip.clear();
        if (accessor.getNBTData().hasKey("foxtc.bogienameoverride"))
        {
            currenttip.add(EnumChatFormatting.GOLD + accessor.getNBTData().getString("foxtc.bogienameoverride") + " (Bogie)");
        }
        else
        {
            currenttip.add(EnumChatFormatting.GOLD + safeEntityName(entity));
        }

        return currenttip;
    }

    @Override
    public List getWailaBody(Entity entity, List currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        if (!config.getConfig(CONFIG_STOCK_INFO, true)) {
            return currenttip;
        }

        if (entity == null) {
            return currenttip;
        }

        NBTTagCompound tag = accessor.getNBTData();

        if (tag != null && tag.hasKey("foxtc.stockType")) {
            currenttip.add(
                    EnumChatFormatting.YELLOW + "Type: " +
                            EnumChatFormatting.WHITE + cleanName(tag.getString("foxtc.stockType"))
            );
        }

        if (tag != null && tag.hasKey("foxtc.note"))
        {
            currenttip.add(EnumChatFormatting.YELLOW + "Mark: " + EnumChatFormatting.WHITE + tag.getString("foxtc.note"));
        }

        if (tag != null && tag.hasKey("foxtc.owner")) {
            currenttip.add(
                    EnumChatFormatting.YELLOW + "Owner: " +
                            EnumChatFormatting.WHITE + tag.getString("foxtc.owner")
            );
        }

        if (config.getConfig(CONFIG_STOCK_DEBUG, false)) {
            currenttip.add(
                    EnumChatFormatting.GRAY + "Entity: " +
                            entity.getEntityId() + " / " +
                            entity.getClass().getSimpleName()
            );

            currenttip.add(
                    EnumChatFormatting.GRAY + "Pos: " +
                            round(entity.posX) + ", " +
                            round(entity.posY) + ", " +
                            round(entity.posZ)
            );
        }

        return currenttip;
    }

    @Override
    public List getWailaTail(Entity entity, List currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        currenttip.clear();


        EntityRegistry.EntityRegistration er = EntityRegistry.instance().lookupModSpawn(entity.getClass(), true);
        ModContainer modC = er.getContainer();
        String modName = modC.getName();
        currenttip.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + modName);

        return currenttip;
    }

    // ---------------------------------------------------------------------
    // Tooltip builders
    // ---------------------------------------------------------------------

    private void addMainRailInfo(List currenttip, NBTTagCompound tag, IWailaConfigHandler config) {
        if (tag == null) {
            return;
        }

        if (tag.hasKey("foxtc.trackType")) {
            currenttip.add(
                    EnumChatFormatting.YELLOW + "Track: " +
                            EnumChatFormatting.WHITE + cleanName(tag.getString("foxtc.trackType"))
            );
        }

        if (tag.hasKey("foxtc.route")) {
            currenttip.add(
                    EnumChatFormatting.YELLOW + "Route: " +
                            EnumChatFormatting.WHITE + cleanName(tag.getString("foxtc.route"))
            );
        }

        if (config.getConfig(CONFIG_RAIL_DEBUG, false))
        {
            if (tag.hasKey("foxtc.facing")) {
                currenttip.add(
                        EnumChatFormatting.YELLOW + "Facing: " +
                                EnumChatFormatting.WHITE + cleanName(tag.getString("foxtc.facing"))
                );
            }


            currenttip.add(
                    EnumChatFormatting.GRAY + "TE: " +
                            tag.getInteger("foxtc.x") + ", " +
                            tag.getInteger("foxtc.y") + ", " +
                            tag.getInteger("foxtc.z")
            );
        }
    }

    private void addGagRailInfo(List currenttip, NBTTagCompound tag, IWailaConfigHandler config) {
        if (tag == null) {
            return;
        }

        if (tag.hasKey("foxtc.gagType")) {
            currenttip.add(
                    EnumChatFormatting.YELLOW + "Track Part Type: " +
                            EnumChatFormatting.WHITE + cleanName(tag.getString("foxtc.gagType"))
            );
        }

        currenttip.add(
                EnumChatFormatting.YELLOW + "Rail Part: " +
                        EnumChatFormatting.WHITE + "Linked"
        );

        if (tag.hasKey("foxtc.originX") && tag.hasKey("foxtc.originY") && tag.hasKey("foxtc.originZ")) {
            currenttip.add(
                    EnumChatFormatting.YELLOW + "Origin: " +
                            EnumChatFormatting.WHITE +
                            tag.getString("foxtc.originX") + ", " +
                            tag.getString("foxtc.originY") + ", " +
                            tag.getString("foxtc.originZ")
            );
        }



        if (config.getConfig(CONFIG_RAIL_DEBUG, false)) {
            currenttip.add(
                    EnumChatFormatting.GRAY + "TE: " +
                            tag.getInteger("foxtc.x") + ", " +
                            tag.getInteger("foxtc.y") + ", " +
                            tag.getInteger("foxtc.z")
            );
        }
    }

    // ---------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------

    private static boolean isClassNamed(Object obj, String simpleName) {
        return obj != null && obj.getClass().getSimpleName().equals(simpleName);
    }

    private static String safeEntityName(Entity entity) {
        String name = null;

        try {
            name = entity.getCommandSenderName();
        } catch (Throwable ignored) {
        }

        if (name == null || name.trim().isEmpty()) {
            name = entity.getClass().getSimpleName();
        }

        return name;
    }

    private static String cleanName(String value) {
        if (value == null) {
            return "";
        }

        String text = value.replace('_', ' ').trim();

        if (text.length() == 0) {
            return text;
        }

        String lower = text.toLowerCase();
        StringBuilder out = new StringBuilder();
        boolean upperNext = true;

        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);

            if (upperNext && Character.isLetter(c)) {
                out.append(Character.toUpperCase(c));
                upperNext = false;
            } else {
                out.append(c);
            }

            if (c == ' ' || c == '-') {
                upperNext = true;
            }
        }

        return out.toString();
    }

    private static String round(double value) {
        return String.valueOf(Math.round(value * 100.0D) / 100.0D);
    }

    private static Object firstNonNull(Object... values) {
        if (values == null) {
            return null;
        }

        for (Object value : values) {
            if (value != null) {
                return value;
            }
        }

        return null;
    }

    private static Object callNoArg(Object target, String methodName) {
        if (target == null || methodName == null) {
            return null;
        }

        try {
            Method method = target.getClass().getMethod(methodName);
            method.setAccessible(true);
            return method.invoke(target);
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static Object getField(Object target, String fieldName) {
        if (target == null || fieldName == null) {
            return null;
        }

        Class clazz = target.getClass();

        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(target);
            } catch (Throwable ignored) {
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }
}