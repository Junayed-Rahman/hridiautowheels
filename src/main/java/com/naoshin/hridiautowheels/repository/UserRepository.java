package com.naoshin.hridiautowheels.repository;


import com.naoshin.hridiautowheels.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmailAndPassword(String userEmail, String userPassword);
}
