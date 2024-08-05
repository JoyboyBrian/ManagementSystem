package com.backend.management.service;

import com.backend.management.exception.MaintenanceAlreadyExistException;
import com.backend.management.exception.OrderNotExistException;
import com.backend.management.model.Order;
import com.backend.management.model.User;
import com.backend.management.repository.OrderRepository;
import com.backend.management.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
    // tenant service
    public List<Order> listByTenant(String username){
        return orderRepository.findByUser(new User.Builder().setUsername(username).build());
    }
    public Order findByIdAndTenant(Long orderId, String username) throws OrderNotExistException {
        Order order = orderRepository.findByIdAndUser(orderId,new User.Builder().setUsername(username).build());
        if(order == null){
            throw new OrderNotExistException("Order DNE");
        }
        return order;
    }


    // add order
    public void add(Order order){
        order.setStatus("submitted");
        order.setSubmittedDate(LocalDate.now());
        orderRepository.save(order);
    }
    @Transactional
    public void delete(Long orderId, String username)throws OrderNotExistException{
        Order order = orderRepository.findByIdAndUser(orderId,new User.Builder().setUsername(username).build());
        if(order == null){
            throw new OrderNotExistException("Order DNE");
        }
        orderRepository.deleteById(orderId);
    }



//  provider p0 feature
    public List<Order> listOfUnclaimed(){
        return orderRepository.findAllByStatus("submitted");

    }
    @Transactional
    public void claimTask(Long id, String provider_username, String note) {
        Order task = orderRepository.getById(id);
        task.setStatus("In Progress");
        task.setProviderNote(note);
        task.setProvider(new User.Builder().setUsername(provider_username).build());
        task.setProcessedDate(LocalDate.now());
    }
    @Transactional
    public void completeTask(Long id) {
        Order task = orderRepository.getById(id);
        task.setStatus("complete");
        task.setCompleteDate(LocalDate.now());
    }
    public List<Order> listByProvider(String username){
        return orderRepository.findAllByProvider(new User.Builder().setUsername(username).build());
    }
    public List<Order> listClaimedByProvider(String username){
        List<Order> allList= listByProvider(username);
        List<Order> result = new ArrayList<>();
        for(Order order : allList){
            if(order.getStatus().equals("In Progress")){
                result.add(order);
            }
        }
        return result;
    }
    public List<Order> listCompleteByProvider(String username){
        List<Order> allList= listByProvider(username);
        List<Order> result = new ArrayList<>();
        for(Order order : allList){
            if(order.getStatus().equals("complete")){
                result.add(order);
            }
        }
        return result;
    }





    // list by manager
    public List<Order> listByManager(String username){
        return orderRepository.findByUser(new User.Builder().setUsername(username).build());
    }
    // manager p0
    public List<Order> listOfAll(){
        return orderRepository.findAll();
    }
}
