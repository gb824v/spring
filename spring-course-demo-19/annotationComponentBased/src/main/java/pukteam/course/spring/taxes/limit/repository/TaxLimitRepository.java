package pukteam.course.spring.taxes.limit.repository;

import pukteam.course.spring.taxes.limit.TaxLimit;

import java.util.stream.Stream;

public interface TaxLimitRepository {
    int size();
    Stream<TaxLimit> stream();
}
