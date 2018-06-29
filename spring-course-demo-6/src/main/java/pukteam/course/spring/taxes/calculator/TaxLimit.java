package pukteam.course.spring.taxes.calculator;

public class TaxLimit implements Comparable<TaxLimit>{

    private int lowerBoundIncome;
    private int taxPercent;

    public TaxLimit(int lowerBoundIncome, int taxPercent) {
        this.lowerBoundIncome = lowerBoundIncome;
        this.taxPercent = taxPercent;
    }

    public int getLowerBoundIncome() {
        return lowerBoundIncome;
    }

    public int getTaxPercent() {
        return taxPercent;
    }

    public int compareTo(TaxLimit other) {
        return this.lowerBoundIncome - other.lowerBoundIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxLimit taxLimit = (TaxLimit) o;

        return lowerBoundIncome == taxLimit.lowerBoundIncome;
    }

    @Override
    public int hashCode() {
        return lowerBoundIncome;
    }

    @Override
    public String toString() {
        return "TaxLimit{" +
                "lowerBoundIncome=" + lowerBoundIncome +
                ", taxPercent=" + taxPercent +
                '}';
    }
}
