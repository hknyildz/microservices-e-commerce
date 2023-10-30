package com.hknyildz.inventoryservice.controller;

import com.hknyildz.inventoryservice.dto.InventoryResponse;
import com.hknyildz.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    InventoryService inventoryService;

    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(List<String> skuCodes){
        return inventoryService.isInStock(skuCodes);
    }
}
