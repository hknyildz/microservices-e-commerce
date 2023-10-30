package com.hknyildz.inventoryservice.service;

import com.hknyildz.inventoryservice.dto.InventoryResponse;
import com.hknyildz.inventoryservice.model.Inventory;
import com.hknyildz.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static sun.awt.image.MultiResolutionCachedImage.map;

@Service
@RequiredArgsConstructor
public class InventoryService {

    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream().
                map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()
        ).toList();
    }
}
