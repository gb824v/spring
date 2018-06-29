package pukteam.course.spring.fullcontrol.component;

public class Wheel {

    private int size;
    private int pressure;

    public Wheel() {
        size = 50;
        pressure = 35;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "size=" + size +
                ", pressure=" + pressure +
                '}';
    }
}
