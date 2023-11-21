package com.hknyildz.orderservice.controller;

import com.hknyildz.orderservice.dto.OrderRequest;
import com.hknyildz.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<OrderRespone> getOrders()
//    {
//        return orderService.
//    }
}
