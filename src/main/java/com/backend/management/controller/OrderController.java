package com.backend.management.controller;

import com.backend.management.model.Order;
import com.backend.management.model.User;
import com.backend.management.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // tenant only feature
    @GetMapping(value = "/orders")
    public List<Order> listOrder(Principal principal) {
        return orderService.listByTenant(principal.getName());
    }



    //provider feature
    @GetMapping(value = "/orders/orderCompleted")
    public List<Order> listOrderCompletedByProvider(Principal principal) {
        return orderService.listCompleteByProvider(principal.getName());
    }
    @GetMapping(value = "/orders/orderClaimed")
    public List<Order> listOrderClaimed(Principal principal) {
        return orderService.listClaimedByProvider(principal.getName());
    }
    @PostMapping("/orders/claim")
    public void claimTask(Principal principal, @RequestParam(name = "order_id") Long OrderId, @RequestBody String note) {
        orderService.claimTask(OrderId,principal.getName(),note);
    }
    @PostMapping("/orders/complete")
    public void completeTask(@RequestParam(name = "order_id") Long OrderId) {
        orderService.completeTask(OrderId);
    }
    @GetMapping(value = "/orders/findAllUnclaimed")
    public List<Order> listOfAllUnclaimed(){
        return orderService.listOfUnclaimed();
    }





    // add order
    @PostMapping("/orders")
    public void addOrder(@RequestBody Order order, Principal principal) {
        Order newOrder = new Order.Builder()
                .setDescription(order.getIssueDescription())
                .setLocation(order.getLocation())
                .setTitle(order.getTitle())
                .setUser(new User.Builder().setUsername(principal.getName()).build())
                .build();
        orderService.add(newOrder);
    }
    //get order
    @GetMapping(value = "/orders/id")
    public Order getOrder(
            @RequestParam(name = "order_id") Long OrderId,
            Principal principal) {
        return orderService.findByIdAndTenant(OrderId, principal.getName());
    }
    @DeleteMapping("/orders")
    public void deleteOrder(
            @RequestParam(name = "order_id") Long OrderId,
            Principal principal) {
        orderService.delete(OrderId, principal.getName());
    }




    // manager feature
    @GetMapping(value = "/orders/findAll")
    public List<Order> listOfAllOrder(){
        return orderService.listOfAll();
    }

    @GetMapping(value = "/orders/findAllByManager")
    public List<Order> listOfAllManager(Principal principal){
        return orderService.listByManager(principal.getName());
    }

}
