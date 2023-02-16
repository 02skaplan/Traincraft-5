package train.common.library;

import com.jcirmodelsquad.tcjcir.vehicles.locomotives.*;
import com.jcirmodelsquad.tcjcir.vehicles.rollingstock.*;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import train.common.api.AbstractTrains;
import train.common.core.util.TraincraftUtil;
import train.common.entity.rollingStock.*;

import java.lang.reflect.InvocationTargetException;

public enum EnumTrains {

	/**
	 * Passengers
	 */
	passengerCartBlue("Passenger Blue", EntityPassengerBlue.class, ItemIDs.minecartPassengerBlue.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Red", "Green", "White", "Black", "Cyan", "Orange", "Grey", "LightGrey", "Lime"}), 18, 0),
	passengerCartBlackSmall("Passenger Small Black", EntityPassenger2.class, ItemIDs.minecartPassenger2.item, "passenger", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
	passengerLongGreen("Passenger Green Long", EntityPassenger5.class, ItemIDs.minecartPassenger5.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Green", "Yellow"}), 18, 0),
	passengerShortGreen("Passenger Short Green", EntityPassenger7.class, ItemIDs.minecartPassenger7.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
	passenger_1class_DB("Passenger 1Class DB", EntityPassenger_1class_DB.class, ItemIDs.minecartPassenger8_1class_DB.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
	passenger_2class_DB("Passenger 2Class DB", EntityPassenger_2class_DB.class, ItemIDs.minecartPassenger9_2class_DB.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
	passengerHighSpeedZeroED("Passenger High Speed Zero ED", EntityPassengerHighSpeedCarZeroED.class, ItemIDs.minecartPassengerHighSpeedCarZeroED.item, "passenger", 0, 0, 2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"White", "LightGrey", "Grey"}), 18, 0),
	passengerTramNY("Passenger Tram NY", EntityPassengerTramNY.class, ItemIDs.minecartPassengerTramNY.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
	passengerAdler("Passenger Adler", EntityPassengerAdler.class, ItemIDs.minecartPassengerAdler.item, "passenger", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
	passengerDBOriental("Passenger DB oriental", EntityPassengerDBOriental.class, ItemIDs.minecartPassengerDBOriental.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Blue", "White"}), 18, 0),
	passengerIC4_DSB_FG("Passenger IC4 DSB FG", PassengerIC4_DSB_FG.class, ItemIDs.minecartIC4_DSB_FG.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 10, 0, (new String[]{"Unfinished, Creative Only"})),
	passengerIC4_DSB_FH("Passenger IC4 DSB FH", PassengerIC4_DSB_FH.class, ItemIDs.minecartIC4_DSB_FH.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 10, 0, (new String[]{"Unfinished, Creative Only"})),
	passengerICE1_Class1("Passenger ICE 1st Class", EntityPassengerICE_1class.class, ItemIDs.minecartICE1_1stClass.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 10, 0),
	passengerICE1_Class2("Passenger ICE 2nd Class", EntityPassengerICE_2class.class, ItemIDs.minecartICE1_2ndClass.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 10, 0),
	passengerICE1_Restaurant("ICE Restaurant", EntityPassengerICE_Restaurant.class, ItemIDs.minecartICE1_Restaurant.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 10, 0),
	passengerGS4("Passenger GS4", EntityPassengerGS4.class, ItemIDs.minecartGS4_Passenger.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "White", "Yellow", "Brown", "Green", "Lime", "Red", "Grey", "Green", "LightGrey", "Blue", "Black", "LightBlue"}), 10, 0),
	passengerGS4Observatory("Passenger GS4 Observatory", EntityPassengerGS4_Observatory.class, ItemIDs.minecartGS4_Observatory.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "White", "Yellow", "Brown", "Lime", "Red", "Grey", "Green", "LightGrey", "Black", "LightBlue"}), 10, 0),
	passengerGS4_Tail("Passenger GS4 Tail", EntityPassengerGS4_Tail.class, ItemIDs.minecartGS4_Tail.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "White", "Yellow", "Brown", "Lime", "Red", "Grey", "Green", "LightGrey", "Black", "LightBlue"}), 10, 0),

	passengerDenverRioGrange("Passenger Denver Rio Grande", EntityPassengerDenverRioGrande.class, ItemIDs.minecartDenverRioGrandePassenger.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Red", "Green"}), 10, 0),
	passengerDenverRioGrandeCombo("Passenger Denver Rio Grande Combo", EntityPassengerDenverRioGrandeCombo.class, ItemIDs.minecartDenverRioGrandeCombo.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Red", "Green"}), 10, 0),

	passengerRheingold("Passenger Rheingold", EntityPassengerRheingold.class, ItemIDs.minecartPassengerRheingold.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Red", "Green", "LightBlue", "Magenta", "Lime", "Cyan"}), 18, 0),
	passengerRheingoldDining1("Rheingold Dining", EntityPassengerRheingoldDining1.class, ItemIDs.minecartPassengerRheingoldDining1.item, "work", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Red", "Green", "LightBlue", "Magenta", "Lime"}), 18, 0),
	passengerRheingoldDining2("Rheingold Dining Alternate", EntityPassengerRheingoldDining2.class, ItemIDs.minecartPassengerRheingoldDining2.item, "work", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Red", "Green", "LightBlue", "Magenta", "Lime"}), 18, 0),
	passengerRheingoldPanorama("Rheingold Panorama", EntityPassengerRheingoldPanorama.class, ItemIDs.minecartPassengerRheingoldPanorama.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Red"}), 18, 0),

	passengerMILW("Passenger MILW", EntityPassengerMILW.class, ItemIDs.minecartPassengerMILW.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
	passengerMILWTail("Passenger MILW Tail", EntityPassengerMILWTail.class, ItemIDs.minecartPassengerMILWTail.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
	//passengerMILWDiner("Passenger MILW Diner",EntityPassengerMILWDiner.class, ItemIDs.minecartPassengerMILWDiner.item,"passenger",0,0,1,0,0,0,0,0,0,null,18,0),

	passengerBamboo("Bamboo Flatcar Passenger", EntityPassengerBamboo.class, ItemIDs.minecartPassengerBamboo.item, "passenger", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey"}), 18, 0),


	/**
	 * Caboose
	 */
	cabooseRed("Caboose Red", EntityCaboose.class, ItemIDs.minecartCaboose.item, "caboose", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
	cabooseBlack("Caboose Black", EntityCaboose3.class, ItemIDs.minecartCaboose3.item, "caboose", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),

	/**
	 * Specials
	 **/
	GWRBrakeVan("GWR Toad Brake Van", EntityGWRBrakeVan.class, ItemIDs.minecartGWRBrakeVan.item, "work", 0, 0, 0.7, 0, 0, 0, 0, 0, 0, null, 18, 0),
	stockCar("Stock Cart", EntityStockCar.class, ItemIDs.minecartStockCar.item, "stock car", 0, 0, 2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Red", "Yellow", "White"}), 18, 0),
	drwgStockCar("DRWG Stock Cart", EntityStockCarDRWG.class, ItemIDs.minecartDRWGStockCar.item, "stock car", 0, 0, 2, 0, 0, 0, 0, 0, 0, null, 18, 0),
	workCart("Work Cart Yellow", EntityWorkCart.class, ItemIDs.minecartWork.item, "work", 0, 0, 0.7, 0, 0, 0, 0, 0, 0, null, 18, 0),
	flatCart("Flat Cart", EntityFlatCart.class, ItemIDs.minecartFlatCart.item, "flat", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, null, 18, 0),
	workCaboose("Work Caboose", EntityCabooseWorkCart.class, ItemIDs.minecartCabooseWork.item, "work", 0, 0, 0.6, 0, 0, 0, 0, 0, 0, null, 18, 0),
	cabooseLogging("Caboose Logging", EntityCabooseLogging.class, ItemIDs.minecartCabooseLogging.item, "work", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Red", "Cyan", "Grey"}), 18, 0),
	cabooseLoggingPRR("PRR Caboose Logging", EntityCabooseLoggingPRR.class, ItemIDs.minecartCabooseLoggingPRR.item, "work", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Red", "Blue", "Green", "Orange"}), 18, 0),
	mailWagen_DB("Mail Wagon DB", EntityMailWagen_DB.class, ItemIDs.minecartMailWagon_DB.item, "work", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
	jukeBoxCart("JukeBox Cart", EntityJukeBoxCart.class, ItemIDs.minecartJukeBoxCart.item, "special", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, null, 18, 0),
	flatCartSU("Flat Cart SU", EntityFlatCartSU.class, ItemIDs.minecartFlatCartSU.item, "flat", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, null, 18, 0),
	flatCartUS("Flat Cart US", EntityFlatCartUS.class, ItemIDs.minecartFlatCartUS.item, "flat", 0, 0, 0.4, 0, 0, 0, 0, 0, 0, null, 18, 0),
	tracksBuilder("Tracks Builder", EntityTracksBuilder.class, ItemIDs.minecartBuilder.item, "special", 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 14, 0),
	flatCar_DB("Flat Cart DB", EntityFlatCar_DB.class, ItemIDs.minecartFlatCart_DB.item, "flat", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Red", "Green"}), 18, 0),
	BUnitEMDF7("EMD F7 B Unit", EntityBUnitEMDF7.class, ItemIDs.minecartLocoEMDF7B.item, "b-unit", 0, 0, 5, 0, 0, 0, 0, 0, 12000, TraincraftUtil.getBytesFromColors(new String[]{"Black", "Lime", "Red", "Brown", "Green", "Orange", "Yellow"}), 18, 0, (new String[]{"Reduces weight carried by 50 tons when fueled"})),
	BUnitEMDF3("EMD F3 B Unit", EntityBUnitEMDF3.class, ItemIDs.minecartLocoEMDF3B.item, "b-unit", 0, 0, 5, 0, 0, 0, 0, 0, 12000, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Black", "Orange", "Blue", "Brown", "Green", "Magenta"}), 18, 0, (new String[]{"Reduces weight carried by 50 tons when fueled"})),
	BUnitDD35("DD35 B Unit", EntityBUnitDD35.class, ItemIDs.minecartLocoDD35B.item, "b-unit", 0, 0, 8, 0, 0, 0, 0, 0, 12000, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "Black"}), 18, 0, (new String[]{"Reduces weight carried by 80 tons when fueled"})),
	propagandaUs("Propaganda USA", EntityPropagandaUS.class, ItemIDs.minecartPropagandaUs.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "White", "Red", "Green"}), 14, 0),
	propagandaUSSR("Propaganda USSR", EntityPropagandaUSSR.class, ItemIDs.minecartPropagandaUSSR.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "White", "Red"}), 14, 0),
	propagandaJapan("Propaganda Japan", EntityPropagandaJapan.class, ItemIDs.minecartPropagandaJapan.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Red", "White", "Yellow"}), 14, 0),
	propagandaBritain("Propaganda Britain", EntityPropagandaBritain.class, ItemIDs.minecartPropagandaBritish.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "White", "Yellow"}), 14, 0),

	/**
	 * Freight
	 */
	freightCartRed("Freight Cart Red", EntityFreightCart2.class, ItemIDs.minecartFreightCart2.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "White", "Orange"})), 18, 36,(new String[]{"Cargo: any"})),
	freightCartYellow("Freight Cart Yellow", EntityFreightCart.class, ItemIDs.minecartChest.item, "freight", 3, null, 18, 36,(new String[]{"Cargo: any"})),
	freightWood("Freight Flat Cart Log", EntityFreightWood.class, ItemIDs.minecartWood.item, "freight", 3, null, 18, 27, (new String[]{"Cargo: only Logs"})),
	freightHopper("Freight Hopper Green", EntityFreightGrain.class, ItemIDs.minecartGrain.item, "freight", 4, (TraincraftUtil.getBytesFromColors(new String[]{"Green", "White"})), 18, 36, (new String[]{"Cargo: wheat, seeds"})),
	freightKClassRailBox("Freight K Class Rail Box", EntityFreightKClassRailBox.class, ItemIDs.minecartKClassRailBox.item, "freight", 4, (TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Orange"})), 18, 36,(new String[]{"Fictional. Cargo: any"})),
	freightShortCoveredHopper("Freight Short Covered Hopper", EntityFreightShortCoveredHopper.class, ItemIDs.minecartShortCoveredHopper.item, "freight", 4, (TraincraftUtil.getBytesFromColors(new String[]{"Grey", "Orange", "LightBlue", "Lime", "Yellow", "Blue", "Cyan", "Skin16"})), 18, 36,(new String[]{"Cargo: any"})),
	freightLongCoveredHopper("Freight Long Covered Hopper", EntityFreightLongCoveredHopper.class, ItemIDs.minecartLongCoveredHopper.item, "freight", 6, (TraincraftUtil.getBytesFromColors(new String[]{"LightGrey", "Grey","Pink", "White", "Green", "Orange", "Lime", "Cyan"})), 18, 54,(new String[]{"Cargo: any"})),
	freightOpenWagon("Freight Open Wagon", EntityFreightOpenWagon.class, ItemIDs.minecartOpenWagon.item, "freight", 2, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Brown", "Pink"})), 18, 36, (new String[]{"Cargo: blocks, vanilla items"})),//"train_hopper" for open wagon => weird
	freightHopperUS("Freight Hopper US", EntityFreightHopperUS.class, ItemIDs.minecartFreightHopperUS.item, "freight", 4, (TraincraftUtil.getBytesFromColors(new String[]{"Brown", "Grey", "LightGrey", "Blue", "Red", "Yellow", "Black", "LightBlue", "Purple", "Green", "Magenta", "Orange","Skin17"})), 18, 27, (new String[]{"Cargo: blocks"})),
	freight100TonHopper("Freight 100 Ton Hopper", EntityFreight100TonHopper.class, ItemIDs.minecartFreight100TonHopper.item, "freight", 4, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Black", "Blue", "Grey", "LightGrey", "Brown"})), 18, 54, (new String[]{"Cargo: blocks"})),
	flatCartWoodUS("Freight Flat Cart Wood US", EntityFlatCartWoodUS.class, ItemIDs.minecartFlatCartWoodUS.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "White"})), 18, 27, (new String[]{"Cargo: wood stuff"})),
	bulkheadFlatCartWood("Freight Bulkhead Flat Cart", EntityBulkheadFlatCart.class, ItemIDs.minecartBulkheadFlatCart.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Brown", "Yellow", "Green"})), 18, 27, (new String[]{"Cargo: only planks"})),
	freightCartUS("Freight Cart US", EntityFreightCartUS.class, ItemIDs.minecartFreightCartUS.item, "freight", 3.5, (TraincraftUtil.getBytesFromColors(new String[]{"Brown", "Yellow", "Black", "Blue", "Cyan", "Green", "Grey", "LightBlue", "LightGrey", "Lime", "Magenta", "Orange", "Pink", "Purple", "Red", "White"})), 18, 36,(new String[]{"Cargo: blocks, vanilla items"})),
	freightBoxCartUS("Freight Box Cart US", EntityBoxCartUS.class, ItemIDs.minecartBoxCartUS.item, "freight", 2, (TraincraftUtil.getBytesFromColors(new String[]{"Brown", "Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Purple", "Orange", "Grey"})), 18, 45,(new String[]{"Cargo: any"})),
	freightBoxCartPRR("Freight Box Cart PRR", EntityBoxCartPRR.class, ItemIDs.minecartBoxCartPRR.item, "freight", 2, null, 18, 45,(new String[]{"Cargo: any"})),
	freightCartSmall("Freight Cart Small", EntityFreightCartSmall.class, ItemIDs.minecartFreightCartSmall.item, "freight", 1, null, 18, 36,(new String[]{"Cargo: any"})),
	freightMinetrain("Freight Minecart Yellow", EntityFreightMinetrain.class, ItemIDs.minecartMineTrain.item, "freight", 0.5, null, 18, 18, (new String[]{"Cargo: opaque blocks"})),
	freightGTNG("Freight GTNG Ore Wagon", EntityFreightGTNG.class, ItemIDs.minecartFreightGTNG.item, "freight", 0.5, null, 18, 18,(new String[]{"Cargo: opaque blocks"})),
	flatCartWoodLogs("Freight Flat Logs", EntityFreightWood2.class, ItemIDs.minecartFreightWood2.item, "freight", 3, null, 18, 18, (new String[]{"Cargo: only logs"})),
	freightClosedRedBrown("Freight Cart Closed RedBrown", EntityFreightClosed.class, ItemIDs.minecartFreightClosed.item, "freight", 2.5, null, 18, 36,(new String[]{"Cargo: any"})),
	freightOpenRedBrown("Freight Open RedBrown", EntityFreightOpen2.class, ItemIDs.minecartFreightOpen2.item, "freight", 5, null, 18, 21,(new String[]{"Cargo: any"})),
	freightWagen_DB("Freight Wagon DB", EntityFreightWagenDB.class, ItemIDs.minecartFreightWagon_DB.item, "freight", 4, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Green", "Yellow"})), 18, 54,(new String[]{"Cargo: any"})),
	flatCarRails_DB("Freight Flat Cart Rails DB", EntityFlatCarRails_DB.class, ItemIDs.minecartFlatCartRail_DB.item, "freight", 5, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Green"})), 18, 36, (new String[]{"Cargo: only rails"})),
	ASTFAutorack("Freight ASTF Autorack", EntityFreightASTFAutorack.class, ItemIDs.minecartASTFAutorack.item, "freight", 5, null, 18, 36, (new String[]{"Cargo: any | Stack limit 1"})),
	flatCarLogs_DB("Freight Flat Cart Logs DB", EntityFlatCarLogs_DB.class, ItemIDs.minecartFlatCartLogs_DB.item, "freight", 4, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Green"})), 18, 45, (new String[]{"Cargo: only logs"})),
	slateWagon("Freight Slate Wagon", EntityFreightSlateWagon.class, ItemIDs.minecartSlateWagon.item, "freight", 0.5, null, 18, 38, (new String[]{"Cargo: only stone and ores"})),
	iceWagon("Freight Ice Wagon", EntityFreightIceWagon.class, ItemIDs.minecartIceWagon.item, "freight", 0.5, null, 18, 38, (new String[]{"Cargo: only ice"})),
	freightCartGS4("Freight Cart GS4 Baggage", EntityFreightGS4_Baggage.class, ItemIDs.minecartGS4_Baggage.item, "freight", 1, (TraincraftUtil.getBytesFromColors(new String[]{"Orange", "White", "Yellow", "Brown", "Lime", "Red", "Grey", "Green", "LightGrey", "Black", "LightBlue"})), 18, 45,(new String[]{"Cargo: any"})),
	freightGondola_DB("Freight Gondola DB", EntityFreightGondola_DB.class, ItemIDs.minecartFreightGondola_DB.item, "freight", 3.5, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Green"})), 18, 45, (new String[]{"Cargo: blocks, vanilla items"})),
	freightCenterBeam_Empty("Freight Center Beam Empty", EntityFreightCenterbeam_Empty.class, ItemIDs.minecartFreightCenterBeam_Empty.item, "freight", 0.5, (TraincraftUtil.getBytesFromColors(new String[]{"Grey", "LightGrey"})), 18, 54,(new String[]{"Cargo: any"})),
	freightCenterBeam_Wood1("Freight Center Beam Wood1", EntityFreightCenterbeam_Wood_1.class, ItemIDs.minecartFreightCenterBeam_Wood_1.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Brown", "Blue", "White"})), 18, 54, (new String[]{"Cargo: wood stuff"})),
	freightCenterBeam_Wood2("Freight Center Beam Wood2", EntityFreightCenterbeam_Wood_2.class, ItemIDs.minecartFreightCenterBeam_Wood_2.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Brown", "Yellow", "White"})), 18, 54, (new String[]{"Cargo: wood stuff"})),
	//freightWellcar("Freight Well Car",EntityFreightWellcar.class, ItemIDs.minecartFreightWellcar.item,"freight",3,TraincraftUtil.getBytesFromColors(new String[] {"Blue","Red","Green","LightGrey","Grey", "Yellow"}),18,54,(new String[] {"Cargo: any"})),
	freightWellcar("Freight Well Car", EntityFreightWellcar.class, ItemIDs.minecartFreightWellcar.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Red", "Green", "Black", "Grey", "Cyan", "Brown", "Lime", "LightBlue", "LightGrey", "Magenta", "Orange", "Pink", "Purple", "White", "Yellow"})), 18, 54,(new String[]{"Cargo: any"})),
	freightTrailer("Freight Trailer", EntityFreightTrailer.class, ItemIDs.minecartFreightTrailer.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Yellow", "LightBlue", "Red", "Grey", "LightGrey", "Magenta", "Orange", "Pink", "Purple", "Lime", "White", "Green"})), 18, 54,(new String[]{"Cargo: any"})),
	freightDenverRioGrange("Denver Rio Grande Baggage", EntityFreightDenverRioGrande.class, ItemIDs.minecartDenverRioGrandeBaggage.item, "freight", 0.5, (TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Red", "Green"})), 18, 54,(new String[]{"Cargo: any"})),
	freightMILWBaggage("MILW Baggage", EntityFreightBaggageMILW.class, ItemIDs.minecartBaggageMILW.item, "freight", 0.5, null, 18, 54,(new String[]{"Cargo: any"})),
	freightheavyweight("Heavyweight Freight Car", EntityFreightHeavyweight.class, ItemIDs.minecartHeavyweightMailcar.item, "freight", 0.5, null, 18, 27,(new String[]{"Cargo: any"})),
	freightCartBamboo("Bamboo Flatcar Freight", EntityFreightBamboo.class, ItemIDs.minecartFreightBamboo.item, "freight", 0.1, (TraincraftUtil.getBytesFromColors(new String[] {"Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey"})), 18, 36,(new String[]{"Cargo: any"})),
	freightGermanPost("Freight German Post", EntityFreightGermanPost.class, ItemIDs.minecartFreightGermanPost.item, "freight", 0.1, (TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Red", "Blue"})), 18, 36, (new String[]{"Cargo: non-blocks"})),
	freightDepressedFlatbed("Freight Depressed Flatcar", EntityFreightDepressedFlatbed.class, ItemIDs.minecartFreightDepressedFlatbed.item, "freight", 0.1, (TraincraftUtil.getBytesFromColors(new String[]{"Black", "Grey", "LightGrey", "Blue", "Yellow", "White"})), 18, 36,(new String[]{"Cargo: any"})),
	freightCarL("Freight Car L", EntityFreightCartL.class, ItemIDs.minecartFreightL.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Blue"})), 18, 27,(new String[]{"Cargo: any"})),
	freightHeavyweight("Freight Heavyweight Baggage", EntityFreightHeavyweightBaggage.class, ItemIDs.minecartHeavyweightFreight.item, "freight", 3, (TraincraftUtil.getBytesFromColors(new String[]{"Red", "Grey"})), 18, 36,(new String[]{"Cargo: any"})),

	/**
	 * Tanks
	 **/
	tankWagon_DB("Tank Wagon DB", EntityTankWagon_DB.class, ItemIDs.minecartTankWagon_DB.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 50000, (TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Green"})), 18, 0, (new String[]{"Capacity: 50000mb"})),
	tankThreeDome("Tank Wagon Three Dome", EntityTankWagonThreeDome.class, ItemIDs.minecartTankWagonThreeDome.item, "tank", 0, 0, 7.5, 0, 0, 0, 0, 0, 90000, (TraincraftUtil.getBytesFromColors(new String[]{"Green", "White"})), 18, 0, (new String[]{"Capacity: 90000mb"})),
	tankWagonUS("Tank Wagon US", EntityTankWagonUS.class, ItemIDs.minecartTankWagonUS.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 70000, (TraincraftUtil.getBytesFromColors(new String[]{"Black", "Grey", "Yellow", "White", "LightGrey", "Green", "Blue", "Lime", "Orange", "Cyan", "Pink", "Purple", "LightBlue", "Magenta","Red","Brown"})), 18, 0, (new String[]{"Capacity: 70000mb"})),
	tankWagonGrey("Tank Wagon Grey", EntityTankWagon2.class, ItemIDs.minecartTankWagon2.item, "tank", 0, 0, 3, 0, 0, 0, 0, 0, 40000, (TraincraftUtil.getBytesFromColors(new String[]{"Blue", "White", "Orange", "Black"})), 18, 0, (new String[]{"Capacity: 40000mb"})),
	tankCartLava("Tank Lava", EntityTankLava.class, ItemIDs.minecartWatertransp.item, "tank", 0, 0, 5, 0, 0, 0, 0, 0, 30000, (TraincraftUtil.getBytesFromColors(new String[]{"Empty", "Full"})), 18, 0, (new String[]{"Lava Capacity: 30000mb"})),
	tankWagonYellow("Tank Wagon Yellow", EntityTankWagon.class, ItemIDs.minecartTankWagon.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 40000, null, 18, 0, (new String[]{"Capacity: 40000mb"})),

	/**
	 * Tenders
	 */
	tenderSmall("Tender Small Black", EntityTenderSmall.class, ItemIDs.minecartTender.item, "tender", 0, 0, 0.1, 0, 0, 0, 0, 0, 5000, TraincraftUtil.getBytesFromColors(new String[]{"Black", "Red", "Blue", "Green", "Yellow"}), 18, 0, (new String[]{"Water capacity: 5000mb"})),
	tenderHeavy("Tender Heavy", EntityTenderHeavy.class, ItemIDs.minecartTenderHeavy.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 14000, TraincraftUtil.getBytesFromColors(new String[]{"Black", "White"}), 18, 0, (new String[]{"Water capacity: 14000mb"})),
	tenderGS4("Tender GS4", EntityTenderGS4.class, ItemIDs.minecartGS4_Tender.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 18000, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "White"}), 18, 0, (new String[]{"Water capacity: 18000mb"})),
	Model4000GallonTender("4000GallonTender",EntityTender4000.class, ItemIDs.minecart4000GallonTender.item,"tender",0,0,2,0,0,0,0,0,15000,TraincraftUtil.getBytesFromColors(new String[]{"Green", "Blue", "Red", "Lime"}),18,0,(new String[] {"Water capacity: 15000mb"})),
	ModelFowler4FTender("Fowler 4F Tender",EntityTenderFowler4F.class, ItemIDs.minecartFowler4FTender.item,"tender",0,0,2,0,0,0,0,0,15000, null, 18,0,(new String[] {"Water capacity: 15000mb"})),
	Model225Tender("1225 tender",EntityTenderBerk1225.class, ItemIDs.minecarttenderBerk1225.item,"tender",0,0,2,0,0,0,0,0,15000,TraincraftUtil.getBytesFromColors(new String[]{"Black", "Grey","LightGrey"}),18,0,(new String[] {"Water capacity: 15000mb"})),
	tender4_4_0("Tender 4-4-0", EntityTender4_4_0.class,  ItemIDs.minecartSteamRedTender.item, "tender", 0, 0, 0.2, 0, 0, 0, 0, 0, 8000, TraincraftUtil.getBytesFromColors(new String[] { "Black", "White", "Brown", "Blue", "Green", "Red", "Purple" }), 18, 0, (new String[] { "Water capacity: 8000mb" })),
	tenderA4("Tender A4 Mallard", EntityTenderA4.class,  ItemIDs.minecartLocoA4MallardTender.item,"tender", 0, 0, 0.2, 0, 0, 0, 0, 0, 6000, TraincraftUtil.getBytesFromColors(new String[] { "Blue", "Lime", "Black", "Green", "White" }), 18,0, (new String[] { "Water capacity: 6000mb" })),
	tenderBR01("Tender BR01",EntityTenderBR01_DB.class, ItemIDs.minecartTenderBR01_DB.item,"tender",0,0,0.5,0,0,0,0,0,20000,null,18,0,(new String[] {"Water capacity: 20000mb"})),
	tenderCoranationClass("Tender Coranation Class",EntityTenderCoranationClass.class,ItemIDs.minecartLocoCoranationClassTender.item,"tender",0,0,0.5,0,0,0,0,0,20000,null,18,0,(new String[] {"Water capacity: 20000mb"})),
	tenderEr_Ussr("Tender ER_USSR",EntityTenderEr_Ussr.class, ItemIDs.minecartTenderEr.item,"tender",0,0,2,0,0,0,0,0,16000,null,18,0,(new String[] {"Water capacity: 16000mb"})),
	tenderC62Class("Tender C62Class", EntityTenderC62Class.class, ItemIDs.minecartTenderC62Class.item, "tender", 0, 0, 0.5, 0, 0, 0, 0, 0, 14000, TraincraftUtil.getBytesFromColors(new String[] { "Black", "Red" }), 18, 0, (new String[] { "Water capacity: 14000mb" })),
	tenderD51("Tender D51", EntityTenderD51.class, ItemIDs.minecartTenderD51.item, "tender", 0, 0, 0.5, 0, 0, 0, 0, 0, 14000, null, 18, 0, (new String[] { "Water capacity: 12000mb" })),
	tenderAdler("Tender Adler",EntityTenderAdler.class, ItemIDs.minecartTenderAdler.item,"tender",0,0,0.5,0,0,0,0,0,4000,null,18,0,(new String[] {"Water capacity: 4000mb"})),
	tender_C41("Tender C41",EntityTender_C41.class, ItemIDs.minecartTenderC41.item,"tender",0,0,1.5,0,0,0,0,0,16000,TraincraftUtil.getBytesFromColors(new String[]{"Black", "Grey"}),18,0,(new String[] {"Water capacity: 16000mb"})),
	tender_Southern1102("Tender Southern1102",EntityTender_Southern1102.class, ItemIDs.minecartTenderSouthern1102.item,"tender",0,0,1.5,0,0,0,0,0,16000,null,18,0,(new String[] {"Water capacity: 16000mb"})),
	tender_MILW("Tender MILW",EntityTenderMILW.class, ItemIDs.minecartMILWTender.item,"tender",0,0,1.5,0,0,0,0,0,16000,null,18,0,(new String[] {"Water capacity: 16000mb"})),

	/** Diesel */
	locoDieselKOF("Loco Diesel KOF DB",EntityLocoDieselKof_DB.class,ItemIDs.minecartKof_DB.item, "diesel",750,45,0,60,0,170,0.66,0.96,5000,TraincraftUtil.getBytesFromColors(new String[] {"Red","Green","Yellow","Black","Blue"}),17,-1.6),
	locoDieselGP40("Loco Diesel CD742", EntityLocoDieselCD742.class,  ItemIDs.minecartCD742.item, "diesel", 1727, 70, 0, 50, 0, 250, 0.8, 0.966, 10000, TraincraftUtil.getBytesFromColors(new String[] { "Yellow", "White", "Blue", "Orange", "LightBlue" }), 15, -2.5),
	locoDieselChME3("Loco Diesel ChME3", EntityLocoDieselChME3.class,  ItemIDs.minecartChmE3.item,"diesel", 2383, 95, 0, 60, 0, 170, 0.66, 0.96, 5000, null, 15,  -1.2),
	locoDieselGP7Red("Loco Diesel GP7",EntityLocoDieselGP7Red.class, ItemIDs.minecartGP7Red.item,"diesel",2464,105,0,50,0,200,0.74,0.96,20000,(TraincraftUtil.getBytesFromColors(new String[] {"Red","Blue","Black","Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey", "Skin16", "Skin17", "Skin18", "Skin19"})),15,-1.4),
	locoDieselSD40("Loco Diesel SD40",EntityLocoDieselSD40.class, ItemIDs.minecartLocoSD40.item,"diesel",3041,105,0,60,0,200,0.8,0.97,20000,(TraincraftUtil.getBytesFromColors(new String[] {"Red","Yellow","Black","Green","Orange","Magenta","Blue", "Pink"})),10,-2.3),
	locoDieselSD70("Loco Diesel SD70",EntityLocoDieselSD70.class, ItemIDs.minecartLocoSD70.item,"diesel",4055,120,0,60,0,200,0.8,0.97,20000,(TraincraftUtil.getBytesFromColors(new String[] {"Orange","Yellow","Red","Blue","Magenta","Black", "Pink", "Green"})),10,-2.3),
	locoDieselShunter("Loco Diesel Shunter", EntityLocoDieselShunter.class,  ItemIDs.minecartShunter.item,"diesel", 354, 32, 0, 70, 0, 260, 0.6, 0.94, 8000,TraincraftUtil.getBytesFromColors(new String[] { "Blue", "Green", "Red", "Black", "Cyan" }), 14,  -2.58),
	locoDieselV60_DB("Loco Diesel V60 DB",EntityLocoDieselV60_DB.class, ItemIDs.minecartV60_DB.item,"diesel",1058,60,0,60,0,170,0.66,0.96,8000,TraincraftUtil.getBytesFromColors(new String[] {"Red","Green","Yellow","Cyan"}),15,-1.3),
	locoDieselIC4_DSB_MG("Loco Diesel IC4 DSB MG",EntityLocoDieselIC4_DSB_MG.class, ItemIDs.minecartIC4_DSB_MG.item,"diesel",760,200,0,60,0,200,0.8,0.97,20000,TraincraftUtil.getBytesFromColors(new String[] {"White","Red"}),10,-6, (new String[]{"Unfinished, Creative Only"})),
	//locoDieselMILW_H1044("Loco Diesel MILW H10-44",EntityLocoDieselMILW_H1044.class,ItemIDs.minecartMILW_H1044.item,"diesel",1618,97,0,60,0,170,0.66,0.96,8000,TraincraftUtil.getBytesFromColors(new String[]{"Orange", "Yellow", "Black", "Red", "Blue", "Grey"}),15,-4.4),
	locoDieselEMDF7("Loco Diesel EMD F-7", EntityLocoDieselEMDF7.class, ItemIDs.minecartLocoEMDF7.item, "diesel", 1600, 150, 0, 50, 0, 200, 0.8, 0.97, 12000,TraincraftUtil.getBytesFromColors(new String[]{"Black", "Lime", "Red", "Brown", "Green", "Orange", "Yellow"}), 10, -2.4),
	locoDieselEMDF3("Loco Diesel EMD F-3", EntityLocoDieselEMDF3.class, ItemIDs.minecartLocoEMDF3.item, "diesel", 1500, 166, 0, 55, 0, 200, 0.8, 0.97, 12000, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Black", "Orange", "Blue", "Brown", "Green", "Magenta"}), 10, -2.25),
	locoDieselEWSClass66("Loco Electric EWS Class 66", EntityLocoDieselClass66.class, ItemIDs.minecartLocoEWSClass66.item, "diesel", 3678, 121, 0, 10, 0, 170, 0.7, 0.965, 6400, TraincraftUtil.getBytesFromColors(new String[] { "Pink", "Green", "Red"}), 18, -5.5),
	locoDieselDeltic("Loco Diesel Deltic", EntityLocoDieselDeltic.class, ItemIDs.minecartLocoDeltic.item, "diesel", 1997, 161, 0, 10, 0, 170, 0.7, 0.965, 6400, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan"}), 18, -5),
	locoDieselDD35A("Loco Diesel DD35A", EntityLocoDieselDD35A.class, ItemIDs.minecartLocoDD35A.item, "diesel", 5000, 90, 0, 10, 0, 170, 0.7, 0.965, 6850, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "Black"}), 18, -4.75),
	locoDiesel44TonSwitcher("GE 44-ton Diesel switcher", EntityLocoDiesel44TonSwitcher.class, ItemIDs.minecartLoco44TonSwitcher.item, "diesel", 400, 56, 0, 10, 0, 170, 0.7, 0.965, 6850, TraincraftUtil.getBytesFromColors(new String[]{"Black", "Cyan"}), 18, -2.75),
	locoDieselBamboo("Bamboo Flatcar Engine", EntityLocoDieselBamboo.class, ItemIDs.minecartTrainBamboo.item, "diesel", 30, 20, 0, 10, 0, 170, 0.7, 0.965, 3000,  TraincraftUtil.getBytesFromColors(new String[] {"Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey"}), 18, -2),
	locoDieselWLs40("Loco WLs40", EntityLocoDieselWLs40.class, ItemIDs.minecartLocoWLs40.item, "diesel", 60, 17, 0, 10, 0, 170, 0.7, 0.965, 3000, null, 18, -3),
	locoDieselFOL_M1("Loco FOL-M1", EntityLocoDieselFOLM1.class, ItemIDs.minecartLocoFOLM1.item, "diesel", 5000, 110, 0, 10, 0, 170, 0.7, 0.965, 15000, TraincraftUtil.getBytesFromColors(new String[]{"Grey", "Blue", "Black"}), 18, -3.9, (new String[] {"Fictional loco from Factorio"})),

	/** Electric */
	locoElectricVL10("Loco Electric VL10", EntityLocoElectricVL10.class,  ItemIDs.minecartVL10.item, "electric", 6250, 100, 0, 8, 0, 400, 1.1, 0.956, 0, null, 14, -2.3),
	locoElectricBR_E69("Loco Electric BR_E69",EntityLocoElectricBR_E69.class, ItemIDs.minecartBR_E69.item,"electric",400,50,0,5,0,400,0.9,0.946,0,TraincraftUtil.getBytesFromColors(new String[] {"Green","Red","Black","Grey"}),18,0),
	locoElectricMineTrain("Loco Electric Minetrain",EntityLocoElectricMinetrain.class,ItemIDs.minecartLocoMineTrain.item,"electric",500,40,0,80,0,160,0.5,0.97,0,null,18,-1),
	locoElectricSpeedZeroED("Loco Electric High Speed ZeroED", EntityLocoElectricHighSpeedZeroED.class, ItemIDs.minecartLocoHighSpeedZeroED.item, "electric", 700, 240, 0, 3, 0,230, 1.4, 0.98, 0, TraincraftUtil.getBytesFromColors(new String[]{"White", "LightGrey", "Grey"}), 13,  -3.4),
	locoElectricICE1("Loco Electric ICE 1", EntityLocoElectricICE1.class, ItemIDs.minecartICE1_Loco.item, "electric", 4300, 280, 0, 4, 0, 250, 1.5, 0.98, 0, null, 13, -5),
	//locoSpeedGrey("Loco High Speed",EntityLocoElectricNewHighSpeedLoco.class, null,null),
	locoElectricTramYellow("Loco Electric Yellow Wood Tram",EntityLocoElectricTramWood.class, ItemIDs.minecartTramWood.item,"electric",300,55,0,10,0,140,0.5,0.965,0,null,14,-2),
	locoElectricTramNY("Loco Electric Tram NY", EntityLocoElectricTramNY.class,  ItemIDs.minecartNYTram.item, "electric", 1327, 89, 0, 10, 0, 170, 0.7, 0.965, 0, null, 18, -3),
	locoElectricBR185("Loco Electric BR 185", EntityLocoElectricBR185.class, ItemIDs.minecartLocoBR185.item, "electric", 2428, 160, 0, 6, 0, 170, 0.7, 0.965, 0, TraincraftUtil.getBytesFromColors(new String[]{"Red", "Blue", "Magenta", "Cyan", "Grey", "LightBlue", "LightGrey", "Orange", "Pink", "Purple", "White", "Yellow", "Black", "Brown","skin16"}), 18, -5),
	locoDieselE10lDB("Loco Electric E10 DB",EntityLocoElectricE10_DB.class, ItemIDs.minecartE10_DB.item,"electric",2473,150,0,8,0,170,0.66,0.96,8000,TraincraftUtil.getBytesFromColors(new String[] {"Blue","Red", "Grey", "Brown", "Green", "LightGrey", "Lime", "Pink", "Purple", "White","Cyan"}),15,-3.8),
	locoDieselE103("Loco Electric E103",EntityLocoElectricE103.class, ItemIDs.minecartE103.item,"electric",2806,200,0,8,0,170,0.66,0.96,8000,TraincraftUtil.getBytesFromColors(new String[] {"Red","Blue"}),15,-3.6),
	locoElectricClass85("Loco Electric Class 85", EntityLocoElectricClass85.class, ItemIDs.minecartLocoClass85.item, "electric", 1997, 160, 0, 10, 0, 170, 0.7, 0.965, 0, null, 18, -5),
	locoElectricCD151("Loco Electric CD151", EntityLocoElectricCD151.class, ItemIDs.minecartLocoCD151.item, "electric", 1133, 150, 0, 10, 0, 170, 0.7, 0.965, 6850, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Yellow", "Red"}), 18, -4),
	locoElectricBP4("Loco Electric BP4",EntityLocoElectricBP4.class, ItemIDs.minecartLocoBP4.item,"electric",1520,105,0,60,0,200,0.8,0.97,8650,TraincraftUtil.getBytesFromColors(new String[]{"Green", "Purple", "Cyan", "Grey"}),10,-3),

	/** Steam */
	locoSteamA4("Loco Steam A4 Mallard", EntityLocoSteamMallardA4.class,  ItemIDs.minecartLocoA4Mallard.item, "steam", 1418, 203, 0, 60, 200, 160, 0.65, 0.97, 10000, TraincraftUtil.getBytesFromColors(new String[] { "Blue", "Lime", "Black", "Green", "White" }), 7, -5 ),
	locosteamHallClass("Loco Hall Class", EntityLocoSteamHallClass.class,  ItemIDs.minecartLocoHallClass.item, "steam", 1091, 164, 0, 60, 200, 160, 0.65, 0.97, 10000, TraincraftUtil.getBytesFromColors(new String[] { "Green", "Red", "Lime" }), 7, -4.95 ),
	locosteamBerk1225("Loco Berkshire 1225", EntityLocoSteamBerk1225.class,  ItemIDs.minecartLocoBerk1225.item, "steam", 2775, 164, 0, 60, 200, 160, 0.65, 0.97, 10000, TraincraftUtil.getBytesFromColors(new String [] {"Black", "LightGrey"}), 7, -4.25 ),
	locosteamBerk765("Loco Berkshire 765", EntityLocoSteamBerk765.class,  ItemIDs.minecartLocoBerk765.item, "steam", 2563, 164, 0, 60, 200, 160, 0.65, 0.97, 10000, null, 7, -4.25 ),
	locosteamFowler("Loco Fowler", EntityLocoSteamFowler.class,  ItemIDs.minecartLocoFowler.item, "steam", 980, 102, 0, 60, 200, 160, 0.65, 0.97, 10000, null, 7, -3.25 ),
	locosteamKingClass("Loco King Class", EntityLocoSteamKingClass.class,  ItemIDs.minecartLocoKingClass.item, "steam", 1613, 174, 0, 60, 200, 160, 0.65, 0.97, 10000, TraincraftUtil.getBytesFromColors(new String[] { "Green", "Blue", "Lime" }), 7, -5.35 ),
	locoSteamMILW("Loco Steam MILW Class A", EntityLocoSteamMILWClassA.class,  ItemIDs.minecartLocoMILWClassA.item, "steam", 1228, 193, 0, 60, 150, 160, 0.65, 0.97, 10000, null, 7, -4.5 ),
	locoSteamCherepanov("Loco Steam Cherepanov",EntityLocoSteamCherepanov.class, ItemIDs.minecartLocoCherepanov.item,"steam",60,30,0,40,120,120,0.3D,0.98D,3000,null,18,-1.7),
	locoSteamBR80("Loco Steam BR80",EntityLocoSteamBR80_DB.class,ItemIDs.minecartLocoBR80_DB.item,"steam",575,45,0,100,130,135,0.45,0.97,7000,TraincraftUtil.getBytesFromColors(new String [] {"Black", "Green"}),16,-1.1),
	locoSteam4_4_0("Loco Steam 4-4-0",EntityLocoSteam4_4_0.class, ItemIDs.minecartPower.item,"steam",400,50,0,40,160,190,0.65,0.95,5000,TraincraftUtil.getBytesFromColors(new String[] {"Red","White","Blue","Brown","Green","Black", "Purple"}),16,-2),
	locoSteamSmall("Loco Steam Small",EntityLocoSteamSmall.class,  ItemIDs.minecartLoco3.item, "steam",250,45,0,140,140,160,0.5D,0.968D,5000,TraincraftUtil.getBytesFromColors(new String[] {"Blue","Red","Green","Yellow","Black"}),18,-1.7),
	locoSteamLSSP7("Loco Steam LSSP7",EntityLocoSteamLSSP7.class,  ItemIDs.minecartLocoLSSP7.item, "steam",250,45,0,140,140,160,0.5D,0.968D,5000,null,18,-1.1),
	locoHeavySteam("Loco Steam Heavy",EntityLocoSteamHeavy.class, ItemIDs.minecartHeavySteam.item,"steam",3000, 65, 0, 40, 140, 190,0.4D, 0.9D, 10000, null, 7, -3),//SIKE ITS BACK
	locoSteamC62Class("Loco Steam C62Class", EntityLocoSteamC62Class.class,  ItemIDs.minecartLocoC62Class.item,
			"steam", 1223, 129, 0, 60, 180, 160, 0.7, 0.97, 10000, TraincraftUtil.getBytesFromColors(new String[] { "Black", "Red" }), 7, -5.66),
	locoSteamD51Short("Loco Steam D51 Short Streamlining", EntityLocoSteamD51.class,  ItemIDs.minecartLocoD51Short.item,
			"steam", 1658, 85, 0, 60, 180, 160, 0.7, 0.97, 10000,  TraincraftUtil.getBytesFromColors(new String[] { "Black", "Grey" }), 7, -5.66),
	locoSteamD51Long("Loco Steam D51 Long Streamlining", EntityLocoSteamD51Long.class,  ItemIDs.minecartLocoD51Long.item,
			"steam", 1658, 85, 0, 60, 180, 160, 0.7, 0.97, 10000, TraincraftUtil.getBytesFromColors(new String[] { "Black", "Grey" }), 7, -5.66),
	locoSteamBR01_DB("Loco Steam BR01", EntityLocoSteamBR01_DB.class, ItemIDs.minecartLocoBR01_DB.item,"steam", 2120, 130, 0, 60, 200, 300, 0.6, 0.97, 10000, null, 10,  -3.7),
	locoSteamCoranationClass("Loco Steam Coranation Class", EntityLocoSteamCoranationClass.class,
							 ItemIDs.minecartLocoCoranationClass.item, "steam", 1619, 183, 0, 60, 200, 300, 0.6, 0.97, 10000, null, 10,
									 -6),
	locoSteamGS4("Loco Steam GS4", EntityLocoSteamGS4.class,  ItemIDs.minecartGS4_Loco.item, "steam", 2653, 180, 0, 60, 350, 450, 0.6, 0.95, 8800, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "White"}), 10, -6 ),
	locoSteamEr_USSR("Loco Steam ER_USSR", EntityLocoSteamEr_Ussr.class, ItemIDs.minecartLocoEr.item,"steam", 800, 80, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -3.7 ),
	locoSteamC41("Loco Steam C41", EntityLocoSteamC41.class, ItemIDs.minecartLocoC41.item,"steam", 1484, 120, 0, 80, 100, 200, 0.35, 0.975, 4000, null, 10, -3.4 ),
	locoSteamC41_080("Loco Steam C41 0-8-0", EntityLocoSteamC41_080.class, ItemIDs.minecartLocoC41_080.item,"steam", 1484, 120, 0, 80, 100, 200, 0.35, 0.975, 4000, null, 10, -3.4 ),
	locoSteamAlcoSc4("Loco Steam Alco SC4", EntityLocoSteamAlcoSC4.class, ItemIDs.minecartLocoAlcoSC4.item,"steam", 800, 120, 0, 80, 100, 200, 0.35, 0.975, 4000, TraincraftUtil.getBytesFromColors(new String[]{"Black", "Grey"}), 10, -3.4 ),
	locoSteamSouthern1102("Loco Steam Southern 1102", EntityLocoSteamSouthern1102.class, ItemIDs.minecartLocoSouthern1102.item,"steam", 1236, 118, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -3.4 ),
	locoSteamUSATCUS("Loco Steam USATCUS", EntityLocoSteamUSATCUS.class, ItemIDs.minecartLocoUSATCUS.item,"steam", 197, 75, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -2.5 ),
	locoSteamUSATCUK("Loco Steam USATCUK", EntityLocoSteamUSATCUK.class, ItemIDs.minecartLocoUSATCUK.item,"steam", 197, 75, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -2.5 ),
	locoSteamC41T("Loco Steam C41T", EntityLocoSteamC41T.class, ItemIDs.minecartLocoC41T.item,"steam", 1484, 110, 0, 80, 100, 200, 0.35, 0.975, 16000, null, 10, -3.4 ),
	locoSteamForney("Loco Steam Forney",EntityLocoSteamForneyRed.class, ItemIDs.minecartLocoForneyRed.item,"steam",600,70,0,60,160,130,0.44,0.968,8000,TraincraftUtil.getBytesFromColors(new String[] {"Red","Grey","Yellow","Brown","Blue","Green"}),15,-1.35),
	locoSteamMogul("Loco Steam Mogul",EntityLocoSteamMogulBlue.class, ItemIDs.minecartLocomogulBlue.item,"steam",500,65,0,50,180,180,0.56,0.967,5000,TraincraftUtil.getBytesFromColors(new String[] {"Blue","Black","Brown","Green","Red","White"}),15,-2.2),
	locoSteamShay("Loco Steam Shay",EntityLocoSteamShay.class, ItemIDs.minecartLocoSteamShay.item,"steam",250,50,0,50,160,130,0.5,0.968,4000,null,15,-1),
	locoSteamVBShay("Loco Steam VB Shay",EntityLocoSteamVBShay.class, ItemIDs.minecartLocoSteamVBShay.item,"steam",250,32,0,40,140,100,0.5,0.968,3000,null,15,-0.5),
	locoSteamClimax("Loco Steam Climax",EntityLocoSteamClimax.class, ItemIDs.minecartLocoSteamClimax.item,"steam",250,45,0,50,160,130,0.5,0.968,4000,null,15,-1.5),
	locoSteamPannier("Loco Steam Pannier",EntityLocoSteamPannier.class, ItemIDs.minecartLocoSteamPannier.item,"steam",903,80,0,50,160,130,0.5,0.968,8000,null,15,-3.5),
	locoSteamAlice("Loco Steam Alice",EntityLocoSteamAlice0_4_0.class, ItemIDs.minecartLocoSteamAlice.item,"steam",200,32,0,60,160,200,0.5,0.968,3750,null,15,-2),
	locoSteamGLYN("Loco Steam glyn",EntityLocoSteamGLYN042T.class, ItemIDs.minecartLocoSteamGLYN.item,"steam",600,32,0,60,160,200,0.45,0.968,3750,null,15,-2.5),
	locoSteam262T("Loco Steam 262T",EntityLocoSteam262T.class, ItemIDs.minecartLocoSteam262T.item,"steam",300,70,0,60,160,300,0.5,0.968,4250,null,15,-3),
	locoSteam040VB("Loco Steam 040VB",EntityLocoSteam040VB.class, ItemIDs.minecartLocoSteam040vb.item,"steam",200,32,0,40,120,200,0.5,0.968,2500,null,15,-1.1),
	locoSteamAdler("Loco Steam Adler",EntityLocoSteamAdler.class, ItemIDs.minecartLocoSteamAdler.item,"steam",200,65,0,60,160,300,0.5,0.968,3000,null,15,-1.5),
	locoSteamSnowPlow("Loco Steam Snow Plow", EntityLocoSteamSnowPlow.class, ItemIDs.minecartLocoSnowPlow.item, "steam, snow plow", 200, 20, 0, 10, 120, 170, 0.7, 0.965, 6850, null, 18, -4.75),

	/** JCIR-TC*/

	//stEAmeeee//
	Climax2("2TruckClimax", SteamClimaxNew.class, ItemIDs.minecartClimaxNew.item, "steam", 280,35,0,50,160,120,0.7,0.8,8000, null,15,-1.0),
	VBShay("2TruckVerticalBoilerShay", SteamVBShay2.class, ItemIDs.minecartVBShay2.item, "steam", 230,35,0,50,160,120,0.7,0.8,6000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Grey"}),15,-1.3),
	Skook("Skookum", SteamSkook.class, ItemIDs.minecartSkook.item, "steam", 670, 75, 0, 75, 75, 190, 0.8, 0.91, 2000, null, 10, -2.4),
	SkookTender("Skookum Tender", TenderSkookTender.class, ItemIDs.minecartSkookTender.item,"tender",0,0,2,0,0,0,0,0,15000,null,18,0, (new String[] {"Water capacity: 15000mb."})),
	Shay3Truck("3 Truck Shay", SteamShay3Truck.class, ItemIDs.minecartShay3Truck.item, "steam", 438, 35, 0, 75, 75, 190, 0.8, 0.9, 4000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Grey", "White"}), 10, -1.8),
	Shay3TruckTender("3 Truck Shay Tender", TenderShay3Truck.class, ItemIDs.minecartShay3TruckTender.item,"tender",0,0,2,0,0,0,0,0,10000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Grey", "White"}),18,0,(new String[] {"Water capacity: 10000mb."})),
	F01("F01", SteamF01.class, ItemIDs.minecartF01.item, "steam", 350,65,0,50,160,120,0.7,0.8,9000, null,10,-1.7),
	BKno2a("BKno2a", SteamBKno2a.class, ItemIDs.minecartBKno2a.item, "steam", 700,80,0,50,160,120,0.7,0.8,15000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Orange"}),10,-1.3,(new String[] {"Place facing forward"})),
	BKno2b("BKno2b", SteamBKno2b.class, ItemIDs.minecartBKno2b.item, "steam", 700,80,0,50,160,120,0.7,0.8,15000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Orange"}),10,-1.2,(new String[] {"Place facing backward"})),
	WCPBuckingBull("WCPBuckingBull", SteamBuckingBull.class, ItemIDs.minecartWCPBuckingBull.item, "steam", 2200, 70, 0, 55, 70, 170, 0.78, 0.87, 2000, null, 10, -2.4),
	WCPBaler("WCPBaler", TenderBaler.class, ItemIDs.minecartWCPBaler.item,"tender",0,0,3,0,0,0,0,0,15000, null,18,0, (new String[] {"Water capacity: 15000mb."})),

	C11("Loco Steam C11", SteamC11.class, ItemIDs.minecartLocoC11.item,"steam", 1030, 97, 0, 80, 100, 200, 0.35, 0.975, 16000, null, 10, -3.0),
	Onion("Onion", SteamOnion.class, ItemIDs.minecartOnion.item, "steam", 350,155,0,50,140,100,0.7,0.99,8000, TraincraftUtil.getBytesFromColors(new String[]{"Black", "Orange", "Blue", "Grey", "Red", "Yellow", "White", "Brown", "LightGrey", "Pink"}),15,-1.3),
	OnionTender("Onion Tender", TenderOnion.class, ItemIDs.minecartOnionTender.item, "tender", 0, 0, 0.1, 0, 0, 0, 0, 0, 12000, TraincraftUtil.getBytesFromColors(new String[]{"Black", "Orange", "Blue", "Grey", "Red", "Yellow", "White", "Brown", "LightGrey", "Pink"}), 15, 0, (new String[] {"Water capacity: 12000mb."})),

	PELoco("Loco Steam PELoco", SteamPELoco.class, ItemIDs.minecartPELocomotive.item,"steam", 1484, 120, 0, 80, 100, 200, 0.35, 0.975, 4000, null, 10, -3.4 ),
	PETender("Tender PETender",TenderPETender.class, ItemIDs.minecartPETender.item,"tender",0,0,1.5,0,0,0,0,0,20000,null,18,0,(new String[] {"Water capacity: 20000mb"})),

	//EMD//
	F3A("F3A", DieselF3A.class, ItemIDs.minecartF3A.item, "diesel", 1500, 133, 0, 25, 0, 200, 0.9, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Blue"}), 10, -2.5),
	F7A("F7A", DieselF7A.class, ItemIDs.minecartF7A.item, "diesel", 1500, 133, 0, 25, 0, 200, 0.9, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "LightGrey", "White", "Orange", "Black", "Yellow", "Grey", "Magenta", "LightBlue", "Pink", "Cyan", "Brown", "Red", "Lime", "Blue", "Purple", "Skin16", "Skin17", "Skin25", "Skin18", "Skin19", "Skin20", "Skin21", "Skin22", "Skin23", "Skin24"}), 10, -2.70),
	F7B("F7B", DieselF7B.class, ItemIDs.minecartF7B.item, "diesel", 1500, 133, 0, 25, 0, 200, 0.9, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "LightGrey", "White", "Orange", "Black", "Yellow", "Grey", "Magenta", "LightBlue", "Pink", "Cyan", "Brown", "Red", "Lime", "Blue", "Purple", "Skin16", "Skin17", "Skin25", "Skin18", "Skin21", "Skin22"}), 10, -2.4),
	//BUnitF7B("BUnitF7B", BUnitF7.class, ItemIDs.minecartBUnitF7B.item, "b-unit", 0, 0, 7, 0, 0, 0, 0, 0, 15000, TraincraftUtil.getBytesFromColors(new String[]{"Green", "LightGrey", "White", "Orange", "Black", "Yellow", "Grey", "Magenta", "LightBlue", "Pink", "Cyan", "Brown", "Red", "Lime", "Blue", "Purple", "Skin16", "Skin17", "Skin18", "Skin21", "Skin22", "Skin19"}), 18, 0, (new String[] {"Reduces weight carried by 70 tons when fueled"})),
	CF7angle("CF7angle", DieselCF7angle.class, ItemIDs.minecartCF7angle.item, "diesel", 1500, 116, 0, 25, 0, 200, 0.65, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Red", "LightGrey", "White", "Green", "Brown", "Skin16", "Skin17", "Orange", "Skin18", "Skin19", "Skin20", "Skin21", "Black", "Grey", "Cyan", "Skin22", "Skin23", "Skin24", "Magenta", "Pink", "Lime", "Skin25", "Purple", "LightBlue", "Skin26", "Skin27", "Skin28"}), 10, -2.5),
	CF7round("CF7round", DieselCF7round.class, ItemIDs.minecartCF7round.item, "diesel", 1500, 116, 0, 25, 0, 200, 0.65, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Yellow", "White", "Grey", "Pink", "Cyan", "Skin24", "LightBlue", "Lime", "Skin16", "Skin17"}), 10, -2.5),
	CF7angle2("CF7angle2", DieselCF7angle2.class, ItemIDs.minecartCF7angle2.item, "diesel", 1470, 90, 0, 35, 0, 200, 0.6, 0.9, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Skin16", "Green", "White", "Skin17"}), 10, -2.5),
	F40PH("F40PH", DieselF40PH.class, ItemIDs.minecartF40PH.item, "diesel", 3000, 103, 0, 35, 0, 200, 0.9, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Grey", "Red", "Yellow", "Cyan"}), 10, -3.5),

	E7A("E7A", DieselE7A.class, ItemIDs.minecartE7A.item, "diesel", 2000, 188, 4.5, 20, 0, 180, 0.7, 0.79, 15000, TraincraftUtil.getBytesFromColors(new String[] {"Magenta", "Cyan", "Green", "Lime", "Brown", "Black", "Orange", "Blue", "LightBlue", "Yellow", "Grey", "LightGrey", "Purple", "Red", "Pink", "White", "Skin16", "Skin17", "Skin18", "Skin19", "Skin20", "Skin21", "Skin22", "Skin23", "Skin24", "Skin25", "Skin26", "Skin27", "Skin28", "Skin29", "Skin30", "Skin31", "Skin32", "Skin33", "Skin34", "Skin35"}), 10, -3.65),
	E7B("E7B", DieselE7B.class, ItemIDs.minecartE7B.item, "diesel", 2000, 188, 4.5, 20, 0, 180, 0.7, 0.79, 15000, TraincraftUtil.getBytesFromColors(new String[] {"Magenta", "Orange", "Yellow", "Grey", "LightGrey", "Purple", "Red", "Pink", "Skin27"}), 10, -3.77),
	//BUnitE7B("BUnitE7B", BUnitE7B.class, ItemIDs.minecartBUnitE7B.item, "diesel", 0, 0, 5, 0, 0, 0, 0, 0, 11000, TraincraftUtil.getBytesFromColors(new String[] {"Magenta", "Orange", "Yellow", "Grey", "LightGrey", "Purple", "Red", "Pink"}), 10, 0, (new String[] {"Reduces weight carried by 50 tons when fueled"})),
	E8A("E8A", DieselE8A.class, ItemIDs.minecartE8A.item, "diesel", 2250, 188, 0, 25, 0, 200, 0.8, 0.69, 11000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Purple", "Cyan", "Blue", "Brown", "Red", "Pink", "Yellow", "Lime", "Grey", "LightGrey", "White"}), 10, -3.65),
	E8B("E8B", DieselE8B.class, ItemIDs.minecartE8B.item, "diesel", 2250, 188, 0, 25, 0, 200, 0.8, 0.69, 11000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Blue", "Red", "Grey", "LightGrey"}), 10, -3.77),
	//BUnitE8B("BUnitE8B", BUnitE8B.class, ItemIDs.minecartBUnitE8B.item, "diesel", 0, 0, 5, 0, 0, 0, 0, 0, 11000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Blue", "Red", "Grey", "LightGrey"}), 10, 0, (new String[] {"Reduces weight carried by 50 tons when fueled"})),
	E9A("E9A", DieselE9A.class, ItemIDs.minecartE9A.item, "diesel", 2400, 188, 0, 25, 0, 200, 0.8, 0.69, 11000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Grey"}), 10, -3.75),
	E9B("E9B", DieselE9B.class, ItemIDs.minecartE9B.item, "diesel", 2400, 188, 0, 25, 0, 200, 0.8, 0.69, 11000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Grey"}), 10, -3.6875),
	CE8("CE8", DieselCE8.class, ItemIDs.minecartCE8.item, "diesel", 2250, 116, 0, 25, 0, 200, 0.8, 0.69, 11000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Grey", "LightBlue"}), 10, -3.65),

	GP7("GP7", DieselGP7.class, ItemIDs.minecartGP7.item, "diesel", 1500, 105, 0, 25, 0, 200, 0.80, 0.85, 18000, TraincraftUtil.getBytesFromColors(new String[] {"White", "Green", "Black", "Yellow", "Skin19", "LightGrey", "Skin20", "Skin21", "Lime", "Purple", "Blue", "Skin16", "Skin17", "Skin18", "Grey", "Orange", "Pink", "Skin22"}), 10, -2.9),
	GP7b("GP7b", DieselGP7b.class, ItemIDs.minecartGP7b.item, "diesel", 1500, 105, 0, 25, 0, 200, 0.80, 0.85, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "White", "Red", "Yellow"}), 10, -2.9),
	//BUnitGP7b("BUnitGP7b", BUnitGP7b.class, ItemIDs.minecartBUnitGP7b.item, "diesel", 0, 0, 7, 0, 0, 0, 0, 0, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "White", "Red", "Yellow"}), 10, 0, (new String[] {"Reduces weight carried by 70 tons when fueled"})),
	GP7u("GP7u",DieselGP7u.class, ItemIDs.minecartGP7u.item, "diesel", 1500, 100, 0, 25, 0, 200, 0.65, 0.85, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Blue", "LightGrey", "Grey", "White", "Yellow"}), 10, -2.9),
	GP7f("GP7f", DieselGP7f.class, ItemIDs.minecartGP7f.item, "diesel", 1575, 100, 0, 25, 0, 200, 0.85, 0.85, 18000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Magenta"}), 10, -2.9),
	GP9("GP9", DieselGP9.class, ItemIDs.minecartGP9.item, "diesel", 1750, 100, 0, 25, 0, 200, 0.80, 0.85, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "Black", "Orange", "White", "Yellow", "Skin18", "Skin16", "Skin17", "LightGrey"}), 10, -2.9),
	GP7old("GP7old", DieselGP7old.class, ItemIDs.minecartGP7old.item, "diesel", 1500, 105, 0, 25, 0, 200, 0.80, 0.85, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Blue", "White", "Brown", "Black", "Red", "Green", "Purple", "LightGrey", "Grey", "Lime", "Cyan", "Pink", "Skin16", "Skin17", "Skin18", "Skin19", "Skin20", "Skin21", "Skin22"}), 10, -2.9),
	GP9old("GP9old", DieselGP9old.class, ItemIDs.minecartGP9old.item, "diesel", 1750, 105, 0, 25, 0, 200, 0.80, 0.85, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "Red", "Orange", "Magenta", "Yellow","Black","Brown","Blue","Green"}), 10, -2.9),
	GP13("GP13", DieselGP13.class, ItemIDs.minecartGP13.item, "diesel", 2510,90,0,25,0,200,0.74,0.85,15000, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "LightBlue", "Black", "Orange", "White", "Yellow", "Skin16", "Skin17", "Skin18", "Skin19", "Skin20"}), 10, 1.4, (new String[] {"Uses the heritage GP40 Model"})),
	GP15("GP15", DieselGP15.class, ItemIDs.minecartGP15.item, "diesel", 1800, 105, 0, 15, 0, 200, 0.85, 0.85, 15000, TraincraftUtil.getBytesFromColors(new  String[] {"Yellow", "LightGrey", "Cyan", "LightBlue", "Skin26"}), 10, -2.6),
	GP30("GP30", DieselGP30.class, ItemIDs.minecartGP30.item, "diesel", 2250, 114, 0, 30, 0, 200, 0.8, 0.85, 15000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Black", "Yellow", "Blue", "Orange", "White", "Grey", "Red", "Purple", "Pink", "Cyan"}), 10, -3.1),
	GP35("GP35", DieselGP35.class, ItemIDs.minecartGP35.item, "diesel", 2500, 105, 0, 25, 0, 190, 0.8, 0.85, 15000, TraincraftUtil.getBytesFromColors(new  String[] {"Red","Black","Magenta","Skin16","Grey","Skin17","LightBlue","LightGrey"}), 10, -2.9),
	GP38dash2("GP38dash2", DieselGP38dash2.class, ItemIDs.minecartGP38dash2.item, "diesel", 2000, 105, 0, 25, 0, 200, 0.8, 0.85, 20000, TraincraftUtil.getBytesFromColors(new  String[] {"Cyan", "Green", "LightGrey", "Red", "Blue", "Yellow", "Orange", "Grey", "Black", "Pink", "Lime", "Skin16"}), 10, -3.1),
	GP38dash9W("GP38dash9W", DieselGP38dash9W.class, ItemIDs.minecartGP38dash9W.item, "diesel", 2000, 105, 0, 45, 0, 200, 0.5, 0.85, 10000, null, 10, -3.2),
	GP38H3("GP38H3", DieselGP38H3.class, ItemIDs.minecartGP38h3.item, "diesel", 2000, 169, 0, 25, 0, 200, 0.8, 0.85, 16000, TraincraftUtil.getBytesFromColors(new  String[] {"White", "Blue"}), 10, -3.25),
	GP38("GP38", DieselGP38.class, ItemIDs.minecartGP38.item, "diesel", 2300, 105, 0, 25, 0, 200, 0.8, 0.85, 20000, TraincraftUtil.getBytesFromColors(new  String[] {"Yellow","Grey", "Green", "Skin16", "Cyan", "LightGrey", "Skin18", "Blue"}), 10, -2.9),
	GP39("GP39", DieselGP39.class, ItemIDs.minecartGP39.item, "diesel", 2300, 105, 0, 25, 0, 200, 0.8, 0.85, 20000, TraincraftUtil.getBytesFromColors(new  String[] {"Green", "Grey", "Skin16", "Skin18", "Skin19"}), 10, -2.9),
	GP40("GP40", DieselGP40.class, ItemIDs.minecartGP40.item, "diesel", 3000, 105, 0, 25, 0, 200, 0.8, 0.85, 20000, TraincraftUtil.getBytesFromColors(new  String[] {"Grey", "Skin16", "Orange", "Skin17", "Lime", "White", "Skin18", "Skin19", "Blue"}), 10, -2.9),
	GP40TC("GP40TC", DieselGP40TC.class, ItemIDs.minecartGP40tc.item, "diesel", 3000, 169, 0, 25, 0, 200, 0.8, 0.85, 16000, TraincraftUtil.getBytesFromColors(new  String[] {"Blue", "White"}), 10, -3.25),
	GP49("GP49", DieselGP49.class, ItemIDs.minecartGP49.item, "diesel", 2800, 105, 0, 35, 0, 200, 0.65, 0.85, 20000, null, 10, -3.1),

	SD9("SD9", DieselSD9.class, ItemIDs.minecartSD9.item, "diesel", 1750, 104, 0, 35, 0, 200, 0.75, 0.85, 15000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Green", "Lime", "Orange", "White", "Cyan", "Grey", "Yellow", "LightGrey", "Skin16", "Skin17"}), 10, -3.2),
	SD38("SD38", DieselSD38.class, ItemIDs.minecartSD38.item, "diesel", 2000, 105, 0, 10, 0, 200, 0.75, 0.85, 12000, TraincraftUtil.getBytesFromColors(new String[] {"White", "LightGrey", "LightBlue", "Blue", "Skin16", "Cyan", "Skin17", "Black"}), 10, -3.45),
	SD39("SD39", DieselSD39.class, ItemIDs.minecartSD39.item, "diesel", 2300, 105, 0, 10, 0, 200, 0.75, 0.85, 12000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "LightGrey", "Brown", "Blue", "Skin16", "Skin18", "Cyan"}), 10, -3.45),
	SDL39("SDL39", DieselSDL39.class, ItemIDs.minecartSDL39.item, "diesel", 2300, 152, 0, 5, 0, 200, 0.75, 0.85, 7000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Blue", "Red", "Cyan"}), 10, -3),
	SD40("SD40", DieselSD40dash2.class, ItemIDs.minecartSD40dash2.item, "diesel", 3000, 105, 0, 20, 0, 200, 0.75, 0.85, 12000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Magenta", "White", "Skin18", "LightGrey", "Skin16", "Skin17", "Skin19"}), 10, -3.45),
	SD40T2("SD40T-2", DieselSD40T2.class, ItemIDs.minecartSD40T2.item, "diesel", 3000, 105, 0, 10, 0, 200, 0.75, 0.85, 14000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Grey", "Red", "Skin17", "Yellow", "Magenta", "Blue", "LightGrey", "LightBlue", "Purple", "Pink", "Skin16", "Skin18", "Green", "White"}), 10, -3.45),
	SD40dash2B("SD40-2B", DieselSD40dash2B.class, ItemIDs.minecartSD40dash2B.item, "diesel", 3000, 105, 0, 5, 0, 200, 0.75, 0.85, 12000, TraincraftUtil.getBytesFromColors(new String[] {"Cyan", "Red"}), 10, -3.8),
	SD40R("SD40R", DieselSD40R.class, ItemIDs.minecartSD40R.item, "diesel", 3000, 105, 0, 20, 0, 200, 0.75, 0.85, 12000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "LightGrey", "Skin16", "Skin17"}), 10, -3.45),
	SD40A("SD40A", DieselSD40A.class, ItemIDs.minecartSD40A.item, "diesel", 3000, 105, 0, 20, 0, 200, 0.75, 0.85, 14000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Grey"}), 10, -3.45),
	SDP40("SDP40", DieselSDP40.class, ItemIDs.minecartSDP40.item, "diesel", 3000, 160, 0, 5, 0, 200, 0.75, 0.85, 12000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "Lime", "Skin16", "Skin17", "Grey", "Blue", "Orange", "Skin18", "Skin19"}), 10, -3.8),
	SDP40F("SDP40F",DieselSDP40F.class, ItemIDs.minecartSDP40F.item, "diesel", 3000, 160, 0, 10, 0, 200, 0.75, 0.85, 12000, TraincraftUtil.getBytesFromColors(new String[] {"LightBlue", "White", "LightGrey", "Grey", "Yellow"}), 10, -3.95),
	SD45("SD45", DieselSD45dash2.class, ItemIDs.minecartSD45dash2.item, "diesel", 3600, 132, 0, 10, 0, 200, 0.75, 0.85, 16000, TraincraftUtil.getBytesFromColors(new String[] {"Blue","Skin16","Green","Pink","Grey","Black","Skin17","Skin18","Orange","Skin19","Yellow","Skin20","Red","Skin21","Skin22","Skin23","Skin24","Skin25","Skin26","LightGrey","Skin27","Skin28", "Skin29"}), 10, -3.45),
	SD45dash2B("SD45-2B", DieselSD45dash2B.class, ItemIDs.minecartSD45dash2B.item, "diesel", 3600, 132, 0, 10, 0, 200, 0.75, 0.85, 16000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Yellow", "Red", "White"}), 10, -3.8),
	FP45("FP45",DieselFP45.class, ItemIDs.minecartFP45.item, "diesel", 3600, 145, 0, 10, 0, 200, 0.75, 0.85, 16000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Yellow", "LightGrey", "Grey"}), 10, -3.95),
	F45("F45",DieselF45.class, ItemIDs.minecartF45.item, "diesel", 3600, 145, 0, 10, 0, 200, 0.75, 0.58, 16000, TraincraftUtil.getBytesFromColors(new String[] {"LightBlue", "Green", "Blue", "Yellow", "Red"}), 10, -3.65),
	SD70Mac("SD70Mac", DieselSD70Mac.class, ItemIDs.minecartSD70Mac.item, "diesel", 4000, 112, 0, 10, 0, 200, 0.7, 0.85, 20000, TraincraftUtil.getBytesFromColors(new String[]{"Red", "Blue", "Grey", "LightGrey", "LightBlue", "Yellow", "Black","Green", "Orange", "Lime", "Pink", "Magenta", "Brown", "Cyan"}), 18, -4),

	SW1("SW1", DieselSW1.class, ItemIDs.minecartSW1.item, "diesel", 600, 105, 0, 35, 0, 160, 0.6, 0.890, 8000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Green", "LightBlue", "Skin16", "Black", "Skin17", "Pink", "Purple", "Skin18", "Skin19", "Lime"}), 10, -2),
	SW8("SW8", DieselSW8.class, ItemIDs.minecartSW8.item, "diesel", 800, 105, 0, 35, 0, 200, 0.85, 0.7, 8000, TraincraftUtil.getBytesFromColors(new String[]{"Red", "Black"}), 10, -2.0),
	SW1000("SW1000", DieselSW1000.class, ItemIDs.minecartSW1000.item, "diesel", 1000, 105, 0, 25, 0, 200, 0.85, 0.7, 10000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Green", "Purple", "Yellow", "Skin16"}), 10, -2),
	SW1200("SW1200", DieselSW1200.class, ItemIDs.minecartSW1200.item, "diesel", 1200, 105, 0, 25, 0, 200, 0.85, 0.7, 8000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "Blue", "Skin17", "Black", "Lime", "Yellow", "Brown", "Cyan", "Orange", "Red", "Skin16"}), 10, -2),
	SW1500("SW1500", DieselSW1500.class, ItemIDs.minecartSW1500.item, "diesel", 1500, 105, 0, 25, 0, 200, 0.85, 0.7, 10000, TraincraftUtil.getBytesFromColors(new String[] {"LightBlue", "LightGrey", "Grey", "Black", "Magenta", "Pink", "Cyan", "Skin18", "Red", "Orange", "Skin16", "Blue", "Skin17", "Green"}), 10, -2),
	MP15DCW9("MP15DCW9", DieselMP15DCW9.class, ItemIDs.minecartMP15DCW9.item, "diesel", 1500, 97, 0, 25, 0, 200, 0.85, 0.7, 8000, TraincraftUtil.getBytesFromColors(new String[] {"Orange"}), 10, -2.3),

	Beep("Beep", DieselBeep.class, ItemIDs.minecartBeep.item, "diesel", 1500, 105, 0, 25, 0, 200, 0.89, 0.8, 9000, null, 10, -2.2),

	//GE//
	GE44Ton("GE 44-ton", DieselGE44Ton.class, ItemIDs.minecart44Ton.item, "diesel", 360, 56, 0, 10, 0, 200, 0.7, 0.75, 7000, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Black", "White"}), 10, -2.0),
	Boxcab23Ton("23 Ton Boxcab", DieselBoxcab23Ton.class, ItemIDs.minecartBoxcab23Ton.item, "diesel", 150, 40, 0, 10, 0, 200, 0.6, 0.6, 4000,  TraincraftUtil.getBytesFromColors(new String[] {"Blue", "White", "Black", "Green"}), 10, -1.5),
	//note on the boxcab: check the RenderEnum for more info related to its hitboxes
	GE25Ton("GE 25-ton", DieselGE25Ton.class, ItemIDs.minecart25Ton.item, "diesel", 150, 50, 0, 10, 0, 200, 0.65, 0.7, 4000, TraincraftUtil.getBytesFromColors(new String[]{"Brown", "Pink", "Cyan", "Orange", "Green"}), 10, -1),

	U18B("U18B", DieselU18B.class, ItemIDs.minecartU18B.item, "diesel", 1800, 113, 0, 20, 0, 200, 0.7, 0.88, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Orange","Skin16", "Red", "Skin17", "LightGrey", "Pink", "Magenta", "Cyan", "Purple", "Lime", "Yellow", "Grey", "Skin18", "Skin19", "Black", "Skin20", "Skin22", "Brown", "LightBlue", "Blue", "Skin23", "Skin24", "Skin25", "Skin26"}), 10, -2.5),
	U18BB("U18BB", DieselU18BB.class, ItemIDs.minecartU18BB.item, "diesel", 1800, 113, 0, 20, 0, 200, 0.7, 0.88, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Cyan", "Lime"}), 10, -2.5),
	U18BW("U18BW", DieselU18BW.class, ItemIDs.minecartU18BW.item, "diesel", 1800, 120, 0, 20, 0, 200, 0.7, 0.88, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Brown", "White", "Cyan", "Orange", "Grey", "Lime"}), 10, -2.5),
	U18Balt("U18Balt", DieselU18Balt.class, ItemIDs.minecartU18Balt.item, "diesel", 1800, 113, 0, 35, 0, 200, 0.7, 0.88, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "Skin27", "Cyan","Skin16", "Skin25", "Lime"}), 10, -2.5),
	U23B("U23B", DieselU23B.class, ItemIDs.minecartU23B.item, "diesel", 2250, 113, 0, 25, 0, 200, 0.7, 0.88, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "Orange", "LightGrey", "Cyan", "Skin16", "Skin18", "Yellow", "Blue", "LightBlue", "Black", "Green", "Purple", "Skin17", "Skin19"}), 10, -3.3),
	U30C("U30C", DieselU30C.class, ItemIDs.minecartU30C.item, "diesel", 3000, 113, 0, 20, 0, 200, 0.7, 0.88, 21000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "Grey", "Cyan", "Red", "Yellow", "Skin16"}), 10, -3.3),
	SF30C("SF30C", DieselSF30C.class, ItemIDs.minecartSF30C.item, "diesel", 3000, 121, 0, 20, 0, 200, 0.7, 0.8, 21000, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Red"}), 10, -4.3),
	U36C("U36C", DieselU36C.class, ItemIDs.minecartU36C.item, "diesel", 3600, 113, 0, 15, 0, 200, 0.75, 0.84, 21000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "Blue", "Red", "Yellow", "Orange"}), 10, -3.75),

	B23("B23-7", DieselB23.class, ItemIDs.minecartB23.item, "diesel", 2250, 113, 0, 25, 0, 200, 0.74, 0.91, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Red", "Blue", "LightGrey", "White", "Orange", "Pink", "Brown"}), 10, -3.4),
	SB23R("SB23R", DieselSB23R.class, ItemIDs.minecartSB23R.item, "diesel", 2300, 113, 0, 25, 0, 200, 0.74, 0.91, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Cyan","Grey", "Orange", "Purple", "White", "Skin16", "Green", "Brown"}), 10, -3.4),
	SB23E("SB23E", DieselSB23E.class, ItemIDs.minecartSB23E.item, "diesel", 2300, 113, 0, 25, 0, 200, 0.74, 0.91, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "Orange", "Red", "White", "Green"}), 10, -3.4),
	B30("B30-7", DieselB30.class, ItemIDs.minecartB30.item, "diesel", 3000, 113, 0, 35, 0, 200, 0.74, 0.91, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "Yellow"}), 10, -3.4),

	Dash840B("Dash840B", DieselDash840B.class, ItemIDs.minecartDash840B.item, "diesel", 4000, 113, 0, 40, 0, 200, 0.7, 0.88, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "Blue", "Cyan", "Orange", "LightGrey", "Skin16", "Green"}), 10, -3.3),
	Dash840BB("Dash840BB", DieselDash840BB.class, ItemIDs.minecartDash840BB.item, "diesel", 4000, 113, 0, 40, 0, 200, 0.7, 0.88, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Red"}), 10, -3.3),
	//BUnitDash840B("BUnitDash840B", BUnitDash840B.class, ItemIDs.minecartBUnitDash840BB.item, "diesel", 0, 0, 9, 0, 0, 0, 0, 0, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Red"}), 10, 0, (new String[] {"Reduces weight carried by 90 tons when fueled"})),
	Dash840BW("Dash840BW", DieselDash840BW.class, ItemIDs.minecartDash840BW.item, "diesel", 4000, 113, 0, 40, 0, 200, 0.7, 0.88, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Green"}), 10, -3.3),
	Dash840C("Dash840C", DieselDash840C.class, ItemIDs.minecartDash840C.item, "diesel", 4000, 113, 0, 40, 0, 200, 0.7, 0.88, 21000, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Yellow", "Black", "Grey", "Red", "LightGrey", "Green"}), 10, -3.3),

	Dash944CW("Dash944CW", DieselDash944CW.class, ItemIDs.minecartDash944CW.item, "diesel", 4400, 119, 0, 45, 0, 180, 0.75, 0.87, 21000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Skin16", "LightGrey", "Grey", "Orange", "Magenta", "Skin17", "Pink", "Green", "White"}), 10, -4.0),
	AC4400CW("AC4400CW", DieselAC4400CW.class, ItemIDs.minecartAC4400CW.item, "diesel", 4400, 119, 0, 45, 0, 180, 0.75, 0.87, 21000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Skin16", "LightGrey"}), 10, -4.0),

	ES44("ES44", DieselES44.class, ItemIDs.minecartES44.item, "diesel", 4400, 120, 0, 10, 0, 200, 0.7, 0.8, 21000, TraincraftUtil.getBytesFromColors(new String[] {"Lime", "Orange", "Red", "Yellow", "Blue", "LightGrey", "Grey", "Black"}), 10, -4.3),
	geGenesis("Ge Genesis", GeGenesis.class, ItemIDs.minecartGeGenesis.item, "diesel", 4250, 177, 0, 5, 0, 175, 0.93, 0.94, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "LightGrey", "White"}), 10, -5.5),

	//ALCO//
	AlcoPA1("PA1", DieselPA1.class, ItemIDs.minecartAlcoPA1.item, "diesel", 2000, 188, 0, 25, 0, 200, 0.7, 0.79, 15000, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Pink", "Black", "Blue", "Cyan", "Purple", "Yellow", "Orange", "Grey", "Brown", "LightGrey", "Green", "Lime", "LightBlue", "Magenta", "White", "Skin16", "Skin17", "Skin18"}), 10, -3.15),
	AlcoPB1("PB1", DieselPB1.class, ItemIDs.minecartAlcoPB1.item, "diesel", 2000, 188, 0, 25, 0, 200, 0.7, 0.79, 15000, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Black", "Yellow", "Orange", "LightGrey", "Magenta"}), 10, -3.1),

	C415H("C415H", DieselC415H.class, ItemIDs.minecartC415H.item, "diesel", 1500, 106, 0, 35, 0, 200, 0.87, 0.93, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Purple", "Magenta", "Brown", "Red", "Pink", "Green"}), 10, -2.4),
	C415S("C415S", DieselC415S.class, ItemIDs.minecartC415S.item, "diesel", 1500, 106, 0, 35, 0, 200, 0.87, 0.93, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Green", "Brown", "Yellow"}), 10, -2.5),
	C415L("C415L", DieselC415L.class, ItemIDs.minecartC415L.item, "diesel", 1500, 106, 0, 35, 0, 200, 0.87, 0.93, 17000, TraincraftUtil.getBytesFromColors(new String[] {"Black"}), 10, -2.5),
	C424("C424",DieselC424.class, ItemIDs.minecartC424.item, "diesel", 2400, 113, 0, 40, 0, 200, 0.7, 0.8, 18000, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Red", "Cyan", "Pink", "Orange"}), 10, -2.8),
	C425("C425", DieselC425.class, ItemIDs.minecartC425.item, "diesel", 2500, 113, 0, 40, 0, 200, 0.7, 0.8, 18000, TraincraftUtil.getBytesFromColors(new String[]{"Black", "Orange"}), 10, -2.8),
	DH643("DH643", DieselDH643.class, ItemIDs.minecartDH643.item, "diesel", 4300, 124, 0, 60, 0, 200, 0.7, 0.87, 16000, null, 10, -4.1),
	C855a("C855a", DieselC855a.class, ItemIDs.minecartC855a.item, "diesel", 5500, 106, 0, 40, 0, 200, 0.8, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[]{"Yellow"}), 10, -4.25),

	HH660("HH660", DieselHH660.class, ItemIDs.minecartHH660.item, "diesel", 660, 50, 0, 15, 0, 200, 0.9, 0.95, 6000, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Orange", "Black", "Red", "Cyan"}), 10, -2),
	S2("S2",  DieselS2.class, ItemIDs.minecartS2.item, "diesel", 1000, 96, 0, 15, 0, 200, 0.7, 0.965, 6000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Grey", "Green", "Orange", "Pink", "White", "Blue", "LightGrey", "Magenta", "Red"}), 18, -2),

	RSD15("RSD15", DieselRSD15.class, ItemIDs.minecartRSD15.item, "diesel", 2400, 105, 0, 30, 0, 200, 0.7, 0.91, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Blue","Cyan", "Grey", "Red"}), 10, -3.7),

	//BRuhMOMent//
	VO1000("VO1000", DieselVO1000.class, ItemIDs.minecartVO1000.item, "diesel", 1000, 67, 0, 20, 0, 200, 0.8, 0.9, 6000, TraincraftUtil.getBytesFromColors(new String[]{"Green", "Pink", "Blue", "LightBlue"}), 10, -2.2),

	//Fairbanks Morse//
	H1044("H1044", DieselH1044.class, ItemIDs.minecartH1044.item, "diesel", 1000, 97, 0, 25, 0, 200, 0.75, 0.85, 10000, TraincraftUtil.getBytesFromColors(new String[]{"Green", "Yellow", "Black", "Red", "Blue", "Grey", "LightBlue", "Orange", "White", "Lime", "LightGrey"}), 18, -2.2),
	H16_66("H16-66", DieselH16_66.class, ItemIDs.minecartH16_66.item, "diesel", 1600, 130, 0, 45, 0, 200, 0.87, 0.93, 16000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Yellow", "LightGrey", "Green", "Lime", "Black", "Grey", "Orange", "Brown", "Red", "Pink", "Magenta", "Skin16"}), 10, -3.35),
	H24_66("H24-66", DieselH24_66.class, ItemIDs.minecartH24_66.item, "diesel", 2400, 130, 0, 45, 0, 200, 0.87, 0.93, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Black", "Brown", "Yellow", "Grey", "LightGrey", "Lime"}), 10, -3.75),
	H24_66L("H24-66L", DieselH24_66L.class, ItemIDs.minecartH24_66L.item, "diesel", 2400, 130, 0, 45, 0, 200, 0.87, 0.93, 18000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Green"}), 10, -3.75),

	//other MOtOEr Shuite//
	NRE3gs21b("NRE3gs21b", DieselNRE3gs21b.class, ItemIDs.minecartNRE3gs21b.item, "diesel genset", 2100, 104, 0, 10,0,  200, 0.78, 0.8, 14000, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Orange", "Grey", "LightGrey"}), 10, -3.1),
	gtavthing("gtavthing", Dieselgtavthing.class, ItemIDs.minecartgtavthing.item, "diesel", 1550, 110, 0, 45, 0, 200, 0.85, 0.9, 10000, TraincraftUtil.getBytesFromColors(new String[] {"Yellow"}), 10, -3.2, (new String[] {"Fictional locomotive from GTA-V"})),
	FOLM1B("FOL-M1B", DieselFOLM1B.class, ItemIDs.minecartFOLM1B.item, "diesel", 5000, 110, 0, 10, 0, 200, 0.7, 0.965, 15000, TraincraftUtil.getBytesFromColors(new String[]{"Grey", "Blue"}), 18, -3.8, (new String[] {"Fictional B unit for the Fictional loco from Factorio"})),
	//BUnitFOLM1B("BUnitFOL-M1B", BUnitFOLM1B.class, ItemIDs.minecartBUnitFOLM1B.item, "b-unit", 0, 0, 9, 0, 0, 0, 0, 0, 15000, TraincraftUtil.getBytesFromColors(new String[]{"Grey", "Blue"}), 18, 0, (new String[]{"Reduces weight carried by 90 tons when fueled"})),
	Krautt("Krautt", DieselKrautt.class, ItemIDs.minecartKrautt.item, "diesel", 3540, 113, 0, 65, 0, 200, 0.75, 0.85, 20000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "LightGrey", "Black", "Red"}), 10, -3.3),
	ILMC("ILMC", DieselILMC.class, ItemIDs.minecartILMC.item, "diesel", 600, 45, 0, 25, 0, 200, 0.75, 0.85, 7000, null, 10, -1),

	SlugMA("SlugMA", SlugMA.class, ItemIDs.minecartSlugMA.item, "slug", 0, 0, 7, 0, 0, 0, 0, 0, 18000, TraincraftUtil.getBytesFromColors(new String[] {"White", "Skin16", "Magenta", "Blue"}), 10, 0, (new String[] {"Reduces weight carried by 70 tons when fueled"})),
	F7BSlug("F7BSlug", BUnitF7.class, ItemIDs.minecartF7BSlug.item, "slug", 0, 0, 7, 0, 0, 0, 0, 0, 15000, TraincraftUtil.getBytesFromColors(new String[]{"Skin19"}), 18, 0, (new String[] {"Reduces weight carried by 70 tons when fueled"})),

	//E-lect-trock//
	EF1("EF1",ElectricEF1.class, ItemIDs.minecartEF1.item, "electric", 1720, 64, 0, 55, 0, 200, 0.65, 0.75, 12500, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Yellow", "Red", "Green", "Black", "Grey"}), 10, -3.4375),
	EF1B("EF1B",ElectricEF1B.class, ItemIDs.minecartEF1B.item, "electric", 1720, 64, 0, 55, 0, 200, 0.65, 0.75, 12500, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Red", "Black", "Pink"}), 10, -2.3125),
	EP1A("EP1A",ElectricEP1A.class, ItemIDs.minecartEP1A.item, "electric", 2500, 120, 0, 55, 0, 200, 0.75, 0.85, 12500, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Red"}), 10, -3.4375),

	GM6C("GM6C",ElectricGM6C.class, ItemIDs.minecartGM6C.item, "electric", 6000, 115, 0, 55, 0, 200, 0.65, 0.79, 12500, null, 10, -3.8),

	ILMA("Loco Electric ILMA", ElectricILMA.class, ItemIDs.minecartILMA.item,"electric",400,40,0,45,0,400,0.9,0.946,0,TraincraftUtil.getBytesFromColors(new String[] {"Green", "Lime"}),18,-1),
	ILMB("Loco Electric ILMB", ElectricILMB.class, ItemIDs.minecartILMB.item,"electric",500,40,0,45,0,400,0.9,0.946,0,TraincraftUtil.getBytesFromColors(new String[] {"Brown", "Lime"}),18,-1),
	JT7("JT7", ElectricJT7.class, ItemIDs.minecartJT7.item,"electric",3900,90,0,85,0,200,0.7,0.7,0,TraincraftUtil.getBytesFromColors(new String[] {"Lime", "Green"}),18,-4),

	AEM7("AEM7", ElectricAEM7.class, ItemIDs.minecartAEM7.item,"electric",7000,201,0,10,0,200,0.9,0.946,0,TraincraftUtil.getBytesFromColors(new String[] {"LightGrey"}),10,-2),

	//FrERf//
	RoundHopper("Freight Round Covered Hopper", RoundHopper.class, ItemIDs.minecartRoundHopper.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[]{"Red", "Black", "White", "LightGrey"}), 18, 36,(new String[] {"Cargo: Any."})),
	RibbedHopper("Freight Ribbed Covered Hopper", RibbedHopper.class, ItemIDs.minecartRibbedHopper.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[]{"Grey", "Cyan", "LightGrey", "Red", "Brown", "White"}), 18, 36,(new String[] {"Cargo: Any."})),
	Hopper5201("5201 Cu Foot Hopper", Hopper5201.class, ItemIDs.minecartHopper5201.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[]{"Grey", "Cyan", "Black"}), 10, 36,(new String[] {"Cargo: Any."})),
	Hopper6260("6260 Cu Foot Jumbo Hopper", Hopper6260.class, ItemIDs.minecartHopper6260.item, "freight", 6, TraincraftUtil.getBytesFromColors(new String[]{"Grey", "LightGrey", "White"}), 10, 45,(new String[] {"Cargo: Any."})),
	FNCC2375Hopper("FNCC2375Hopper", FNCC2375Hopper.class, ItemIDs.minecartFNCC2375Hopper.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[]{"Cyan", "Green", "Black", "LightGrey", "Blue", "Skin16", "Skin17", "Purple", "Magenta", "Pink", "Yellow", "Red", "Skin18"}), 10, 27,(new String[] {"Cargo: Any."})),
	PDH2800("PDH2800", PDH2800.class, ItemIDs.minecartPDH2800.item, "freight", 3, TraincraftUtil.getBytesFromColors(new String[]{"LightGrey","Yellow","Grey","Magenta",}), 10, 27,(new String[] {"Cargo: Any."})),

	Freight60centerbeam("60centerbeam",  Freight60centerbeam.class, ItemIDs.minecart60centerbeam.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Yellow", "Green", "LightGrey"}), 10, 54, (new String[] {"Cargo: Woodthings."})),
	Freight66centerbeam("66centerbeam",  Freight66centerbeam.class, ItemIDs.minecart66centerbeam.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "Cyan", "Green", "Lime", "LightBlue", "Pink", "Blue", "Yellow", "Red", "Black"}), 0, 54, (new String[] {"Cargo: Woodthings."})),
	Freight73centerbeam("73centerbeam",  Freight73centerbeam.class, ItemIDs.minecart73centerbeam.item, "freight", 6, TraincraftUtil.getBytesFromColors(new String[] {"Pink", "Cyan", "Green", "Orange", "Yellow", "Red"}), 10, 54, (new String[] {"Cargo: Woodthings."})),
	GSI60FootBulkhead("GSI_Bulkhead", GSI60FootBulkhead.class, ItemIDs.minecartGSI60FootBulkhead.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Skin16", "Green", "Black", "Skin17", "Brown"}), 10, 45, (new String[] {"Cargo: Bulkhead Items."})),
	GSC60FootFlatcar("GSC_Flatcar", GSC60FootFlat.class, ItemIDs.minecartGSC60FootFlatcar.item, "freight", 3, TraincraftUtil.getBytesFromColors(new String[] {"LightBlue", "Orange", "Yellow", "Skin17", "Brown", "Green", "Skin16"}), 10, 45, (new String[] {"Cargo: whatever flatcars carry i guess"})),
	Thrall63centerbeam("63centerbeam",  Thrall63centerbeam.class, ItemIDs.minecartthrall63centerbeam.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[] {"Green", "Yellow", "Red", "Black", "Cyan", "Skin16"}), 10, 45, (new String[] {"Cargo: Woodthings."})),
	FNCC60FootBulk("FNCC60FootBulk", FNCC60FootBulk.class, ItemIDs.minecartFNCC60FootBulk.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[] {"Cyan", "Green"}), 10, 45, (new String[] {"Cargo: Bulkhead Stuff."})),

	PS140("PS140",  PS140.class, ItemIDs.minecartPS140.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[] {"Brown", "Red", "LightBlue", "Green", "Cyan", "Skin16", "Skin17", "Skin18", "Skin19", "Skin20", "Yellow", "Skin21", "Skin22", "Skin23"}), 10, 27, (new String[] {"Cargo: Any."})),
	PS150("PS150",  PS150.class, ItemIDs.minecartPS150.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[] {"White", "Lime", "Red", "Yellow", "Skin16", "Skin17"}), 10, 36, (new String[] {"Cargo: Any."})),
	PS160("PS160", PS160.class, ItemIDs.minecartPS160.item, "freight", 6, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Orange", "Cyan", "White"}), 10, 45, (new String[] {"Cargo: Any."})),
	OWO60Verticube("OWO 60 Verticube", OWO60Verticube.class, ItemIDs.minecartOWO60Verticube.item, "freight", 6, TraincraftUtil.getBytesFromColors(new String[] {"Cyan", "Lime", "Green", "Brown", "White"}), 10, 54, (new String[] {"Cargo: Any."})),
	MILW40boxcar("MILW40boxcar", MILW40boxcar.class, ItemIDs.minecartMILW40boxcar.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Brown", "Yellow", "Green", "Blue", "Purple", "Orange", "Lime"}), 10, 27, (new String[] {"Cargo: Any."})),
	Freight40highcube("40highcube", Freight40highcube.class, ItemIDs.minecart40highcube.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[] {"Brown", "Green", "Cyan", "Orange", "Grey", "Red", "Lime", "LightGrey", "Pink", "Purple", "Blue", "Black", "White", "Magenta", "Skin16"}), 0, 40, (new String[] {"Cargo: Any."})),
	Hicube60("60 Foot Hi-Cube Boxcar", Hicube60foot.class, ItemIDs.minecarthicube60foot.item, "freight", 7, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Red", "Pink", "Blue", "Skin16", "Skin17"}), 10, 54, (new String[] {"Cargo: Any."})),
	Reefer64("Reefer64", Reefer64Foot.class, ItemIDs.minecartReefer64.item, "freight", 6.5, TraincraftUtil.getBytesFromColors(new String[] {"White", "LightGrey", "Grey", "Orange", "Green", "Red", "Black", "Cyan", "Skin16", "Skin17"}), 10, 54, (new String[] {"Cargo: Any."})),
	FNCC50Foot("FNCC50FootBoxcar",  FNCC50Foot.class, ItemIDs.minecartFNCC50Foot.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[] {"Cyan", "Green", "Brown", "Pink", "Black", "Skin16", "Blue", "Skin22", "Skin21", "Yellow", "White"}), 0, 36, (new String[] {"Cargo: Any."})),

	VersaLongi("VersaLongi", VersaLongi.class, ItemIDs.minecartVersaLongi.item, "freight", 3, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Black", "Orange"}), 10, 36, (new String[] {"Cargo: Aggregates."})),
	VersaTrans("VersaTrans", VersaTrans.class, ItemIDs.minecartVersaTrans.item, "freight", 3, TraincraftUtil.getBytesFromColors(new String[] {"LightBlue", "Grey", "LightGrey"}), 10, 36, (new String[] {"Cargo: Aggregates."})),
	MillGondola("52footMillGondola", MillGondola.class, ItemIDs.minecartMillGondola.item, "freight", 3, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Black", "Orange", "Green", "Grey", "Skin16", "Blue", "Lime"}), 10, 50, (new String[] {"Cargo: Any."})),
	WoodchipHopper("Woodchip Hopper", WoodchipHopper.class, ItemIDs.minecartWoodchipHopper.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[] {"Brown", "Blue", "Green", "Orange", "Red", "White", "Cyan"}), 10, 50, (new String[] {"Cargo: Woodchips/Sawdust."})),
	OreJenny("Ore Jenny", OreJenny.class, ItemIDs.minecartOreJenny.item, "freight", 1, TraincraftUtil.getBytesFromColors(new String[] {"Brown", "Red", "Orange", "Pink", "Black"}), 0, 27, (new String[] {"Cargo: Ores."})),
	PRRGLaHopper("PRR GLa hopper", PRRGLaHopper.class, ItemIDs.minecartPRRGLaHopper.item, "freight", 1, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Black", "Yellow", "Magenta"}), 0, 27, (new String[] {"Cargo: Ores."})),


	DOT11111000( "DOT11000", DOT11111000.class, ItemIDs.minecart11000DOT111.item, "tank", 0, 0, 4, 0, 0, 0, 0, 0, 41000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "LightGrey", "Grey"}), 10, 0, (new String[] {"Capacity: 41000mb."})),
	DOT11120600( "DOT20600", DOT11120600.class, ItemIDs.minecart20600DOT111.item, "tank", 0, 0, 5, 0, 0, 0, 0, 0, 78000, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "Black", "White", "Green", "LightGrey"}), 10, 0, (new String[] {"Capacity: 78000mb."})),
	DOT11129080( "DOT29080", DOT11129080.class, ItemIDs.minecart29080DOT111.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 110000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Orange", "LightGrey", "White", "Grey", "Skin16", "Skin17"}), 10, 0, (new String[] {"Capacity: 110000mb."})),
	DOT11117600( "DOT17600", DOT11117600.class, ItemIDs.minecart17600DOT111.item, "tank", 0, 0, 4.5, 0, 0, 0, 0, 0, 67000, TraincraftUtil.getBytesFromColors(new String[] {"White", "Black", "Blue", "LightGrey", "Grey", "Yellow", "Brown", "Purple"}), 10, 0, (new String[] {"Capacity: 67000mb."})),
	NATX30600( "NATX30600", NATX30600.class, ItemIDs.minecartNATX30600.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 116000, TraincraftUtil.getBytesFromColors(new String[] {"Black", "Grey", "Green", "Red", "Yellow", "Pink", "Skin16"}), 10, 0, (new String[] {"Capacity: 116000mb."})),

	SkeletonCar("Skeleton", SkeletonLogCar.class, ItemIDs.minecartSkeletonLogCar.item, "freight", 1.5, TraincraftUtil.getBytesFromColors(new String[]{"Black","Grey", "LightGrey"}), 18, 18, (new String[] {"Cargo: Logs."})),
	EarlyFlat("EarlyFlat", EarlyFlat.class, ItemIDs.minecartEarlyFlat.item, "flat", 0, 0, 0.7, 0, 0, 0, 0, 0, 0, null, 18, 0),

	Gunderson89FootAutorack("89ftAutorack", Gunderson89ftAutorack.class, ItemIDs.minecartGunderson89ftAutorack.item, "freight", 7, TraincraftUtil.getBytesFromColors(new String[]{"Yellow"}), 10, 54, (new String[] {"Cargo: Any"})),
	Gunderson89FootFlat("89ftFlat", Gunderson89ftFlat.class, ItemIDs.minecartGunderson89ftFlat.item, "freight", 3, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Blue", "Purple"}), 10, 54, (new String[] {"Cargo: Any"})),
	StampedeRack("StampedeRack", StampedeRack.class, ItemIDs.minecartStampedeRack.item, "stock car", 0, 0, 5, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Brown"}), 10, 0),

	IngotPig("IngotPig", IngotPig.class, ItemIDs.minecartIngotPig.item, "freight", 0.5, TraincraftUtil.getBytesFromColors(new String[] {"Brown", "Black"}), 0, 9, (new String[] {"Cargo: Ingots."})),
	SteelSlabFlat("SteelSlabFlat", SteelSlabFlat.class, ItemIDs.minecartSteelSlabFlat.item, "freight", 1.5, TraincraftUtil.getBytesFromColors(new String[] {"Brown", "Black"}), 0, 9, (new String[] {"Cargo: steel and metal ig."})),

	//pAssenger//
	pch120commute("PCH-120 Commute", PCH120Commute.class, ItemIDs.minecartPCH120Commute.item, "electric",1400, 120, 0, 55, 0, 160, 0.8, 0.985, 0, TraincraftUtil.getBytesFromColors(new String[] {"White", "Blue", "Red", "Green","Cyan"}), 18, -2.7),
	pch120coach("PCH-120 Commute Car", PCH120Coach.class, ItemIDs.minecartPCH120Car.item, "passenger",0, 0, 2, 0, 0, 0, 0, 0, 0,TraincraftUtil.getBytesFromColors(new String[] {"White", "Blue", "Red", "Green", "Cyan"}), 0, 0),

	PSRPO("PSRPO", PSRPO.class, ItemIDs.minecartPSRPO.item, "freight", 1.5, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Blue", "Cyan"}), 18, 27,null),
	PS85Baggage("Freight PS 85 Baggage", PS85Baggage.class, ItemIDs.minecartPS85Baggage.item, "freight", 2, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Blue"}), 18, 27, null),
	PSCombine("PS Combine Coach", PSCombine.class, ItemIDs.minecartPScombine.item, "passenger/combine", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Yellow", "Orange", "LightGrey", "Grey", "White"}), 10, 0),
	PS52SeatCoach("PS 52 Seat Coach", PS52SeatCoach.class, ItemIDs.minecartPS52seatCoach.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "LightBlue", "Yellow", "Orange", "Cyan", "Grey", "LightGrey", "Purple", "Green", "Lime", "White"}), 10, 0),
	PSDamnitAnotherDiner("PS Center Diner 2", PSDamnitAnotherDiner.class, ItemIDs.minecartPSDamnitAnotherDiner.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Yellow", "Orange", "Blue", "Cyan"}), 10, 0),
	PSSleeper565("PS 5-6-5 Sleeper", PSSleeper565.class, ItemIDs.minecartPSSleeper565.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan", "Brown", "Orange", "LightBlue", "Grey", "Yellow", "LightGrey"}), 10, 0),
	PSSleeper565DRGW("PS 5-6-5 Sleeper DRGW Edition", PSSleeper565DRGW.class, ItemIDs.minecartPSSleeper565DRGW.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, null, 10, 0),
	PSLunchCounter_Lounge("PS Lunch Counter-Lounge", PSLunchCounter_Lounge.class, ItemIDs.minecartPSLunchCounter_Lounge.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan", "Yellow", "Orange", "LightBlue", "Purple", "LightGrey", "White"}), 10, 0, null),
	PS30SeatParlor("PS 30 Seat Parlor", PS30SeatParlor.class, ItemIDs.minecartPS30SeatParlor.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan", "LightBlue"}), 10, 0, null),

	PSRPOPM("PSPMRPO", PSRPOPM.class, ItemIDs.minecartPSRPOPM.item, "freight", 1.5, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Yellow"}), 18, 27,null),
	PS73Baggage("Freight PS 73 Baggage", PS73Baggage.class, ItemIDs.minecartPS73Baggage.item, "freight", 1.5, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Yellow", "Green", "Magenta", "Orange"}), 18, 27, null),
	PS54SeatCoach_Lounge("PS 54 Seat Coach-Lounge", PS54SeatCoach_Lounge.class, ItemIDs.minecartPS54SeatCoach_Lounge.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan", "LightGrey"}), 10, 0, null),
	PS54SeatCoach_Lounge_1950("PS 54 Seat Coach-Lounge 1950", PS54SeatCoach_Lounge_1950.class, ItemIDs.minecartPS54SeatCoach_Lounge_1950.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan"}), 10, 0, null),
	PScenterDiner("PS Center Diner", PScenterDiner.class, ItemIDs.minecartPSCenterDiner.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan"}), 10, 0),
	PS58SeatCoach_Observation("PS 58 Seat Coach-Observation", PS58SeatCoach_Observation.class, ItemIDs.minecartPS58SeatCoach_Observation.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan", "LightBlue", "Purple", "Orange", "Red", "Magenta", "Pink", "LightGrey", "Grey"}), 10, 0, null),

	PSBMCombine("PS B&M Combine", PSBMCombine.class, ItemIDs.minecartPSBMCombine.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Magenta", "Brown", "Green", "Blue"}), 10, 0, null),
	PSBM56SeatCoach("PS B&M 56 Seat Coach", PSBM56SeatCoach.class, ItemIDs.minecartPSBM56SeatCoach.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Magenta", "LightGrey", "Grey", "Brown", "Red", "Pink", "Green", "Lime", "Yellow", "Blue", "Cyan", "LightBlue", "Purple", "Black", "Orange", "White"}), 10, 0, null),
	PSBMDiner_Lounge("PS B&M Diner_Lounge", PSBMDiner_Lounge.class, ItemIDs.minecartPSBMDiner_Lounge.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Magenta", "Brown", "Green", "Blue", "Cyan", "LightGrey"}), 10, 0, null),

	ACFGNRPO_30("AC&F GN RPO (30' mail)", ACFGNRPO_30.class, ItemIDs.minecartACFGNRPO_30.item, "freight", 1.5, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "Green"}), 18, 27,null),
	ACFGN60SeatCoach("AC&F GN 60 Seat Coach", ACFGN60SeatCoach.class, ItemIDs.minecartACFGN60SeatCoach.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Orange", "Yellow", "Green", "LightGrey"}), 10, 0, null),
	ACFGNDinerObservation("AC&F GN Diner-Observation", ACFGNDinerObservation.class, ItemIDs.minecartACFGNDiner_Observation.item, "freight", 1.5, TraincraftUtil.getBytesFromColors(new String[]{"Orange",}), 18, 27,null),

	OB84SeatCoach("OB 84 Seat Coach", OB84SeatCoach.class, ItemIDs.minecartOB84SeatCoach.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Green", "Lime", "Grey", "LightBlue", "Yellow", "Magenta", "Red", "Pink", "Blue", "LightGrey"}), 10, 0, null),
	OB92SeatCoach("OB 92 Seat Coach", OB92SeatCoach.class, ItemIDs.minecartOB92SeatCoach.item, "passenger", 0, 0, 3, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Green", "Lime"}), 10, 0, null),

	amfleet("Amfleet", Amfleet.class, ItemIDs.minecartAmfleet.item, "passenger", 3, TraincraftUtil.getBytesFromColors(new String[] {"White", "Grey", "LightGrey", "LightBlue", "Red", "Blue"}), 10, 0, null),
	amfleet2("Amfleet2", Amfleet2.class, ItemIDs.minecartAmfleet2.item, "passenger", 3, TraincraftUtil.getBytesFromColors(new String[] {"Blue","LightGrey", "Grey"}), 10, 0, null),

	PEcoach("PEcoach", PEcoach.class, ItemIDs.minecartPEcooch.item, "festive passenger",  0, 0, 6, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan"}), 10, 0),
	PEobserve("PEobserve", PEobserve.class, ItemIDs.minecartPEobserve.item, "festive passenger",  0, 0, 6, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"Blue", "Cyan"}), 10, 0),

	//or as i like to call it the cabussy//
	WVcaboose("WVcaboose",WVcaboose.class, ItemIDs.minecartWVcaboose.item, "caboose", 2, TraincraftUtil.getBytesFromColors(new String[] {"Red", "Green", "Lime", "Cyan", "White", "Purple", "Skin16"}), 10, 0, null),
	HBC1C("HBC1C",HBC1Cboose.class, ItemIDs.minecartHBC1Ccaboose.item, "caboose", 2, TraincraftUtil.getBytesFromColors(new String[] {"Orange", "White", "LightGrey"}), 10, 0, null),
	DRGWCaboose("DRGWCaboose", DRGWboose.class, ItemIDs.minecartDRGWCaboose.item, "caboose", 2, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Grey", "Lime", "Yellow", "Black", "Red", "Orange", "Pink", "Skin16", "Purple"}), 10, 0, null),

	//meme
	BigMeme("BigMeme", BigMeme.class, ItemIDs.minecartBigMeme.item, "awesome", 1, 200, 0, 50, 0, 0, 0.9, 0.9, 8000, TraincraftUtil.getBytesFromColors(new String[] {"LightGrey", "Grey"}),10 , -1.1),
	BombCart("Payload", BombCart.class, ItemIDs.minecartBombCart.item, "misc", 1.5, TraincraftUtil.getBytesFromColors(new String[]{"LightBlue", "Red", "Black", "Blue", "Cyan", "Green", "Grey", "Brown", "LightGrey", "Lime", "Magenta", "Orange", "Pink", "Purple", "Yellow", "White"}), 0, 0, (new String[] {"Who is not pushing ze Cart!?"})),
	Thanos("Thanos", Thanos.class, ItemIDs.minecartThanos.item, "electric", 696969, 6210000, 0, 69, 0, 1, 6, 30, 0, null, 18, -1, (new String[] {"You don't want to mess with the Universe's Best"})),
	TGVmobile("TGVmobile", DieselTGVmobile.class, ItemIDs.minecartTGVMobile.item, "diesel", 200, 350, 0, 10, 0, 140, 0.9, 0.9, 1000,null,18, 2.7),
	HHgregg("HHgregg", DieselHHgregg.class, ItemIDs.minecartHHgregg.item, "awesome", 9932, 299, 0, 42, 0, 48, 0.9, 0.9, 7000, null, 10, -2, (new String[] {"hhhgregg, Panasonic Blu-ray nintey-nine dollars, thirty-two inch LCD TV two-nintey nine, LG fourty-two inch HD TV only four eighty-nine, everything on sale during chirstmas in"})),

	//dont speak americano
	//Class345("Class 345", ElectricClass345.class, ItemIDs.minecartClass345engine.item, "electric", 1500, 290, 0,6, 0, 100, 2, 1.5, 1000, null, 18, -3),
	//Class345Coach("Class345", Class345Coach.class, ItemIDs.minecartClass345car.item, "passenger", 0, 0, 1.5,0 , 0, 0, 0,0,0,null,0,0),
	//LUengine("London Underground Engine", ElectricLUengine.class, ItemIDs.minecartLUengine.item, "electric", 600, 100, 0, 10, 0, 100, 0.7, 0.8, 1000, null, 18, -2),
	//LUpassenger("London Underground Passenger Car", LUpassenger.class, ItemIDs.minecartLUcar.item, "passenger", 0.3, null, 0, 0, null),

	//old and extras
	geometryCar("NXTrack Geometry Car", ExperimentalGeometryCar.class, ItemIDs.minecartGeometryCar.item, "geometry car", 0, 0, 2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[] {"Grey", "LightGrey"}), 18, 0, (new String[] {"Checks the railroad for things up to standard"})),
	HighrailTruck("Highrail Truck", DieselHighrailTruck.class, ItemIDs.minecartHighrailTruck.item, "diesel", 90, 80, 0, 10, 0, 140, 0.7, 0.8, 1000,TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "White", "LightGrey"}),18, 2.7),
	FRED("FRED", FRED.class, ItemIDs.minecartFRED.item, "misc", 0.2, null, 0, 1, (new String[] {"the least used item in the mod"})),
	//HuskyStackWellcar("53' HuskyStack Wellcar", HuskyStackWellcar.class, ItemIDs.minecartHuskyStackWellcar.item, "freight", 4, TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Pink"}), 0,0, TraincraftUtil.getBytesFromColors(new String[] {"Model by Bidahochi"})),
	//testControlCar("Test Control Car", TestControlCar.class, ItemIDs.testControlCar.item, "passenger", 0, 0, 2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"White", "LightGrey"}), 18, 0),
	PCH100H("PCH-100H", PCH100H.class, ItemIDs.minecartExperimentalHydrogenTrain.item, "hydrogen",900, 100, 0, 6, 0, 160, 0.8, 0.985, 7000, TraincraftUtil.getBytesFromColors(new String[]{"LightBlue", "Blue"}), 18,  -2.6, (new String[] {"Powered by Mekanism Hydrogen"})),
	PCH100Coach("PCH-100H Coach", PCH100HCoach.class, ItemIDs.minecartPCH100HCoach.item, "passenger",0, 0, 2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"LightBlue", "Blue"}), 0, 0),
	aipkitExplorer("Aipkit Explorer", AipkitExplorer.class, ItemIDs.minecartAipkitExplorer.item, "passenger",0, 0, 2, 0, 0, 0, 0, 0, 0, null, 0, 0),
	aipkitExplorer2("Aipkit Explorer-II", AipkitExplorer2.class, ItemIDs.minecartAipkitExplorer2.item, "passenger",0, 0, 2, 0, 0, 0, 0, 0, 0, TraincraftUtil.getBytesFromColors(new String[]{"White", "Grey","Lime"}), 0, 0),
	Hustler("Hustler", DieselHustler.class, ItemIDs.minecartHustler.item, "diesel", 150, 30, 0, 50, 0, 170, 0.6, 0.7, 9000,  TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "Black", "Blue", "Brown"}), 10, -2),
	BoulderWagon("BoulderWagon", BoulderWagon.class, ItemIDs.minecartBoulderWagon.item, "freight", 5, TraincraftUtil.getBytesFromColors(new String[]{"Grey","skin16"}), 0, 9, null),
	LogcarNP("LogcarNP",LogcarNP.class, ItemIDs.minecartLogcarNP.item,"freight",2, TraincraftUtil.getBytesFromColors(new String[]{"Brown","Black"}), 0, 18, null),
	//CF7R("CF7R", DieselCF7R.class, ItemIDs.minecartCF7R.item, "diesel", 2350, 105, 0, 5, 0, 185, 0.8, 0.9, 7000, TraincraftUtil.getBytesFromColors(new String[] {"Blue", "Yellow", "Black"}), 10, -3, TraincraftUtil.getBytesFromColors(new String[] {"CF7 Rebuild Rebuilt"})),
	HighrailVan("Highrail Van", DieselHighrailVan.class, ItemIDs.minecartHighrailVan.item, "diesel", 90, 80, 0, 10, 0, 140, 0.7, 0.8, 1000,TraincraftUtil.getBytesFromColors(new String[] {"Yellow", "White", "Grey", "Orange", "Green"}),18, 2.7),
	;

//String trainType,int MHP,int maxSpeed, double mass, int fuelConsumption, int waterConsumption, int heatingTime, double accelerationRate, double brakeRate, int tankCapacity, String[] colors,

	private String internalName;
	private Class entityClass;
	private Item item;
	private String trainType;
	private int MHP;
	private int maxSpeed;
	private double mass;
	private int fuelConsumption;
	private int waterConsumption;
	private int heatingTime;
	private double accelerationRate;
	private double brakeRate;
	private int tankCapacity;
	private int[] colors;
	private int guiRenderScale;
	private double bogieLocoPosition;
	private String[] additionnalTooltip;
	private int cargoCapacity;

	/**
	 *
	 * @param internalName : Only used by EntityRegistry
	 * @param entityClass
	 * @param item
	 * @param trainType: "steam", "diesel", "freight", "passenger", "special", "flat", "electric"
	 * @param MHP: power of the locomotive
	 * @param maxSpeed
	 * @param mass (will be multiplied by 10 internally. That means putting 0.1 here will create a mass of 1 Ton in game)
	 * @param fuelConsumption: 1 unit is consumed every x ticks
	 * @param waterConsumption: 1 unit is consumed every x ticks
	 * @param heatingTime
	 * @param accelerationRate: generally around 0.45
	 * @param brakeRate: generally around 0.98
	 * @param tankCapacity
	 * @param colors: an array with all possible colors. Index 0 is used as default color when train is first spawned. leave null if no color available
	 * @param guiRenderScale: scale at which the entity will be rendered inside the GUI (crafting GUI)
	 */
	private EnumTrains(String internalName,Class entityClass, Item item, String trainType,int MHP,int maxSpeed, double mass, int fuelConsumption, int waterConsumption, int heatingTime, double accelerationRate, double brakeRate, int tankCapacity, int[] colors, int guiRenderScale, double bogieLocoPosition){
		this.internalName = internalName;
		this.entityClass = entityClass;
		this.item = item;
		this.trainType=trainType;
		this.MHP=MHP;
		this.maxSpeed=maxSpeed;
		this.mass=mass;
		this.fuelConsumption=fuelConsumption;
		this.waterConsumption=waterConsumption;
		this.heatingTime=heatingTime;
		this.accelerationRate=accelerationRate;
		this.brakeRate=brakeRate;
		this.tankCapacity=tankCapacity;
		this.colors=colors;
		this.guiRenderScale = guiRenderScale;
		this.bogieLocoPosition = bogieLocoPosition;
	}

	/**
	 * Constructor for additionnal tooltips on the item
	 * @param internalName
	 * @param entityClass
	 * @param item
	 * @param trainType
	 * @param MHP
	 * @param maxSpeed
	 * @param mass
	 * @param fuelConsumption
	 * @param waterConsumption
	 * @param heatingTime
	 * @param accelerationRate
	 * @param brakeRate
	 * @param tankCapacity
	 * @param colors
	 * @param guiRenderScale
	 * @param bogieLocoPositions
	 * @param additionnalTooltip
	 */
	private EnumTrains(String internalName,Class entityClass, Item item, String trainType,int MHP,int maxSpeed, double mass, int fuelConsumption,int waterConsumption, int heatingTime, double accelerationRate, double brakeRate, int tankCapacity, int[] colors, int guiRenderScale, double bogieLocoPositions, String[] additionnalTooltip){
		this.internalName = internalName;
		this.entityClass = entityClass;
		this.item = item;
		this.trainType=trainType;
		this.MHP=MHP;
		this.maxSpeed=maxSpeed;
		this.mass=mass;
		this.fuelConsumption=fuelConsumption;
		this.waterConsumption=waterConsumption;
		this.heatingTime=heatingTime;
		this.accelerationRate=accelerationRate;
		this.brakeRate=brakeRate;
		this.tankCapacity=tankCapacity;
		this.colors=colors;
		this.guiRenderScale = guiRenderScale;
		this.bogieLocoPosition = bogieLocoPositions;
		this.additionnalTooltip=additionnalTooltip;
	}

	/**
	 * Constructor for freight carts
	 * @param internalName
	 * @param entityClass
	 * @param item
	 * @param trainType
	 * @param mass
	 * @param colors
	 * @param guiRenderScale
	 * //@param bogieLocoPositions
	 * //@param bogieUtilityPositions
	 * @param cargoCapacity
	 * @param additionnalTooltip
	 */
	private EnumTrains(String internalName,Class entityClass, Item item, String trainType, double mass, int[] colors, int guiRenderScale, int cargoCapacity, String[] additionnalTooltip){
		this.internalName = internalName;
		this.entityClass = entityClass;
		this.item = item;
		this.trainType=trainType;
		this.mass=mass;
		this.colors=colors;
		this.guiRenderScale = guiRenderScale;
		this.additionnalTooltip=additionnalTooltip;
		this.cargoCapacity = cargoCapacity;
	}

	public String getInternalName(){
		return this.internalName;
	}

	public Item getItem(){
		return this.item;
	}

	public String getTrainType(){
		return this.trainType;
	}

	public int getMHP(){
		return this.MHP;
	}

	public int getMaxSpeed(){
		return this.maxSpeed;
	}

	public double getMass(){
		return this.mass;
	}

	public int getFuelConsumption(){
		return this.fuelConsumption;
	}

	public int getWaterConsumption(){
		return this.waterConsumption;
	}

	public int getHeatingTime(){
		return this.heatingTime;
	}

	public double getAccelerationRate(){
		return this.accelerationRate;
	}

	public double getBrakeRate(){
		return this.brakeRate;
	}

	public int getTankCapacity(){
		return this.tankCapacity;
	}

	public int[] getColors(){
		return this.colors;
	}

	public double getBogieLocoPosition(){
		return this.bogieLocoPosition;
	}


	public Class getEntityClass() {
		return this.entityClass;
	}

	public int getGuiRenderScale(){
		return this.guiRenderScale;
	}

	public String[] getAdditionnalTooltip(){
		return this.additionnalTooltip;
	}

	public int getCargoCapacity(){
		return cargoCapacity;
	}

	public static EnumTrains getCurrentTrain(Item item){
		if(item==null)return null;
		for (EnumTrains trains : EnumTrains.values()) {
			if(trains!=null && trains.getItem()!=null && trains.getItem() == item){
				return trains;
			}
		}
		return null;
	}

	public static AbstractTrains getEntityWithItem(Item item, World world, double x, double y, double z){
		if(item==null)return null;
		for (EnumTrains trains : EnumTrains.values()) {
			if(trains!=null && trains.getItem()!=null && trains.getItem() == item){
				return trains.getEntity(world, x, y, z);
			}
		}
		return null;
	}

	public AbstractTrains getEntity(World world){
		try {
			return (AbstractTrains) entityClass.getConstructor(World.class).newInstance(world);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AbstractTrains getEntity(World world, double x, double y, double z){
		try {
			if(world.isRemote){
				entityClass.getConstructor(World.class).newInstance(world);
			} else {
				return (AbstractTrains) entityClass.getConstructor(World.class, double.class, double.class, double.class).newInstance(world, x, y, z);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}
