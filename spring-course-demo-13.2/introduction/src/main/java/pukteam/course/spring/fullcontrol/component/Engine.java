package pukteam.course.spring.fullcontrol.component;

public class Engine {
    private int volume;
    private int cylinder;

    public Engine() {
        this.volume = 1000;
        this.cylinder = 6;
    }

    public int getVolume() {
        return volume;
    }

    public int getCylinder() {
        return cylinder;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "volume=" + volume +
                ", cylinder=" + cylinder +
                '}';
    }
}
