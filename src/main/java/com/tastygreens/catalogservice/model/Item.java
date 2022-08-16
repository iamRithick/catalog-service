package com.tastygreens.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"itemId", "title", "type", "description", "price", "rating"})
@Document(collection = "Inventory")
public class Item {

    public static final String SEQUENCE_NAME = "INVENTORY_SEQ";

    @Id
    private Long itemId;

    private String title;

    private String type;

    private String description;

    private float price;

    private int rating;

    @JsonIgnore
    private int quantity;
}
