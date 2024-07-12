package com.tn.dto;

import lombok.Data;

@Data
public class ProductListDTO {
    private int id;

    private String productname;

    private int price;

    private int quantity;

    private String categoryname;
}
