package com.tastygreens.catalogservice.service.impl;

import com.tastygreens.catalogservice.connector.InventoryConnector;
import com.tastygreens.catalogservice.model.Item;
import com.tastygreens.catalogservice.repo.CatalogRepo;
import com.tastygreens.catalogservice.service.CatalogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {

    private static final Logger LOGGER = LogManager.getLogger(CatalogServiceImpl.class);

    @Autowired
    private InventoryConnector inventoryConnector;

    @Autowired
    private CatalogRepo catalogRepo;

    @Override
    public List<Item> getAllItems(){
        return catalogRepo.findAll().stream()
                .filter(item -> item.getQuantity()>0).collect(Collectors.toList());
    }

    @Override
    public List<Item> getItemsByType(String type) {
        return catalogRepo.findAllByType(type).stream()
                .filter(item -> item.getQuantity()>0).collect(Collectors.toList());
    }
}
