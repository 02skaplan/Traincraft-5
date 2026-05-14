package train.common.entity.cargoManagerProvider;

import tmt.ModelBase;
import tmt.ModelConverter;

public class CargoModelClassProvider implements ICargoModelProvider {

    private final Class<? extends ModelConverter> modelClass;
    private ModelBase model;

    public CargoModelClassProvider(Class<? extends ModelConverter> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public ModelBase getModel() {
        if (model == null && modelClass != null) {
            try {
                model = modelClass.newInstance();
            } catch (Exception e) {
                System.err.println("[FoxTC] Failed to create cargo model: " + modelClass);
                e.printStackTrace();
            }
        }

        return model;
    }
}
