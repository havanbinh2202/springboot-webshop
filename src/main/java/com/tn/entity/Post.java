package com.tn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String author;

    private String title;

    private String content;

    private String Image;
    private LocalDate date;




}
