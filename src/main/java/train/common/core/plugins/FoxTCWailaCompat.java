package train.common.core.plugins;

import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import train.common.api.EntityBogie;
import train.common.api.EntityRollingStock;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

public final class FoxTCWailaCompat {

    private FoxTCWailaCompat() {
    }

    public static void callbackRegister(IWailaRegistrar registrar) {
        FoxTCWailaProvider provider = new FoxTCWailaProvider();

        /*
         * Important:
         *
         * FoxTCWailaProvider implements both IWailaDataProvider and IWailaEntityProvider.
         * WAILA has overloaded methods with the same name:
         *
         * registerHeadProvider(IWailaDataProvider, Class)
         * registerHeadProvider(IWailaEntityProvider, Class)
         *
         * So we split the same provider instance into two typed references.
         * This removes the ambiguous method call error.
         */
        IWailaDataProvider blockProvider = provider;
        IWailaEntityProvider entityProvider = provider;

        registrar.addConfig("FoxTC", "foxtc.railInfo", "Show FoxTC rail info");
        registrar.addConfig("FoxTC", "foxtc.railDebug", "Show FoxTC rail debug info", false);
        registrar.addConfig("FoxTC", "foxtc.stockInfo", "Show FoxTC rollingstock info");
        registrar.addConfig("FoxTC", "foxtc.stockDebug", "Show FoxTC rollingstock debug info", false);

        // -----------------------------------------------------------------
        // Block / TileEntity providers
        // -----------------------------------------------------------------

        registrar.registerStackProvider(blockProvider, BlockTCRail.class);
        registrar.registerHeadProvider(blockProvider, BlockTCRail.class);
        registrar.registerBodyProvider(blockProvider, BlockTCRail.class);
        registrar.registerTailProvider(blockProvider, BlockTCRail.class);

        registrar.registerStackProvider(blockProvider, BlockTCRailGag.class);
        registrar.registerHeadProvider(blockProvider, BlockTCRailGag.class);
        registrar.registerBodyProvider(blockProvider, BlockTCRailGag.class);
        registrar.registerTailProvider(blockProvider, BlockTCRailGag.class);

        registrar.registerNBTProvider(blockProvider, TileTCRail.class);
        registrar.registerNBTProvider(blockProvider, TileTCRailGag.class);

        // -----------------------------------------------------------------
        // Entity providers
        // -----------------------------------------------------------------

        registrar.registerHeadProvider(entityProvider, EntityRollingStock.class);
        registrar.registerBodyProvider(entityProvider, EntityRollingStock.class);
        registrar.registerTailProvider(entityProvider, EntityRollingStock.class);

        registrar.registerHeadProvider(entityProvider, EntityBogie.class);
        registrar.registerBodyProvider(entityProvider, EntityBogie.class);
        registrar.registerTailProvider(entityProvider, EntityBogie.class);

        /*
         * Only keep this if your WAILA jar supports entity NBT providers.
         *
         * If this line gives a compile error, remove it and also remove the
         * entity getNBTData(...) method from FoxTCWailaProvider.
         */
        registrar.registerNBTProvider(entityProvider, EntityRollingStock.class);
        registrar.registerNBTProvider(entityProvider, EntityBogie.class);
    }
}