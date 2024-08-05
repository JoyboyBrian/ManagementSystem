package com.backend.management.service;

import com.backend.management.exception.ReservationCollisionException;
import com.backend.management.exception.ReservationNotFoundException;
import com.backend.management.model.Amenity;
import com.backend.management.model.Reservation;
import com.backend.management.model.User;
import com.backend.management.model.vo.AmenityVo;
import com.backend.management.model.vo.ReservationVo;

import com.backend.management.repository.ReservationRepository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    private AmenityService amenityService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<AmenityVo> listByToday(User username) {
        List<Amenity> amenityList = amenityService.list();
//        String user = reservationRepository.getAuth(username.getUsername());
        List<Reservation> byRequester = reservationRepository.findByToday();

//        amenity_id按照id分组<map, list>
        Map<String, List<Reservation>> map = byRequester.stream().collect(
                java.util.stream.Collectors.groupingBy(Reservation::getAmenity_id));

        List<AmenityVo> amenityVos = new ArrayList<>();
        for (Amenity amenity : amenityList) {
            AmenityVo amenityVo = new AmenityVo();
            amenityVo.setAmenityId(amenity.getAmenityID());
            amenityVo.setAmenityName(amenity.getAmenityName());
            List<Reservation> reservations = map.get(amenity.getAmenityID() + "");
            if (!CollectionUtils.isEmpty(reservations)) {
                List<ReservationVo> reservationVos = new ArrayList<>();
                for (Reservation reservation : reservations) {
                    ReservationVo reservationVo = new ReservationVo();
                    reservationVo.setReservation_id(reservation.getReservationID());
                    reservationVo.setReservation_name(reservation.getReservationName());
                    reservationVo.setStartTime(reservation.getStartTime());
                    reservationVo.setEndTime(reservation.getEndTime());
                    reservationVo.setDate(reservation.getDate());
                    reservationVo.setUserId(reservation.getUser_id());
                    reservationVos.add(reservationVo);
                }
                amenityVo.setChildrenList(reservationVos);
                amenityVos.add(amenityVo);
            }
        }
        return amenityVos;
    }

    //1.get reservation by id
    public Reservation findByIdAndUsername(int reservation_id, User username) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findByReservationAndRequester(reservation_id);
        if (reservation == null) {
            throw new ReservationNotFoundException("Reservation not found");
        }
        return reservation;
    }

    //2.Add reservation
    @Transactional
    public void add(List<Reservation> reservation, Principal principal) throws ReservationCollisionException {
        for (Reservation reservation1 : reservation) {
            Reservation reservation2 = reservationRepository.
                    findReservationByDateAndAmenityStarttimeAndEndTime(
                            reservation1.getDate(), reservation1.getStartTime(), reservation1.getEndTime(), reservation1.getAmenity_id());
            if (reservation2 != null) {
                throw new ReservationCollisionException("Reservation collision");
            }
            reservation1.setUser_id(principal.getName());
            reservationRepository.save(reservation1);
        }
    }

    //3.delete reservation by id
    @Transactional //(Isolation = Isolation.SERIALIZABLE)
    public void delete(Integer reservation_id, String username) throws ReservationNotFoundException {
//        User user = new User.Builder().setUsername(username).build();
//        Reservation reservation = reservationRepository.findByReservationAndRequester(reservation_id, user); //username?

        reservationRepository.deleteByReservation_id(reservation_id);
    }

    public List<ReservationVo> listByAll(User username) {
        List<ReservationVo> voList = new ArrayList<>();
        List<Reservation> byRequester = reservationRepository.findByRequester(username.getUsername());
        for (Reservation reservation : byRequester) {
            ReservationVo reservationVo = new ReservationVo();
            reservationVo.setReservation_id(reservation.getReservationID());
            reservationVo.setStartTime(reservation.getStartTime());
            reservationVo.setEndTime(reservation.getEndTime());
            reservationVo.setReservation_name(reservation.getReservationName());
//            date转为 yyyy-MM-dee
            reservationVo.setDate(reservation.getDate());
            voList.add(reservationVo);
        }
        return voList;
    }

    public List<ReservationVo> listByDate(String date, User user, String amenity_id) {
        List<ReservationVo> voList = new ArrayList<>();
        List<Reservation> byRequester = reservationRepository.findByDate(date,amenity_id);
        for (Reservation reservation : byRequester) {
            ReservationVo reservationVo = new ReservationVo();
            reservationVo.setReservation_id(reservation.getReservationID());
            reservationVo.setStartTime(reservation.getStartTime());
            reservationVo.setEndTime(reservation.getEndTime());
            reservationVo.setReservation_name(reservation.getReservationName());
//            date转为 yyyy-MM-dee
            reservationVo.setDate(reservation.getDate());
            voList.add(reservationVo);
        }
        return voList;
    }
}