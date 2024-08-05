package com.backend.management.repository;

import com.backend.management.model.Amenity;
import com.backend.management.model.Reservation;
import com.backend.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    // TODO: A method that returns the thing you submitted?
    @Query(value = "SELECT amen FROM Amenity amen WHERE amen = :amenity_id")
    List<Amenity> findByAmenity(Amenity amenity_id);


    @Query(value = "SELECT amen FROM Amenity amen")
    List<Amenity> findByAmenityName();


    //@Query(value = "SELECT amen FROM Amenity amen WHERE amen.amenity_id = :id AND amen = ")
    //Amenity findByIdAndUsername(Long id, Reservation reservation); //for deletion
}
