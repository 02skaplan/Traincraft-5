package train.client.render.itemRender;

import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import train.common.library.track.EnumTracks;

public class ItemRenderEmbeddedStopper extends ItemRenderStopper
{
    public ItemRenderEmbeddedStopper(EnumTracks enumTrack)
    {
        super(enumTrack);
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY: {
                renderEmbeddedStopper(0.0F, 0F, 0.0F, 0.0F);
                break;
            }
            case EQUIPPED: {
                renderEmbeddedStopper(0F, 0.4F, 0F, 180.0F);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                renderEmbeddedStopper(0F, 0.4F, 0F, 180.0F);
                return;
            }
            case INVENTORY: {
                renderEmbeddedStopper(1F, 0.65F, 1F, 0.0F);
                break;
            }
            default:
                break;
        }
    }

    private void renderEmbeddedStopper(float f, float g, float h, float rotation) {
        tmt.Tessellator.bindTexture(ItemRenderAmericanStopper.texture);
        GL11.glPushMatrix(); //start
        GL11.glTranslatef(f, g, h); //size
        GL11.glRotatef(rotation, f, g, h);
        ItemRenderAmericanStopper.americanStopper.renderInHand(0.0625F, EnumTracks.SMALL_STRAIGHT);
        GL11.glPopMatrix(); //end
    }
}