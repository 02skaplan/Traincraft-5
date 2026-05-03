package train.common.blocks.stoppers;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.blocks.BlockStopper;
import train.common.tile.tileStopper.wood_type2.TileWoodType2_Generic_Stopper;

public class wood_type2_stopper extends BlockStopper {
    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileWoodType2_Generic_Stopper(meta);
    }
}

