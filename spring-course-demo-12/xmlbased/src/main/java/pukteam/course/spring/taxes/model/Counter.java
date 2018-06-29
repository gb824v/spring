package pukteam.course.spring.taxes.model;

public class Counter {

    private int count;

    public Counter() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void increaseCounter() {
        count++;
    }
}
