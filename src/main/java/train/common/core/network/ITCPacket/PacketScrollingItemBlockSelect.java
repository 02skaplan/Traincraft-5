package train.common.core.network.ITCPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import train.common.items.BallastTypes;
import train.common.items.ItemTCRail;
import train.common.library.track.EnumCoreTrack;
import train.common.library.track.EnumTracks;

import java.io.IOException;
import java.util.*;

public class PacketScrollingItemBlockSelect implements ITCPacket
{
    private int slot = 0;
    private boolean posNeg = false;

    public PacketScrollingItemBlockSelect() {}

    public PacketScrollingItemBlockSelect(int slot, boolean posNeg) {
        this.slot = slot;
        this.posNeg = posNeg;
    }


    private static final List<EnumCoreTrack> CORE_MEDIUM_GROUP =
            Arrays.asList(
                    EnumCoreTrack.CORE_MEDIUM_STRAIGHT,
                    EnumCoreTrack.CORE_3_SLOPE
            );

    private static final List<EnumCoreTrack> CORE_LONG_GROUP =
            Arrays.asList(
                    EnumCoreTrack.CORE_LONG_STRAIGHT,
                    EnumCoreTrack.CORE_6_SLOPE
            );

    private static final List<EnumCoreTrack> CORE_VERY_LONG_GROUP =
            Arrays.asList(
                    EnumCoreTrack.CORE_VERY_LONG_STRAIGHT,
                    EnumCoreTrack.CORE_12_SLOPE
            );

    public static EnumCoreTrack shift(EnumCoreTrack current, boolean forward) {

        List<EnumCoreTrack> group;

        if (CORE_MEDIUM_GROUP.contains(current))
        {
            group = CORE_MEDIUM_GROUP;
        }
        else if (CORE_LONG_GROUP.contains(current))
        {
            group = CORE_LONG_GROUP;
        }
        else if (CORE_VERY_LONG_GROUP.contains(current))
        {
            group = CORE_VERY_LONG_GROUP;
        }
        else
        {
            return current; // not shiftable
        }

        int index = group.indexOf(current);
        int newIndex = index + (forward ? 1 : -1);

        // clamp
        if (newIndex < 0) newIndex = 0;
        if (newIndex >= group.size()) newIndex = group.size() - 1;

        return group.get(newIndex);
    }




    @Override
    public void processData(EntityPlayer entityPlayer, ByteBufInputStream bbis) throws IOException
    {
        int slot = bbis.readInt();
        ItemStack itemStack = entityPlayer.inventory.getStackInSlot(slot);
        boolean incIncrease = bbis.readBoolean();

        if (itemStack != null && itemStack.getItem() instanceof ItemTCRail)
        {
            ItemTCRail itemTCRail = (ItemTCRail)itemStack.getItem();

            if (itemTCRail.getTrackType().getBallastType() != null && itemTCRail.getTrackType().getBallastType() != BallastTypes.DYNAMIC || itemTCRail.getTrackType().getLabel().contains("ROAD_CROSSING"))
            {
                return;
            }

            HashMap<EnumCoreTrack, HashMap<String, EnumTracks>> tracks = EnumTracks.GetTracksByGroup(itemTCRail.getTrackType().getVariant());

            EnumCoreTrack coreTrack = shift(itemTCRail.getTrackType().getCoreTrack(), incIncrease);

            EnumTracks newTrack = tracks.get(coreTrack).get("") == null ? tracks.get(coreTrack).get(BallastTypes.DYNAMIC.name()) : tracks.get(coreTrack).get("") ;

            if (newTrack != null)
            {
                entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack(newTrack.getItem().item, itemStack.stackSize));
            }
        }
    }
    @Override
    public void appendData(ByteBuf buffer) throws IOException {
        buffer.writeInt(slot);
        buffer.writeBoolean(posNeg);
    }
}
