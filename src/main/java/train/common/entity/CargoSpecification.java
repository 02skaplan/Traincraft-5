package train.common.entity;

import tmt.ModelBase;
import tmt.ModelConverter;
import train.common.entity.cargoManagerProvider.CargoModelClassProvider;
import train.common.entity.cargoManagerProvider.ICargoModelProvider;
import train.common.library.Info;

import java.util.LinkedList;

/**
 * @author 02skaplan
 * <p>Stores information about an individual custom load's model, texture, offset, rotation, and scale.</p>
 * <p>Models may have more than one CargoSpecification, stored in the cargoSpecificationList of its entity's CargoManager.</p>
 */
public class CargoSpecification
{
    private final ICargoModelProvider modelProvider;
    public final String resourceDomain;
    public final String textureFile;
    public final String textureName;
    public final RenderParameters renderParameters;

    /**
     * @author 02skaplan
     * <p>Nested class to store information concerning the rendering of the cargo specification model.</p>
     * <p>Contains provisions for render offsets, scales, and rotations.</p>
     */
    public static class RenderParameters {

        /**
         * @author 02skaplan
         * <p>Nested sub-class to store rotation data for render parameters.</p>
         */
        public static class Rotation {

            public final double rotateAngle;
            public final boolean rotateX;
            public final boolean rotateY;
            public final boolean rotateZ;

            public Rotation(double rotateAngle, boolean rotateX, boolean rotateY, boolean rotateZ) {
                this.rotateAngle = rotateAngle;
                this.rotateX = rotateX;
                this.rotateY = rotateY;
                this.rotateZ = rotateZ;
            }

        }

        private double offsetX;
        private double offsetY;
        private double offsetZ;
        private double scaleX;
        private double scaleY;
        private double scaleZ;
        private final LinkedList<Rotation> rotations;


        public RenderParameters() {
            offsetX = 0D;
            offsetY = 0D;
            offsetZ = 0D;
            scaleX = 0D;
            scaleY = 0D;
            scaleZ = 0D;
            rotations = new LinkedList<>();
        }

        /**
         * @author 02skaplan
         * @param offsetX X offset relative to host model origin. 0D is default.
         * @param offsetY Y offset relative to host model origin. 0D is default.
         * @param offsetZ Z offset relative to host model origin. 0D is default.
         */
        public RenderParameters setOffset(double offsetX, double offsetY, double offsetZ) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            return this;
        }

        /**
         * @author 02skaplan
         * @param scaleX X dimension scale. 0D is default. Negative values make the model smaller in this dimension; larger values make the model larger in this dimension.
         * @param scaleY Y dimension scale. 0D is default. Negative values make the model smaller in this dimension; larger values make the model larger in this dimension.
         * @param scaleZ Z dimension scale. 0D is default. Negative values make the model smaller in this dimension; larger values make the model larger in this dimension.
         */
        public RenderParameters setScale(double scaleX, double scaleY, double scaleZ) {
            this.scaleX = scaleX;
            this.scaleY = scaleY;
            this.scaleZ = scaleZ;
            return this;
        }


        /**
         * @author 02skaplan
         * <p>Adds a set of rotation parameters to the list of rotations stored in the cargo specification.</p>
         * <p>This method can be called multiple times to add multiple rotations due to the special nature of GL11 rotation provisions.</p>
         * @param rotateAngle Angle by which to rotate the model. 0D is default.
         * @param rotateX Boolean whether the given rotation angle will be applied about the X-axis. False is default.
         * @param rotateY Boolean whether the given rotation angle will be applied about the Y-axis. False is default.
         * @param rotateZ Boolean whether the given rotation angle will be applied about the Z-axis. False is default.
         */
        public RenderParameters addRotation(double rotateAngle, boolean rotateX, boolean rotateY, boolean rotateZ) {
            rotations.add(new Rotation(rotateAngle, rotateX, rotateY, rotateZ));
            return this;
        }

