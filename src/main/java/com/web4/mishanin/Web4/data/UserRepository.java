package com.web4.mishanin.Web4.data;

import com.web4.mishanin.Web4.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    @Override
    <S extends User> S save(S s);
}
