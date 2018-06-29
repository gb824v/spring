package pukteam.course.spring.taxes.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class TaxLimit implements Comparable<TaxLimit>, InitializingBean, DisposableBean{

    private static Logger logger = LogManager.getLogger(TaxLimit.class);
    private int lowerBoundIncome;
    private int taxPercent;

    public TaxLimit(int lowerBoundIncome, int taxPercent) {
        this.lowerBoundIncome = lowerBoundIncome;
        this.taxPercent = taxPercent;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Creating tax limit successfully with lower income of {} and tax percent of {}", lowerBoundIncome, taxPercent);
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


    @Override
    public void destroy() throws Exception {
        logger.info("Dstroying bean {}", this.toString());
    }

}
