package com.stitchedstamed.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class SpecialOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String itemName;
    String brand;
    String size;
    String itemColor;
    String designNotes;
}
