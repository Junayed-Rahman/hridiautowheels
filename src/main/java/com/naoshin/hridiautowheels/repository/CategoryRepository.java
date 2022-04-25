package com.naoshin.hridiautowheels.repository;


import com.naoshin.hridiautowheels.model.CarCategory;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CarCategory, Integer> {

}
