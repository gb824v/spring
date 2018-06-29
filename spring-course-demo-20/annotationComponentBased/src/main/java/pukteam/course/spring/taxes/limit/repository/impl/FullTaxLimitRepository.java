package pukteam.course.spring.taxes.limit.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pukteam.course.spring.taxes.limit.TaxLimit;
import pukteam.course.spring.taxes.limit.repository.TaxLimitRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class FullTaxLimitRepository implements TaxLimitRepository {

    private static Logger logger = LogManager.getLogger(FullTaxLimitRepository.class);

    @Autowired
    private List<TaxLimit> taxLimits;

    @PostConstruct
    void init() {
        if (taxLimits == null || size() < 2) {
            logger.error("Error on tax limit repository ! It seems that there are no defined limits");
        }
    }
    @Override
    public int size() {
        return taxLimits.size();
    }

    @Override
    public Stream<TaxLimit> stream() {
        return taxLimits.stream();
    }
}
