package com.tn.dto;

import lombok.Data;

@Data
public class ProductShowDTO {
    private Long productId;

    private String productname;

    private int price;

    private String Image;

    private String categoryname;
}
