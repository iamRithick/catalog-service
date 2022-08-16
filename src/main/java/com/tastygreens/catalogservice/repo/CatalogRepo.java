package com.tastygreens.catalogservice.repo;

import com.tastygreens.catalogservice.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepo extends MongoRepository<Item, Long> {
    public List<Item> findAllByType(String type);
}
