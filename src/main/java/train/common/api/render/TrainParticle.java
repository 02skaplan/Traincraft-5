package train.common.api.render;

public class TrainParticle
{
    public String type;
    public int density;
    public double[] position;

    public TrainParticle(String type, int density, double[] position){
        this.type=type;
        this.density=density;
        this.position=position;
    }
}

