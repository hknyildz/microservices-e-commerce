package com.hknyildz.orderservice.service;

import com.hknyildz.orderservice.dto.OrderLineItemsDto;
import com.hknyildz.orderservice.dto.OrderRequest;
import com.hknyildz.orderservice.model.Order;
import com.hknyildz.orderservice.model.OrderLineItems;
import com.hknyildz.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        // call Inventory service and place order if product is in stock
        Boolean result = webClient.get()
                .uri("http://localhost:8082/api/inventory/"+orderLineItems.get(0).getSkuCode())
                .retrieve()
                .bodyToMono(Boolean.class) // mono is object of a reactive framework that helps us to read response
                .block();
        if(Boolean.TRUE.equals(result)) {
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Product is not on stock");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }


}
