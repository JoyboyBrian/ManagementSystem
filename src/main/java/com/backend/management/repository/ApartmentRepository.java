package com.backend.management.repository;

import com.backend.management.model.Apartment;
import com.backend.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, String> {

    @Query("SELECT a FROM Apartment a LEFT JOIN User u ON u.apartmentNumber = a.apartmentNumber WHERE u.apartmentNumber = NULL")
    List<Apartment> apartmentsWithVacancy();

    @Query("SELECT a,u FROM Apartment a JOIN User u ON a.apartmentNumber = u.apartmentNumber.apartmentNumber WHERE a.apartmentNumber = :apartment_number")
    public List<Object[]> getApartmentAndTenantByApartmentNumber(String apartment_number);

    @Query("SELECT a, u2 FROM User u1 JOIN Apartment a ON u1.apartmentNumber.apartmentNumber = a.apartmentNumber JOIN User u2 ON a.apartmentNumber = u2.apartmentNumber.apartmentNumber WHERE u1.username = :username")
    public List<Object[]> getApartmentAndTenantByUsername(String username);
}

