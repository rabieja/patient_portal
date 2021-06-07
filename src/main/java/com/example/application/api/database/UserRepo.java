package com.example.application.api.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
     AppUser getByUsername(String name);
}
