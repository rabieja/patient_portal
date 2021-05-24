package com.example.application.api.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VisitRepo extends CrudRepository<Visit, Long> {
}
