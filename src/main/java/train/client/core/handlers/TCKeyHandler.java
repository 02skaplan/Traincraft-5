package train.client.core.handlers;

import com.jcirmodelsquad.tcjcir.features.geometry.GuiGeometryCar;
import com.jcirmodelsquad.tcjcir.vehicles.rollingstock.misc.ExperimentalGeometryCar;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxmods.unifiedcontrols.client.SharedKeyState;
import foxmods.unifiedcontrols.client.UnifiedKeyRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Keyboard;
import train.client.gui.GuiMTCInfo;
import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.api.Locomotive;
import train.common.api.SteamTrain;
import train.common.core.handlers.ConfigHandler;
import train.common.core.network.PacketKeyPress;

import java.security.Key;

public class TCKeyHandler {
	public static KeyBinding up;
	public static KeyBinding down;
	public static KeyBinding furnace;
	public static KeyBinding MTCScreen;
	public static KeyBinding toggleATO;
	public static KeyBinding mtcOverride;
	public static KeyBinding overspeedOverride;
	public static KeyBinding remoteControlForward;
	public static KeyBinding remoteControlBackwards;
	public static KeyBinding remoteControlHorn;
	public static KeyBinding remoteControlBrake;

	public long bellTimerMillis = System.currentTimeMillis();
	public long storedMillis = System.currentTimeMillis();

