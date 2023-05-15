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

    @GetMapping(value = "/orders")
    public List<Order> listOrder(Principal principal) {
        return orderService.listByTenant(principal.getName());
    }
    @GetMapping(value = "/orders/orderClaimed")
    public List<Order> listOrderClaimed(Principal principal) {
        return orderService.listByProvider(principal.getName());
    }

    @GetMapping(value = "/orders/id")
    public Order getOrder(
            @RequestParam(name = "order_id") Long OrderId,
            Principal principal) {
        return orderService.findByIdAndTenant(OrderId, principal.getName());
    }

    @PostMapping("/orders")
    public void addOrder(@RequestBody Order order) {
        orderService.add(order);
    }

    @PostMapping("/orders/claim")
    public void claimTask(Principal principal, @RequestParam(name = "order_id") Long OrderId) {
        orderService.claimTask(OrderId,principal.getName());
    }

    @PostMapping("/orders/complete")
    public void completeTask(@RequestParam(name = "order_id") Long OrderId) {
        orderService.completeTask(OrderId);
    }


    @DeleteMapping("/orders")
    public void deleteOrder(
            @RequestParam(name = "order_id") Long OrderId,
            Principal principal) {
        orderService.delete(OrderId, principal.getName());
    }

    @GetMapping(value = "/orders/findAll")
    public List<Order> listOfAllOrder(){
        return orderService.listOfAll();
    }


    @GetMapping(value = "/orders/findAllUnclaimed")
    public List<Order> listOfAllUnclaimed(){
        return orderService.listOfUnclaimed();
    }
}
