package com.example.application.api.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {

    @Query("select c from Visit c " +
            "where lower(c.doctor) like lower(concat('%', :searchTerm, '%')) " )
    List<Visit> search(@Param("searchTerm")  String username);

    @Query("select c from Visit c " +
            "where lower(c.doctor) like lower(concat('%', :searchTerm, '%')) " +
            "and lower(c.status) like lower(concat('%', :free, '%'))")
    List<Visit> searchFree(@Param("searchTerm")  String username, @Param("free") String free);



    @Query("select c from Visit c " +
            "where lower(c.date) like lower(concat('%', :searchTerm, '%')) " )
    List<Visit> xxx(@Param("searchTerm")  String username);

    @Query("select c from Visit c " +
            "where lower(c.patient) like lower(concat('%', :searchTerm, '%')) " )
    List<Visit> searchPatientVisit(@Param("searchTerm") String username);
}

