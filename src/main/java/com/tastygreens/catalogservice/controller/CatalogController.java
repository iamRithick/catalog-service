package com.tastygreens.catalogservice.controller;

import com.tastygreens.catalogservice.service.CatalogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

    private static final Logger LOGGER = LogManager.getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/")
    public String hello(){
        return "Hello from catalog-service.";
    }

    @GetMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItems(@RequestParam(name = "type", required = false) String type){
        ResponseEntity<Object> response;
        try {
            if (StringUtils.hasLength(type)){
                response = new ResponseEntity<>(catalogService.getItemsByType(type), HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(catalogService.getAllItems(), HttpStatus.OK);
            }
            if (response.getBody() == null) {
                response = new ResponseEntity<>("Oops! Something went wrong. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex){
            LOGGER.error("Exception while fetching catalog items. ", ex);
            response = null;
        }
        return response;
    }

}
