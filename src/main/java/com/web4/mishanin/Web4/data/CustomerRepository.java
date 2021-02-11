package com.web4.mishanin.Web4.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<UsersPoint, Integer> {

    @Override
    Iterable<UsersPoint> findAll();
    <S extends UsersPoint> S save(S s);
    @Override
    void deleteAll();
    void deleteUsersPointsByOwner(String owner);
}
