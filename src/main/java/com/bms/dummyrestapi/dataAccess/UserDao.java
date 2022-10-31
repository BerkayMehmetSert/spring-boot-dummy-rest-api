package com.bms.dummyrestapi.dataAccess;

import com.bms.dummyrestapi.entities.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDao extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    boolean existsByFirstNameIgnoreCase(String firstName);

    List<User> findByFirstNameIgnoreCase(String firstName);
}
