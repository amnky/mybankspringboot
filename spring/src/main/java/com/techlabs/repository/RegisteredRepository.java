package com.techlabs.repository;

import com.techlabs.entity.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredRepository extends JpaRepository<Registered, Integer> {
}
