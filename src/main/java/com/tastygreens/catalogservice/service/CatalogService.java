package com.tastygreens.catalogservice.service;

import com.tastygreens.catalogservice.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CatalogService {

    public List<Item> getAllItems();

    public List<Item> getItemsByType(String type);

}
