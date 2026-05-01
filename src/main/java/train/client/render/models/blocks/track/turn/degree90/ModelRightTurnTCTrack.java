package train.client.render.models.blocks.track.turn.degree90;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import train.common.items.RailVariants;
import train.common.library.track.EnumCoreTrack;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelRightTurnTCTrack extends AbstractBase90DegreeTurnTCTrack
{
	public ModelRightTurnTCTrack()
	{
		super();
	}

	private void setRotation(byte facing)
	{
		switch(facing)
		{
			case 0:
				GL11.glRotatef(-90, 0, 1, 0);
				break;
			case 1:
				GL11.glRotatef(180, 0, 1, 0);
				break;
			case 2:
				GL11.glRotatef(90, 0, 1, 0);
				break;
			default:

			break;
		}
	}

	public void render(String type, TileTCRail tcRail, double x, double y, double z) {
		render(tcRail.getTrackType().getCoreTrack(), type, tcRail.getTrackType().getVariant(), getRailDirection(tcRail), x, y, z, 1, 1, 1, 1);
	}

	public void render(EnumCoreTrack coreTrack, String type, RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		// Bind the texture, so that OpenGL properly textures our block.
		tmt.Tessellator.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variants));

		GL11.glColor4f(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);

		setRotation((byte)facing);

		switch (coreTrack)
		{
			case CORE_1X_TURN_R:
				GL11.glTranslatef(-0.5f,0,0.5f);
				render1X();
				break;
			case CORE_3X_TURN_R:
				GL11.glTranslatef(-1.0f, 0.0f, 3.0f);
				this.renderMedium();
				break;
			case CORE_5X_TURN_R:
				GL11.glTranslatef(3.5f, 0.0f, 4.5f);
				this.renderLarge();
				break;
			case CORE_10X_TURN_R:
				GL11.glTranslatef(8.5f, 0.0f, 9.50f);
				this.renderVeryLarge();
				break;
			case CORE_16X_TURN_R:
				GL11.glTranslatef(14.5f, 0.0f, 15.5f);
				this.renderSuperLarge();
				break;
			case CORE_29X_TURN_R:
				GL11.glTranslatef(27.5f,0,28.5f);
				render29X();
				break;
			case CORE_32X_TURN_R:
				GL11.glTranslatef(30.5f,0,31.5f);
				render32X();
				break;
		}

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}