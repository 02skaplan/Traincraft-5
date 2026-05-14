package train.common.entity.cargoManagerProvider;

import tmt.ModelBase;

public class CargoModelInstanceProvider implements ICargoModelProvider {

    private final ModelBase model;

    public CargoModelInstanceProvider(ModelBase model) {
        this.model = model;
    }

    @Override
    public ModelBase getModel() {
        return model;
    }
}
