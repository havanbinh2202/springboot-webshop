package com.tn.dto;

import lombok.Data;

@Data
public class ProductShowDTO {
    private int id;

    private String productname;

    private int price;

    private String Image;

    private String categoryname;
}
