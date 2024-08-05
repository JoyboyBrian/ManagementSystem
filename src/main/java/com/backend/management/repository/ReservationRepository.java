package com.backend.management.repository;

import com.backend.management.model.Reservation;
import com.backend.management.model.User;
import com.backend.management.model.vo.ReservationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT res FROM Reservation res WHERE res.user_id = ?1 order by res.date desc")
    List<Reservation> findByRequester(String requester_id);

    @Query(value = "SELECT res FROM Reservation res order by res.date desc")
    List<Reservation> findByToday();

    @Query(value = "SELECT res FROM Reservation res WHERE   res.reservation_id = ?1")
    Reservation findByReservationAndRequester(int reservation_id);

    //Reservation findByReservation_idAndRequester_id(int reservation_id, User requester_id); // for deletion
    @Modifying
    @Query(value = "delete from Reservation res where res.reservation_id =?1")
    int deleteByReservation_id(Integer reservation_id);

    @Query(value = "SELECT res FROM Reservation res WHERE res.date = ?1 AND res.start_time = ?2 AND res.end_time = ?3 and res.amenity_id=?4")
    Reservation findReservationByDateAndAmenityStarttimeAndEndTime(String date, String startTime, String endTime, String amenityid);

    @Query(value = "SELECT  authority FROM authority WHERE username = ?1", nativeQuery = true)
    String getAuth(String username);

    @Query(value = "SELECT res FROM Reservation res WHERE res.date = ?1 and res.amenity_id=?2")
    List<Reservation> findByDate(String date, String amenity_id);
}

