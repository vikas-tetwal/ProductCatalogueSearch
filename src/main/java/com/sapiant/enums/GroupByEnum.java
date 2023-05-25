package com.sapiant.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum GroupByEnum {

    BRANDS("brands"), COLOR("colors"), SIZE("size");
    String value;

    String getValue() {
        return value;
    }
}
