package pukteam.course.spring.taxes.limit.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pukteam.course.spring.taxes.limit.TaxLimit;
import pukteam.course.spring.taxes.limit.repository.TaxLimitRepository;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@Repository
public class LightTaxLimitRepository implements TaxLimitRepository {

    private static Logger logger = LogManager.getLogger(LightTaxLimitRepository.class);

    @Autowired
    @Qualifier("middleTaxRate")
    private TaxLimit taxLimit;

    @PostConstruct
    void init() {
        if (taxLimit == null) {
            logger.error("Error on tax limit repository ! It seems that there are no defined limits");
        }
    }
    @Override
    public int size() {
        return 1;
    }

    @Override
    public Stream<TaxLimit> stream() {
        return Stream.of(taxLimit);
    }
}
