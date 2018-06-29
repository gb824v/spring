package pukteam.course.spring.di.impl.wheel;

import pukteam.course.spring.di.api.Wheel;

public class RaceWheel implements Wheel{

    private int size;
    private int pressure;

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
        return "RaceWheel{" +
                "size=" + size +
                ", pressure=" + pressure +
                '}';
    }
}