        public double getOffsetX() {
            return offsetX;
        }

        public double getOffsetY() {
            return offsetY;
        }

        public double getOffsetZ() {
            return offsetZ;
        }

        public double getScaleX() {
            return scaleX;
        }

        public double getScaleY() {
            return scaleY;
        }

        public double getScaleZ() {
            return scaleZ;
        }

        public LinkedList<Rotation> getRotations() {
            return rotations;
        }
    }

    /**
     * @author 02skaplan
     * <p>Stores information about an individual custom load's model, texture, and offset.</p>
     * <p>Models may have more than one CargoSpecification, stored in the cargoSpecificationList of its entity's CargoManager.</p>
     * @param cargoModelClass Model class of the cargo.
     * @param textureFile Texture file of the cargo. ".PNG" extention is assumed. Ex. "loads/MixedLogs_Oak"
     * @param textureName Texture name of the cargo. Used for GUI display purposes only.
     * @param offsetX X offset relative to host model origin.
     * @param offsetY Y offset relative to host model origin.
     * @param offsetZ Z offset relative to host model origin.
     */

    @Deprecated
    public CargoSpecification(Class<? extends ModelConverter> cargoModelClass, String textureFile, String textureName, double offsetX, double offsetY, double offsetZ) {
        this.modelProvider = new CargoModelClassProvider(cargoModelClass);
        this.textureFile = textureFile;
        this.textureName = textureName;
        this.renderParameters = new RenderParameters();
        this.renderParameters.setOffset(offsetX, offsetY, offsetZ);
        resourceDomain = Info.resourceLocation;
    }

    @Deprecated
    public CargoSpecification(Class<? extends ModelConverter> cargoModelClass, String textureFile, String textureName, double offsetX, double offsetY, double offsetZ, double scaleX, double scaleY, double scaleZ) {
        this.modelProvider = new CargoModelClassProvider(cargoModelClass);
        this.textureFile = textureFile;
        this.textureName = textureName;
        this.renderParameters = new RenderParameters();
        this.renderParameters.setOffset(offsetX, offsetY, offsetZ);
        this.renderParameters.setScale(scaleX, scaleY, scaleZ);
        resourceDomain = Info.resourceLocation;
    }


    public CargoSpecification(Class<? extends ModelConverter> cargoModelClass, String textureFile, String textureName, RenderParameters renderParameters) {
        this.modelProvider = new CargoModelClassProvider(cargoModelClass);
        this.textureFile = textureFile;
        this.textureName = textureName;
        this.renderParameters = renderParameters;
        resourceDomain = Info.resourceLocation;

    }

    /**
     * Designed For Addon Pack Compatability
     * @param cargoModelClass
     * @param resourceDomain Domain IE tc
     * @param textureFile
     * @param textureName
     * @param renderParameters
     */
    public CargoSpecification(Class<? extends ModelConverter> cargoModelClass, String resourceDomain, String textureFile, String textureName, RenderParameters renderParameters) {
        this.modelProvider = new CargoModelClassProvider(cargoModelClass);
        this.resourceDomain = resourceDomain;
        this.textureFile = textureFile;
        this.textureName = textureName;
        this.renderParameters = renderParameters;
    }

    public CargoSpecification(
            ICargoModelProvider modelProvider,
            String textureFile,
            String textureName,
            RenderParameters renderParameters
    ) {
        this(
                modelProvider,
                Info.resourceLocation,
                textureFile,
                textureName,
                renderParameters
        );
    }

    public CargoSpecification(
            ICargoModelProvider modelProvider,
            String resourceDomain,
            String textureFile,
            String textureName,
            RenderParameters renderParameters
    ) {
        this.resourceDomain = resourceDomain;
        this.textureFile = textureFile;
        this.textureName = textureName;
        this.renderParameters = renderParameters;
        this.modelProvider = modelProvider;
    }

    public ModelBase getModel() {
        if (modelProvider == null) {
            return null;
        }

        return modelProvider.getModel();
    }
}