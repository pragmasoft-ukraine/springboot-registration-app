package com.oliinyk.registration.repositories;

import com.oliinyk.registration.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsaStateRepository extends JpaRepository<State, Integer> {
}
