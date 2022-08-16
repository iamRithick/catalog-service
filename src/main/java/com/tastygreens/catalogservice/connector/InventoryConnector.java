package com.tastygreens.catalogservice.connector;

import com.tastygreens.catalogservice.model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InventoryConnector {

    private static final Logger LOGGER = LogManager.getLogger(InventoryConnector.class);

    private final RestTemplate restTemplate;

    @Value("${restClient.def.inventoryService.baseUrl}")
    private String baseUrl;

    @Value("${restClient.def.inventoryService.getAllItems}")
    private String getAllItemsPath;

    @Autowired
    public InventoryConnector(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Item> getInventoryItems() throws HttpClientErrorException{
        try {
            String path = baseUrl + getAllItemsPath;
            LOGGER.info("URI: {}", path);
            ResponseEntity<List<Item>> items = restTemplate.exchange(path, HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>(){});
            LOGGER.info("Response: {}", items);
            return items.getBody();
        } catch (HttpClientErrorException ex){
            LOGGER.error("Exception while fetching items from Inventory Service: ", ex);
        }
        return null;
    }
}