	public TCKeyHandler() {

		up = new KeyBinding("key.traincraft.up", Keyboard.KEY_NONE, "key.categories.traincraft");
		ClientRegistry.registerKeyBinding(up);
		down = new KeyBinding("key.traincraft.down", Keyboard.KEY_NONE, "key.categories.traincraft");
		ClientRegistry.registerKeyBinding(down);
		furnace = new KeyBinding("key.traincraft.furnace", Keyboard.KEY_F, "key.categories.traincraft");
		ClientRegistry.registerKeyBinding(furnace);


			MTCScreen = new KeyBinding("key.traincraft.showMTCScreen", Keyboard.KEY_NONE, "key.categories.traincraft");
			ClientRegistry.registerKeyBinding(MTCScreen);
			toggleATO = new KeyBinding("key.traincraft.toggleATO", Keyboard.KEY_NONE, "key.categories.traincraft");
			ClientRegistry.registerKeyBinding(toggleATO);
			mtcOverride = new KeyBinding("key.traincraft.mtcOverride", Keyboard.KEY_NONE, "key.categories.traincraft");
			ClientRegistry.registerKeyBinding(mtcOverride);
			overspeedOverride = new KeyBinding("key.traincraft.overspeedOverride", Keyboard.KEY_NONE, "key.categories.traincraft");
			ClientRegistry.registerKeyBinding(overspeedOverride);

		remoteControlForward = new KeyBinding("Remote Control Forward", Keyboard.KEY_NUMPAD8, "key.categories.traincraft");
        remoteControlBackwards = new KeyBinding("Remote Control Backwards", Keyboard.KEY_NUMPAD8, "key.categories.traincraft");
        remoteControlBrake = new KeyBinding("Remote Control Brake", Keyboard.KEY_NUMPAD0, "key.categories.traincraft");
        remoteControlHorn = new KeyBinding("Remote Control Horn", Keyboard.KEY_NUMPADENTER, "key.categories.traincraft");

        ClientRegistry.registerKeyBinding(remoteControlForward);
        ClientRegistry.registerKeyBinding(remoteControlBackwards);
        ClientRegistry.registerKeyBinding(remoteControlBrake);
        ClientRegistry.registerKeyBinding(remoteControlHorn);

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientKeyPress(TickEvent.ClientTickEvent event)
	{
		if(event.phase == TickEvent.Phase.END) return;
		if(Minecraft.getMinecraft().currentScreen != null) return;
		if(!(Minecraft.getMinecraft().thePlayer.ridingEntity instanceof AbstractTrains)) return;

		SharedKeyState.poll();

		if (!Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen())
		{
			if (SharedKeyState.gui()) {
				sendKeyControlsPacket(7);
				if (Minecraft.getMinecraft().thePlayer.ridingEntity != null && Minecraft.getMinecraft().thePlayer.ridingEntity instanceof ExperimentalGeometryCar) {
					Minecraft.getMinecraft().displayGuiScreen(new GuiGeometryCar(Minecraft.getMinecraft().thePlayer));
				}
			}

			if (SharedKeyState.horn()) {
				sendKeyControlsPacket(8);
			}

			if (SharedKeyState.sound_2()) {
				if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof Locomotive) {
					if(bellTimerMillis+ 1000 <System.currentTimeMillis()){//15000 for 15 seconds
						bellTimerMillis=System.currentTimeMillis();
					}
				}
				sendKeyControlsPacket(10);
			}

			if (SharedKeyState.brake()) {
				sendKeyControlsPacket(6);
			}
		}
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (!Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()) {
			if (up.getIsKeyPressed()) {
				sendKeyControlsPacket(0);
			}
			if (down.getIsKeyPressed()) {
				sendKeyControlsPacket(2);
			}

			/*if (lampControl.isPressed()) {
				if (Minecraft.getMinecraft().thePlayer.ridingEntity != null && Minecraft.getMinecraft().thePlayer.ridingEntity instanceof Locomotive) {
					Locomotive train = (Locomotive) Minecraft.getMinecraft().thePlayer.ridingEntity;

					if (train.lampOn) {
						train.lampOn = true;
						//((EntityPlayer) train.riddenByEntity).addChatMessage(new ChatComponentText("lampOn true"));
						//System.out.println(train.lampOn);
					} else {
						train.lampOn = false;
					}
					//train.lightsOn = train.lightsOn * -1;
					//train.lampOn = !train.lampOn;
					//System.out.println(train.lampOn);
					//lamp is t/f light is number
				}
				sendKeyControlsPacket(19);
			}*/
			/*if (lampControl.isPressed()) {//TODO: make lights work eventually
				/*if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof Locomotive) {
					if(storedMillis+ 1000 <System.currentTimeMillis()){//15000 for 15 seconds
						storedMillis=System.currentTimeMillis();
					}
				}
				sendKeyControlsPacket(19);
			}*/

			/*if (bell.isPressed()) {
				if (Minecraft.getMinecraft().thePlayer.ridingEntity != null && Minecraft.getMinecraft().thePlayer.ridingEntity instanceof Locomotive) {
					Locomotive train = (Locomotive) Minecraft.getMinecraft().thePlayer.ridingEntity;
						if(bellTimerMillis+ 1000 <System.currentTimeMillis()){//15000 for 15 seconds
							bellTimerMillis=System.currentTimeMillis();
							train.bellPressed=!train.bellPressed;

						}
					//train.bellPressed=!train.bellPressed;
					if (train.bellPressed) {
						train.bellPressed = true;//BELLPRESSED NEEDS TO BE TRUE
						System.out.println(true);// WHY AREYOUNT TRUE
					} else {
						train.bellPressed = false;
						System.out.println(false);
					}
				}
				sendKeyControlsPacket(10);
			}*/



			if (furnace.isPressed()) {
				sendKeyControlsPacket(9);
			}

				if (MTCScreen.isPressed() && !FMLClientHandler.instance().isGUIOpen(GuiMTCInfo.class)) {
					if (Minecraft.getMinecraft().thePlayer.ridingEntity != null) {
						Minecraft.getMinecraft().displayGuiScreen(new GuiMTCInfo(Minecraft.getMinecraft().thePlayer.ridingEntity));
					}
				}
				if (toggleATO.isPressed() && Minecraft.getMinecraft().thePlayer.ridingEntity instanceof Locomotive) {
					sendKeyControlsPacket(16);
					Locomotive train = (Locomotive) Minecraft.getMinecraft().thePlayer.ridingEntity;
					if (train.mtcStatus != 0 && train.mtcType == 2) {
						if (train instanceof SteamTrain && !ConfigHandler.ALLOW_ATO_ON_STEAMERS) {
							((EntityPlayer) train.riddenByEntity).addChatMessage(new ChatComponentText("Automatic Train Operation cannot be used with steam trains"));
						} else {
							if (train.atoStatus == 1) {
								train.atoStatus = 0;
							} else {
								train.atoStatus = 1;
							}
						}

					} else {
						((EntityPlayer) train.riddenByEntity).addChatMessage(new ChatComponentText("Automatic Train Operation can only be activated when you are using W-MTC"));
					}

				}


				if (mtcOverride.isPressed() && Minecraft.getMinecraft().thePlayer.ridingEntity instanceof Locomotive) {
					Locomotive train = (Locomotive) Minecraft.getMinecraft().thePlayer.ridingEntity;


					if (train.mtcOverridePressed) {
						train.mtcOverridePressed = false;
						((EntityPlayer) train.riddenByEntity).addChatMessage(new ChatComponentText("MTC has been enabled and will re-activate when the system receives new data"));
					} else {
						train.mtcOverridePressed = true;
						((EntityPlayer) train.riddenByEntity).addChatMessage(new ChatComponentText("MTC has been disabled and will not receive speed changes or transmit MTC data"));
						train.mtcStatus = 0;
						train.speedLimit = 0;
						train.nextSpeedLimit = 0;
						train.speedChange3 = Vec3.createVectorHelper(0,0,0);
						train.stopPoint3 = Vec3.createVectorHelper(0,0,0);

						train.trainLevel = 0;

					}
					sendKeyControlsPacket(17);
				}
				if (overspeedOverride.isPressed() && Minecraft.getMinecraft().thePlayer.ridingEntity instanceof Locomotive) {
					Locomotive train = (Locomotive) Minecraft.getMinecraft().thePlayer.ridingEntity;
					sendKeyControlsPacket(18);
					if (train.mtcStatus == 1 | train.mtcStatus == 2) {
						train.overspeedOveridePressed = !train.overspeedOveridePressed;
					}
				}

			}



		if (FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.isPressed() && Keyboard.isKeyDown(Keyboard.KEY_F3)) {
			sendKeyControlsPacket(404);
		}
	}


	
	private static void sendKeyControlsPacket(int key)
	{
		Traincraft.keyChannel.sendToServer(new PacketKeyPress(key));
	}
}