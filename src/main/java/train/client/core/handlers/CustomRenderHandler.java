package train.client.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import train.client.render.RenderTCRail;
import train.common.enums.TCTrackDirection;
import train.common.items.BallastTypes;
import train.common.items.ItemTCRail;
import train.common.items.RailVariants;
import train.common.items.TCRailTypes;
import train.common.library.BlockIDs;
import train.common.library.track.EnumCoreTrack;
import train.common.library.track.EnumTracks;

import static train.common.library.track.EnumCoreTrack.*;


public class CustomRenderHandler
{
    String ballastMaterial;
    int blockColour;

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event )
    {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if ( player != null && player.getHeldItem() != null && ( player.getHeldItem().getItem() instanceof ItemTCRail) )
        {
            renderTCRailPreview(player, player.getHeldItem() );
        }
    }

    private void renderTCRailPreview(EntityClientPlayerMP player, ItemStack stack)
    {
        World world = Minecraft.getMinecraft().theWorld;
        if (world == null || Minecraft.getMinecraft().objectMouseOver == null)
        {
            return;
        }
        int x = Minecraft.getMinecraft().objectMouseOver.blockX;
        int y = Minecraft.getMinecraft().objectMouseOver.blockY;
        int z = Minecraft.getMinecraft().objectMouseOver.blockZ;

        if (world.getBlock(x, y, z) == Blocks.air)
        {
            return;
        }

        ItemTCRail item = (ItemTCRail) player.getHeldItem().getItem();

        // Check if item can be placed and select color
        boolean validPlacement = item.tryToPlaceTrack(player.getHeldItem(), player, world, x, y, z, false);
        float r = 1;
        float g = 0;
        float b = 0;
        float a = 0.5f;
        if (validPlacement)
        {
            r = 0;
            g = 1;
        }

        y = item.getPlacementHeight(world, x, y, z);
        double px = TileEntityRendererDispatcher.staticPlayerX;
        double py = TileEntityRendererDispatcher.staticPlayerY;
        double pz = TileEntityRendererDispatcher.staticPlayerZ;
        int facing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        Vector2f dir = ItemTCRail.getDirectionVector(facing);

        // Render
        GL11.glPushMatrix();
        GL11.glTranslated(x - px, y + 1 - py, z - pz);
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_BLEND);

        // Crossing
        if (item.getTrackType() == EnumTracks.SMALL_ROAD_CROSSING)
        {
            RenderTCRail.modelSmallStraight.render(null,"crossing", facing, 0, 0, 0, r, g, b, 0.5f);
        }
        else if (item.getTrackType() == EnumTracks.SMALL_ROAD_CROSSING_1)
        {
            RenderTCRail.modelSmallStraight.render(null, "crossing1", facing, 0, 0, 0, r, g, b, 0.5f);
        }
        else if (item.getTrackType() == EnumTracks.SMALL_ROAD_CROSSING_2)
        {
            RenderTCRail.modelSmallStraight.render(null,"crossing2", facing, 0, 0, 0, r, g, b, 0.5f);
        }
        // Straights
        else if (TCRailTypes.RailTypes.STRAIGHT.equals(item.getTrackType().getRailType()))
        {
            facing = TCTrackDirection.ConvertDiagonalDirectionInput(MathHelper.floor_double((player.rotationYaw * 8.0F / 360.0F + 0.5D)) & 7);
            dir = ItemTCRail.getDirectionVector(facing);

            int length = 1;
            switch (item.getTrackType().getCoreTrack())
            {
                case CORE_MEDIUM_STRAIGHT:
                    length = 3;
                break;
                case CORE_LONG_STRAIGHT:
                    length = 6;
                break;
                case CORE_VERY_LONG_STRAIGHT:
                    length = 12;
                break;
            }

            if (facing == 6 || facing == 4 || facing == 7 || facing == 5)
            {
                float dx = 0;
                float dz = 0;
                for (int i = 0; i < length; i++) {
                    if (facing == 6) {
                        dx = i;
                        dz = -1 * i;
                    }

                    if (facing == 4) {
                        dx = -1 * i;
                        dz = i;
                    }

                    if (facing == 7) {
                        dx = i;
                        dz = i;
                    }

                    if (facing == 5) {
                        dx = -1 * i;
                        dz = -1 * i;
                    }

                    RenderTCRail.modelSmallDiagonalStraight.render("", item.getTrackType().getVariant(), facing, dx, 0, dz, r, g, b, a);
                }
            }
            else
            {
                for (int i = 0; i < length; i++)
                {
                    float dx = dir.getX() * i;
                    float dz = dir.getY() * i;
                    RenderTCRail.modelSmallStraight.render(item.getTrackType(),"", facing, dx, 0, dz, r, g, b, a);
                }
            }
        }
        else if (CORE_DOUBLE_DIAMOND_CROSSING.equals(item.getTrackType().getCoreTrack())) {
            float dx = dir.getX();
            float dz = dir.getY();

            RenderTCRail.modelTwoWaysCrossing.render("diamond", facing, item.getTrackType().getVariant(), dx, 0, dz,  r, g, b, a);
        }
        else if (CORE_FOUR_WAYS_CROSSING.equals(item.getTrackType().getCoreTrack())) {
            RenderTCRail.modelTwoWaysCrossing.render("universal_crossing", facing, item.getTrackType().getVariant(), 0, 0, 0,  r, g, b, a);
        }
        else if (CORE_DIAMOND_CROSSING.equals(item.getTrackType().getCoreTrack()))
        {
            float dx = dir.getX();
            float dz = dir.getY();

            if (item.getTrackOrientation(facing, MathHelper.wrapAngleTo180_float(player.rotationYaw)).equals("left")) {
                RenderTCRail.modelLeftDiamondCrossing.render(item.getTrackType().getVariant(), dx, 0, dz, facing, r, g, b, a);
            } else {
                RenderTCRail.modelRightDiamondCrossing.render(item.getTrackType().getVariant(), dx, 0, dz, facing, r, g, b, a);
            }
        }
        else if (CORE_TWO_WAYS_CROSSING.equals(item.getTrackType().getCoreTrack()))
        {
            facing = TCTrackDirection.ConvertDiagonalDirectionInput(MathHelper.floor_double((player.rotationYaw * 8.0F / 360.0F + 0.5D)) & 7);
            if (facing == 6 || facing == 4 || facing == 7 || facing == 5)
            {
                RenderTCRail.modelTwoWaysCrossing.render( "diagonal_crossing", facing,item.getTrackType().getVariant(), 0, 0, 0,  r, g, b, a);
            }
            else
            {
                float dx = dir.getX();
                float dz = dir.getY();


                RenderTCRail.modelTwoWaysCrossing.render("twoways_crossing", 0, item.getTrackType().getVariant(), dx, 0, dz, r, g, b, 0.5f);

                RenderTCRail.modelSmallStraight.render(item.getTrackType(), "embedded", 0, dx, 0, dz + 1, r, g, b, a);
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), "embedded", 1, dx + 1, 0, dz, r, g, b, a);
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), "embedded", 2, dx, 0, dz - 1, r, g, b, a);
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), "embedded", 3, dx - 1, 0, dz, r, g, b, a);
            }
        }
        // Slopes
        else if (EnumCoreTrack.CORE_18_SLOPE.equals(item.getTrackType().getCoreTrack()))
        {
            if (BallastTypes.WOODSUPPORT.equals(item.getTrackType().getBallastType()) == false && BallastTypes.PEAGRAVEL.equals(item.getTrackType().getBallastType()) == false)
            {
                facing = TCTrackDirection.ConvertDiagonalDirectionInput(MathHelper.floor_double((player.rotationYaw * 8.0F / 360.0F + 0.5D)) & 7);
                blockInfo();
                switch (item.getTrackType().getBallastType())
                {
                    case GRAVEL:
                        blockColour = Blocks.gravel.colorMultiplier(world, x, y, z);
                        IIcon icon = Blocks.gravel.getIcon(1, 0);
                        if (icon != null && icon.getIconName() != null) {
                            ballastMaterial = icon.getIconName();
                        }
                        break;
                    case BALLAST:
                        blockColour = BlockIDs.oreTC.getBlock().colorMultiplier(world, x, y, z);
                        IIcon icon1 = BlockIDs.oreTC.getBlock().getIcon(1, 3);
                        if (icon1 != null && icon1.getIconName() != null) {
                            ballastMaterial = icon1.getIconName();
                        }
                        break;
                    case SNOWGRAVEL:
                        blockColour = BlockIDs.oreTC.getBlock().colorMultiplier(world, x, y, z);
                        IIcon icon2 = BlockIDs.oreTC.getBlock().getIcon(1, 4);
                        if (icon2 != null && icon2.getIconName() != null) {
                            ballastMaterial = icon2.getIconName();
                        }
                        break;
                    case DYNAMIC:
                        break;
                }
                if (facing == 4 || facing == 5 || facing == 6 || facing == 7) {
                    RenderTCRail.model1x18DiagonalSlope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, 0.5f, ballastMaterial, blockColour);
                }
                else {
                    RenderTCRail.model1x18Slope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a, ballastMaterial, blockColour);
                }
            }
            else
            {
                RenderTCRail.model1x18Slope.render(item.getTrackType().getVariant(), item.getTrackType().getBallastType(), facing, 0, 0, 0, r, g, b, 0.5f);
            }
        }
        else if (EnumCoreTrack.CORE_12_SLOPE.equals(item.getTrackType().getCoreTrack()))
        {
            if (BallastTypes.WOODSUPPORT.equals(item.getTrackType().getBallastType()) == false && BallastTypes.PEAGRAVEL.equals(item.getTrackType().getBallastType()) == false)
            {
                facing = TCTrackDirection.ConvertDiagonalDirectionInput(MathHelper.floor_double((player.rotationYaw * 8.0F / 360.0F + 0.5D)) & 7);
                blockInfo();
                switch (item.getTrackType().getBallastType())
                {
                    case GRAVEL:
                        blockColour = Blocks.gravel.colorMultiplier(world, x, y, z);
                        IIcon icon = Blocks.gravel.getIcon(1, 0);
                        if (icon != null && icon.getIconName() != null) {
                            ballastMaterial = icon.getIconName();
                        }
                        break;
                    case BALLAST:
                        blockColour = BlockIDs.oreTC.getBlock().colorMultiplier(world, x, y, z);
                        IIcon icon1 = BlockIDs.oreTC.getBlock().getIcon(1, 3);
                        if (icon1 != null && icon1.getIconName() != null) {
                            ballastMaterial = icon1.getIconName();
                        }
                        break;
                    case SNOWGRAVEL:
                        blockColour = BlockIDs.oreTC.getBlock().colorMultiplier(world, x, y, z);
                        IIcon icon2 = BlockIDs.oreTC.getBlock().getIcon(1, 4);
                        if (icon2 != null && icon2.getIconName() != null) {
                            ballastMaterial = icon2.getIconName();
                        }
                        break;
                    case DYNAMIC:
                        break;
                }
                if (facing == 4 || facing == 5 || facing == 6 || facing == 7) {
                    RenderTCRail.model1x12DiagonalSlope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, 0.5f, ballastMaterial, blockColour);
                }
                else {
                    RenderTCRail.model1x12Slope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a, ballastMaterial, blockColour);
                }
            }
            else
            {
                RenderTCRail.model1x12Slope.render(item.getTrackType().getVariant(), item.getTrackType().getBallastType(), facing, 0, 0, 0, r, g, b, 0.5f);
            }


        }
        else if (EnumCoreTrack.CORE_6_SLOPE.equals(item.getTrackType().getCoreTrack()))
        {
            if (BallastTypes.WOODSUPPORT.equals(item.getTrackType().getBallastType()) == false && BallastTypes.PEAGRAVEL.equals(item.getTrackType().getBallastType()) == false)
            {
                facing = TCTrackDirection.ConvertDiagonalDirectionInput(MathHelper.floor_double((player.rotationYaw * 8.0F / 360.0F + 0.5D)) & 7);
                blockInfo();
                switch (item.getTrackType().getBallastType())
                {
                    case GRAVEL:
                        blockColour = Blocks.gravel.colorMultiplier(world, x, y, z);
                        IIcon icon = Blocks.gravel.getIcon(1, 0);
                        if (icon != null && icon.getIconName() != null) {
                            ballastMaterial = icon.getIconName();
                        }
                        break;
                    case BALLAST:
                        blockColour = BlockIDs.oreTC.getBlock().colorMultiplier(world, x, y, z);
                        IIcon icon1 = BlockIDs.oreTC.getBlock().getIcon(1, 3);
                        if (icon1 != null && icon1.getIconName() != null) {
                            ballastMaterial = icon1.getIconName();
                        }
                        break;
                    case SNOWGRAVEL:
                        blockColour = BlockIDs.oreTC.getBlock().colorMultiplier(world, x, y, z);
                        IIcon icon2 = BlockIDs.oreTC.getBlock().getIcon(1, 4);
                        if (icon2 != null && icon2.getIconName() != null) {
                            ballastMaterial = icon2.getIconName();
                        }
                        break;
                    case DYNAMIC:
                    break;
                }

                if (facing == 4 || facing == 5 || facing == 6 || facing == 7)
                {
                    RenderTCRail.model1x6DiagonalSlope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, 0.5f, ballastMaterial, blockColour);
                }
                else
                {
                    RenderTCRail.model1x6Slope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a, ballastMaterial, blockColour);
                }
            }
            else
            {
                RenderTCRail.model1x6Slope.render(item.getTrackType().getVariant(), item.getTrackType().getBallastType(), facing, 0, 0, 0, r, g, b, 0.5f);
            }
        }
        else if (EnumCoreTrack.CORE_3_SLOPE.equals(item.getTrackType().getCoreTrack()))
        {
            facing = TCTrackDirection.ConvertDiagonalDirectionInput(MathHelper.floor_double((player.rotationYaw * 8.0F / 360.0F + 0.5D)) & 7);
            blockInfo();
            if (facing == 4 || facing == 5 || facing == 6 || facing == 7) {
                RenderTCRail.model1x3DiagonalSlope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, 0.5f, ballastMaterial, blockColour);
            }
            else {
                RenderTCRail.model1X3Slope.renderDynamic(item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, 0.5f, ballastMaterial, blockColour);
            }
        }
        else if (TCRailTypes.RailTypes.PARALLEL.equals(item.getTrackType().getRailType()))
        {
            float yaw = MathHelper.wrapAngleTo180_float(player.rotationYaw);
            boolean isLeftTurn = item.getTrackOrientation(facing, yaw).equals("left");

            String parallelCurve = "small";

            switch (item.getTrackType().getCoreTrack())
            {
                case CORE_S_CURVE_3x12:
                    parallelCurve = "medium";
                    break;
                case CORE_S_CURVE_4x16:
                    parallelCurve = "large";
                    break;
                case CORE_S_CURVE_20x2:
                    parallelCurve = "20x2";
                    break;

            }

            if (isLeftTurn)
            {
                EnumCoreTrack core = EnumCoreTrack.valueOf(item.getTrackType().getCoreTrack().name() + "_L");
                RenderTCRail.modelLeftParallelCurve.render(core, parallelCurve, item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a);
            }
            else
            {
                EnumCoreTrack core = EnumCoreTrack.valueOf(item.getTrackType().getCoreTrack().name() + "_R");
                RenderTCRail.modelRightParallelCurve.render(core, parallelCurve, item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a);
            }
        }
        else if (item.getTrackType().getRailType() == TCRailTypes.RailTypes.DIAGONALTURN) // 45 Degree Turns
        {
            if (item.getTrackOrientation(facing, MathHelper.wrapAngleTo180_float(player.rotationYaw)).equals("left"))
            {
                EnumCoreTrack core = EnumCoreTrack.valueOf(item.getTrackType().getCoreTrack().name() + "_L");
                RenderTCRail.model45DegreeLeftTurn.render(core, null, item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a);
            }
            else
            {
                EnumCoreTrack core = EnumCoreTrack.valueOf(item.getTrackType().getCoreTrack().name() + "_R");
                RenderTCRail.model45DegreeRightTurn.render(core, null, item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a);
            }
        }

        // Turns
        else if (TCRailTypes.RailTypes.TURN.equals(item.getTrackType().getRailType()))
        {
            float yaw = MathHelper.wrapAngleTo180_float(player.rotationYaw);
            boolean isLeftTurn = item.getTrackOrientation(facing, yaw).equals("left");

            if (isLeftTurn)
            {
                EnumCoreTrack core = EnumCoreTrack.valueOf(item.getTrackType().getCoreTrack().name() + "_L");
                RenderTCRail.modelLeftTurn.render(core, "", item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a);
            }
            else
            {
                EnumCoreTrack core = EnumCoreTrack.valueOf(item.getTrackType().getCoreTrack().name() + "_R");
                RenderTCRail.modelRightTurn.render(core, "", item.getTrackType().getVariant(), facing, 0, 0, 0, r, g, b, a);
            }
        }

        // switches
        else if (TCRailTypes.RailTypes.SWITCH.equals(item.getTrackType().getRailType()))
        {
            float yaw = MathHelper.wrapAngleTo180_float(player.rotationYaw);
            boolean isLeftTurn = item.getTrackOrientation(facing, yaw).equals("left");

            float dx = dir.getX();
            float dz = dir.getY();
            int out_0_start = 3;
            int out_0_end = 3;
            int facing_1 = isLeftTurn ? (facing + 4 - 1) % 4 : (facing + 1) % 4;
            int out_1_0 = 3;
            int out_1_1 = 3;
            EnumCoreTrack enumCoreTrack = item.getTrackType().getCoreTrack();
            String switchType = "medium";
            if (CORE_4x11_PARALLEL_SWITCH.equals(enumCoreTrack)) {
                switchType = "medium_parallel";
                out_0_start = 5;
                out_0_end = 10;
                out_1_0 = 10;
                out_1_1 = 3;
            }
            else if (CORE_4x17_PARALLEL_SWITCH.equals(enumCoreTrack))
            {
                switchType = "large_parallel";
                out_0_start = 0;
                out_0_end = 0;
                out_1_0 = 0;
            }
            else if (CORE_6x6_SWITCH.equals(enumCoreTrack))
            {
                switchType = "large_90";
                out_0_start = 5;
                out_0_end = 5;
                out_1_0 = 5;
                out_1_1 = 5;
            }
            else if (CORE_11x11_SWITCH.equals(enumCoreTrack))
            {
                switchType = "very_large_90";
                out_0_start = 0;
                out_0_end = 0;
                out_1_0 = 10;
                out_1_1 = 10;
            }
            else if (CORE_3x5_45DEGREE_SWITCH.equals(enumCoreTrack)) {
                switchType = "medium_45degree";
                out_0_start = 0;
                out_0_end = 0;
                out_1_0 = 0;
                out_1_1 = 0;
            }
            else if (CORE_4x8_45DEGREE_SWITCH.equals(enumCoreTrack)) {
                switchType = "large_45degree";
                out_0_start = 0;
                out_0_end = 0;
                out_1_0 = 0;
                out_1_1 = 0;
            }
            else if (CORE_10x2_CROSSOVER_SWITCH.equals(enumCoreTrack)) {
                handleCrossover(isLeftTurn, "crossover_10x2", item, facing, r, g, b, a);
                GL11.glPopMatrix();
                return;
            }

            Vector2f dir_1 = ItemTCRail.getDirectionVector(facing_1);
            String variant = item.getTrackType().getVariant().equals(RailVariants.EMBEDDED) ? "embedded" : "straight";

            float dx_1 = dir_1.getX();
            float dz_1 = dir_1.getY();

            // Render straight tracks
            for (int out_0 = out_0_start; out_0 < out_0_end + 1; out_0++) {
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), variant, facing, dx * out_0, 0, dz * out_0, r, g, b, a);
            }

            if (CORE_4x11_PARALLEL_SWITCH.equals(enumCoreTrack))
            {
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), variant, facing, 0, 0, 0, r, g, b, a);
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), variant, facing, dx * out_1_0 + dx_1 * out_1_1, 0, dz * out_1_0 + dz_1 * out_1_1, r, g, b, a);
            }
            else if (
                    !(CORE_3x5_45DEGREE_SWITCH.equals(enumCoreTrack)
                    || CORE_4x8_45DEGREE_SWITCH.equals(enumCoreTrack)
                    || CORE_4x17_PARALLEL_SWITCH.equals(enumCoreTrack)))
            {
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), variant, facing, 0, 0, 0, r, g, b, a);
                RenderTCRail.modelSmallStraight.render(item.getTrackType(), variant, facing_1, dx * out_1_0 + dx_1 * out_1_1, 0, dz * out_1_0 + dz_1 * out_1_1, r, g, b, a);
            }

            else{

            }
            // Render switch
            if (isLeftTurn) {
                RenderTCRail.modelLeftSwitchTurn.render(switchType, item.getTrackType().getVariant(), facing, false, dx, 0, dz, r, g, b, a);
            } else {
                RenderTCRail.modelRightSwitchTurn.render(switchType, item.getTrackType().getVariant(), facing, false, dx, 0, dz, r, g, b, a);
            }
        }


            GL11.glPopMatrix();
    }

    private void handleCrossover(boolean isLeftTurn, String switchType, ItemTCRail item, int facing, float r, float g, float b, float a) {
        if (isLeftTurn) {
            RenderTCRail.modelLeftSwitchTurn.render(switchType, item.getTrackType().getVariant(), facing, false, 0, 0, 0, r, g, b, a);
        } else {
            RenderTCRail.modelRightSwitchTurn.render(switchType, item.getTrackType().getVariant(), facing, false, 0, 0, 0, r, g, b, a);}

    }

    private void blockInfo()
    {
        World world = Minecraft.getMinecraft().theWorld;
        int x = Minecraft.getMinecraft().objectMouseOver.blockX;
        int y = Minecraft.getMinecraft().objectMouseOver.blockY;
        int z = Minecraft.getMinecraft().objectMouseOver.blockZ;
        Block block = world.getBlock(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);

        blockColour = block.colorMultiplier(world, x, y, z);
        IIcon icon = block.getIcon(1, metadata);
        if (icon != null && icon.getIconName() != null) {
            ballastMaterial = icon.getIconName();
        }
    }
}
