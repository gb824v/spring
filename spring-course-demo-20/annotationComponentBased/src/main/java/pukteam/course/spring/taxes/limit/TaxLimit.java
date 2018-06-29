package pukteam.course.spring.taxes.limit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class TaxLimit implements Comparable<TaxLimit>, InitializingBean, DisposableBean{

    private static Logger logger = LogManager.getLogger(TaxLimit.class);
    private int boundedIncome;
    private int taxPercent;

    public TaxLimit() {
    }

    public TaxLimit(int boundedIncome, int taxPercent) {
        this.boundedIncome = boundedIncome;
        this.taxPercent = taxPercent;
    }

    public void setBoundedIncome(int boundedIncome) {
        this.boundedIncome = boundedIncome;
    }

    public void setTaxPercent(int taxPercent) {
        this.taxPercent = taxPercent;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Creating tax limit successfully with lower income of {} and tax percent of {}", boundedIncome, taxPercent);
    }

    public int getBoundedIncome() {
        return boundedIncome;
    }

    public int getTaxPercent() {
        return taxPercent;
    }

    public int compareTo(TaxLimit other) {
        return this.boundedIncome - other.boundedIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxLimit taxLimit = (TaxLimit) o;

        return boundedIncome == taxLimit.boundedIncome;
    }

    @Override
    public int hashCode() {
        return boundedIncome;
    }

    @Override
    public String toString() {
        return "TaxLimit{" +
                "boundedIncome=" + boundedIncome +
                ", taxPercent=" + taxPercent +
                '}';
    }


    @Override
    public void destroy() throws Exception {
        logger.info("Dstroying bean {}", this.toString());
    }

}
