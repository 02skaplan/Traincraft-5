package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tanker;

import com.jcirmodelsquad.tcjcir.models.loads.Modelhazmat_plac_natx30600;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;


public class NATX30600 extends AbstractStandardTankerCar
{
    public NATX30600(World world) {
        super(world);
        
        setCargoManager(new CargoManager(new CargoSpecification[][] {
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_flammable_number",
                        "Hazmat Placard - Flammable (Bluk)", 0, 3.0, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_flammable",
                        "Hazmat Placard - Flammable", 0, 3.0, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_corrosive_number",
                        "Hazmat Placard - Corrosive (Bulk)", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_hot",
                        "Hazmat Placard - HOT", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_oxidizer",
                        "Hazmat Placard - Oxidizer/Oxygen", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_class9_numbers",
                        "Hazmat Placard - Class 9 (Bulk)", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_class9",
                        "Hazmat Placard - Class 9", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_class6",
                        "Hazmat Placard - Class 6 (Poision, PG III, Toxic)", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_wet",
                        "Hazmat Placard - Dangerous when Wet", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_explosives",
                        "Hazmat Placard - Explosives", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_natx30600.class, "loads/hazmat_plac_radioactive",
                        "Hazmat Placard - Radioactive", 0, 3, 0)},
        }));
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "NATX");
        InsertTexture(1, "NATX (National Byproducts)");
        InsertTexture(2, "NATX (Monoco)");
        InsertTexture(3, "NATX (America's 100 Years Bicentennial)");
        InsertTexture(4, "NATX (Cider something)");
        InsertTexture(5, "NATX (Indiana Trash Society)");
        InsertTexture(6, "Magnolia");
        InsertTexture(7, "CCOX");
        InsertTexture(8, "PCTX PENN CENTRAL WOO OH YEAH WOO");
        InsertTexture(9, "DLMR");
    }

    @Override
    public String getInventoryName() {
        return "NATX 30,600 Gallon Tank car";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.75F;
    }

}