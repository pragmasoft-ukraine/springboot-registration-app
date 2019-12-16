package com.oliinyk.registration.repositories;

import com.oliinyk.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}
