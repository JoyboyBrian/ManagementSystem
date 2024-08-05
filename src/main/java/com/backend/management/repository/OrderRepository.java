package com.backend.management.repository;

import com.backend.management.model.Order;
import com.backend.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByUser(User user);
    Order findByIdAndUser(Long id, User user);

    @Query("SELECT u FROM Order u WHERE u.status = :status")
    List<Order> findAllByStatus(@Param("status") String status);

    List<Order> findAllByProvider(User user);


//    @Query("SELECT u FROM Order u WHERE u.status = :status")
//    List<Order> findAllCompleteByProvider(User user,String status);
}
