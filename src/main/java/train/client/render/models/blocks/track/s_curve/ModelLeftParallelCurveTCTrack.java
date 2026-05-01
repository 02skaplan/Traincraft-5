package train.client.render.models.blocks.track.s_curve;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import train.common.items.RailVariants;
import train.common.library.track.EnumCoreTrack;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelLeftParallelCurveTCTrack extends AbstractSCurve
{
    public ModelLeftParallelCurveTCTrack()
    {
        if (!baked)
        {
            model2x8SCurve = net.minecraftforge.client.model.AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/s/2x8_left.obj"));
            list2x8SCurve = GL11.glGenLists(1);
            GL11.glNewList(list2x8SCurve, GL11.GL_COMPILE);
            model2x8SCurve.renderAll();
            GL11.glEndList();

            model3x12SCurve = net.minecraftforge.client.model.AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/s/3x12_left.obj"));
            list3x12SCurve = GL11.glGenLists(1);
            GL11.glNewList(list3x12SCurve, GL11.GL_COMPILE);
            model3x12SCurve.renderAll();
            GL11.glEndList();

            model4x16SCurve = net.minecraftforge.client.model.AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/s/4x16_left.obj"));
            list4x16SCurve = GL11.glGenLists(1);
            GL11.glNewList(list4x16SCurve, GL11.GL_COMPILE);
            model4x16SCurve.renderAll();
            GL11.glEndList();

            model2x20SCurve = net.minecraftforge.client.model.AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/s/20x2_left.obj"));
            list2x20SCurve = GL11.glGenLists(1);
            GL11.glNewList(list2x20SCurve, GL11.GL_COMPILE);
            model2x20SCurve.renderAll();
            GL11.glEndList();

            baked = true;
        }
    }

    public void render(String type, TileTCRail tcRail, double x, double y, double z) {
        int facing = tcRail.getWorldObj().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord);
        render(tcRail.getTrackType().getCoreTrack(), type, tcRail.getTrackType().getVariant(), facing, x, y, z, 1, 1, 1, 1 );
    }

    private void setRotation(byte facing)
    {
        switch(facing)
        {
            case 0:
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glTranslatef(0, 0.0f, -2.0f);
                break;
            case 1:
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glTranslatef(-2f, 0.0f, 0f);
                break;
            case 3:
                GL11.glRotatef(-90, 0, 1, 0);
                GL11.glTranslatef(2.0f, 0.0f, 0);
                break;
            default:
                GL11.glTranslatef(0, 0.0f, 2.0f);
            break;
        }
    }

    public void render(EnumCoreTrack core, String type, RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {

        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z - 1.5f);

        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variants));

        GL11.glColor4f(r, g, b, a);
        //GL11.glScalef(0.5f, 0.5f, 0.5f);
        /** where l = 0 is SOUTH
         *        l = 1 is WEST
         *        l = 2 is NORTH
         *        l = 3 is EAST
         */
        setRotation((byte)facing);

        switch (type)
        {
            case "small":
                this.render2x8SCurve();
                break;
            case "medium":
                this.render3x12SCurve();
                break;
            case "large":
                this.render4x16SCurve();
                break;
            case "20x2":
                this.render2x20SCurve();
                break;
        }

        GL11.glPopMatrix();
    }

}