package com.naoshin.hridiautowheels.repository;


import com.naoshin.hridiautowheels.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query("FROM Car as p WHERE p.carCategory.id =:n")
    public List<Car> findProductsByCategoryId(@Param("n")int categoryID);
}
