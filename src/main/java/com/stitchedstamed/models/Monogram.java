package com.stitchedstamed.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Monogram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String itemName;
    String font;
    String threadColor;
    String placement;
    String monogram;
    String designNotes;
    String otherPlacement;
    String otherFont;
}
