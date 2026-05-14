package train.common.entity.cargoManagerProvider;

import tmt.ModelBase;

public abstract class LazyCargoModelProvider implements ICargoModelProvider {

    private ModelBase model;

    @Override
    public ModelBase getModel() {
        if (model == null) {
            model = createModel();
        }

        return model;
    }

    protected abstract ModelBase createModel();
}
