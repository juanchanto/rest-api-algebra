package com.algebra.restapialgebra.repository;

import com.algebra.restapialgebra.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long>{
}
