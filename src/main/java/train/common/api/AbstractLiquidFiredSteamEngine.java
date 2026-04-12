package train.common.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import train.common.api.LiquidManager;
import train.common.api.Locomotive;
import train.common.api.Tender;

public abstract class AbstractLiquidFiredSteamEngine extends Locomotive implements IFluidHandler
{
    private LiquidManager.StandardTank coolantTank;
    private LiquidManager.FilteredTank fuelTank;

    public AbstractLiquidFiredSteamEngine(World world, FluidStack filter)
    {
        super(world);
        coolantTank = LiquidManager.getInstance().new FilteredTank(getCoolantTankCapacity(), filter);
        fuelTank = LiquidManager.getInstance().new FilteredTank(getFuelTankCapacity(), filter);
    }

    /* ------------------------------------------------------------
     * CONFIG
     * ------------------------------------------------------------ */

    public abstract int getCoolantTankCapacity();

    public abstract int getFuelTankCapacity();

    public abstract int getCoolantConsumptionRate();

    public abstract int getFuelConsumptionRate();

    /* ------------------------------------------------------------
     * UPDATE LOOP
     * ------------------------------------------------------------ */

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (worldObj.isRemote)
            return;

        // Sync to client
        dataWatcher.updateObject(23, coolantTank.getFluidAmount());
        dataWatcher.updateObject(24, fuelTank.getFluidAmount());

        transferFluidsFromTender();

        // Consume fuel + water
        if (isRunning()) {
            consumeFluids();
        }

        // Slow down if empty
        if (fuelTank.getFluidAmount() <= 0 || coolantTank.getFluidAmount() <= 0) {
            motionX *= 0.9;
            motionZ *= 0.9;
        }
    }

    protected boolean isRunning() {
        return fuelTank.getFluidAmount() > 0 && coolantTank.getFluidAmount() > 0;
    }

    protected void consumeFluids()
    {
        if (rand.nextInt(100) == 0)
        {
            coolantTank.drain(getCoolantConsumptionRate() / 5, true);
        }
        if (ticksExisted % 5 == 0) {

            fuelTank.drain(getFuelConsumptionRate(), true);
        }
    }

    protected void transferFluidsFromTender() {

        if (ticksExisted % 10 != 0)
            return;

        if (!(cartLinked1 instanceof Tender) && !(cartLinked2 instanceof Tender))
            return;

        Tender tender = null;

        if (cartLinked1 instanceof Tender)
            tender = (Tender) cartLinked1;
        else if (cartLinked2 instanceof Tender)
            tender = (Tender) cartLinked2;

        if (tender == null)
            return;

        // ----------------------------
        // Transfer WATER
        // ----------------------------
        if (coolantTank.getFluidAmount() < coolantTank.getCapacity()) {

            FluidStack simulatedDrain =
                    tender.drain(ForgeDirection.UNKNOWN,
                            new FluidStack(FluidRegistry.WATER, 200),
                            false);

            if (simulatedDrain != null && simulatedDrain.amount > 0) {

                int filled = coolantTank.fill(simulatedDrain, true);

                tender.drain(ForgeDirection.UNKNOWN,
                        new FluidStack(FluidRegistry.WATER, filled),
                        true);
            }
        }

        // ----------------------------
        // Transfer FUEL
        // ----------------------------
        if (fuelTank.getFluidAmount() < fuelTank.getCapacity()) {

            FluidStack tenderFuel =
                    tender.drain(ForgeDirection.UNKNOWN, 200, false);

            if (tenderFuel != null &&
                    tenderFuel.getFluid() != FluidRegistry.WATER) {

                int filled = fuelTank.fill(tenderFuel, true);

                tender.drain(ForgeDirection.UNKNOWN, filled, true);
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        this.fuelTank.writeToNBT(nbttagcompound);
        this.coolantTank.writeToNBT(nbttagcompound);
        nbttagcompound.setBoolean("canBeAdjusted", canBeAdjusted);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.fuelTank.readFromNBT(nbttagcompound);
        this.coolantTank.readFromNBT(nbttagcompound);
        canBeAdjusted = nbttagcompound.getBoolean("canBeAdjusted");
    }

    /* ------------------------------------------------------------
     * FLUID HANDLER
     * ------------------------------------------------------------ */

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (resource == null)
            return 0;

        // Water goes to water tank
        if (resource.getFluid() == FluidRegistry.WATER) {
            return coolantTank.fill(resource, doFill);
        }

        // Everything else goes to fuel tank
        return fuelTank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null)
            return null;

        if (resource.getFluid() == FluidRegistry.WATER) {
            return coolantTank.drain(resource.amount, doDrain);
        }

        return fuelTank.drain(resource.amount, doDrain);
    }


    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null; // Not used
    }
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { coolantTank.getInfo(), fuelTank.getInfo() };
    }
}
