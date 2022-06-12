package com.asd.alfabanktest.model;

import lombok.Data;

import java.util.Map;

@Data
public class BigMoney {

    private String disclaimer;
    private String license;
    private int timeMark;
    private String base;
    private Map<String, Double> rates;
}