package com.dawid.exchangechallenge.repository;

import com.dawid.exchangechallenge.data.CurrencyConversionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrincesRepository extends JpaRepository<CurrencyConversionVO,Long> {
}
