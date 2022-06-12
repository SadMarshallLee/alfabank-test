package com.asd.alfabanktest.entity;

import java.util.List;

public interface MoneyEntity {

    int getKey(String currency);

    List<String> getCharCode();

    void update();
}
