package com.stitchedstamed.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSource {
    IN_STORE("In-Store");

    String orderSource;

}
