package train.client.render.models.blocks.track.turn.degree45;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import org.lwjgl.opengl.GL11;
import train.common.items.RailVariants;
import train.common.library.track.EnumCoreTrack;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)

public class ModelLeft45DegreeTurnTCTrack extends AbstractBase45DegreeTurnTCTrack
{
    public ModelLeft45DegreeTurnTCTrack()
    {
        if (!baked)
        {
            String ROTATION = "left";

            model3x4_45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/45-deg/3x4_" + ROTATION + ".obj"));
            list3x4_45DegreeTurn = GL11.glGenLists(1);
            GL11.glNewList(list3x4_45DegreeTurn, GL11.GL_COMPILE);
            model3x4_45DegreeTurn.renderAll();
            GL11.glEndList();

            model3x6_45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/45-deg/3x6_" + ROTATION + ".obj"));
            list3x6_45DegreeTurn = GL11.glGenLists(1);
            GL11.glNewList(list3x6_45DegreeTurn, GL11.GL_COMPILE);
            model3x6_45DegreeTurn.renderAll();
            GL11.glEndList();

            model4x8_45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/45-deg/4x8_" + ROTATION + ".obj"));
            list4x8_45DegreeTurn = GL11.glGenLists(1);
            GL11.glNewList(list4x8_45DegreeTurn, GL11.GL_COMPILE);
            model4x8_45DegreeTurn.renderAll();
            GL11.glEndList();

            model5x11_45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/45-deg/5x11_" + ROTATION + ".obj"));
            list5x11_45DegreeTurn = GL11.glGenLists(1);
            GL11.glNewList(list5x11_45DegreeTurn, GL11.GL_COMPILE);
            model5x11_45DegreeTurn.renderAll();
            GL11.glEndList();

            model9x20_45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/45-deg/9x20_" + ROTATION + ".obj"));
            list9x20_45DegreeTurn = GL11.glGenLists(1);
            GL11.glNewList(list9x20_45DegreeTurn, GL11.GL_COMPILE);
            model9x20_45DegreeTurn.renderAll();
            GL11.glEndList();

            model10x22_45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track/curve/45-deg/10x22_" + ROTATION + ".obj"));
            list10x22_45DegreeTurn = GL11.glGenLists(1);
            GL11.glNewList(list10x22_45DegreeTurn, GL11.GL_COMPILE);
            model10x22_45DegreeTurn.renderAll();
            GL11.glEndList();

            baked = true;
        }
    }

    public void render(String turnSize, TileTCRail tcRail, double x, double y, double z)
    {
        render(tcRail.getTrackType().getCoreTrack(), turnSize, tcRail.getTrackType().getVariant(), tcRail.getWorldObj().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord), x, y, z, 1, 1, 1, 1);
    }

    public void render(EnumCoreTrack coreTrack, String turnSize, RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {

        // Bind the texture, so that OpenGL properly textures our block.
        tmt.Tessellator.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variant));

        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

        GL11.glColor4f(r, g, b, a);
        //GL11.glScalef(0.5f, 0.5f, 0.5f);

        switch (facing)
        {
            case 0:
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
            case 1:
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
            case 2:
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
            case 3:
                GL11.glRotatef(-90, 0, 1, 0);
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
        }

        switch (coreTrack)
        {
            case CORE_3X4_45DEGREE_TURN_L:
                this.render3x4();
                break;
            case CORE_3X6_45DEGREE_TURN_L:
                this.render3x6();
                break;
            case CORE_4X8_45DEGREE_TURN_L:
                this.render4x8();
                break;
            case CORE_5X11_45DEGREE_TURN_L:
                this.render5x11();
                break;
            case CORE_9X20_45DEGREE_TURN_L:
                this.render9x20();
                break;
            case CORE_10x22_45DEGREE_TURN_L:
                this.render10x22();
                break;
        }

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
}
