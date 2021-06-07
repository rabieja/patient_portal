package com.example.application.api.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor getByUsername(String name);

    @Query("select c from Doctor c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "and lower(c.lastName) like lower(concat('%', :lastNameTerm, '%'))" +
            "and lower(c.specialisation) like lower(concat('%', :specTerm, '%'))" +
            "and lower(c.clinicName) like lower(concat('%', :clinicTerm, '%'))"  )
    List<Doctor> search(@Param("searchTerm") String searchTerm, @Param("lastNameTerm") String lastNameTerm,
                        @Param("specTerm") String specTerm, @Param("clinicTerm") String clinicTerm);
}
