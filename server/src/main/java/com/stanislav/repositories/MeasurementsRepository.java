package com.stanislav.repositories;

import com.stanislav.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Hlova
 */
@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement,Integer> {

}
