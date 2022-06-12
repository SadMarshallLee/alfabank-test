package com.asd.alfabanktest.component;

import com.asd.alfabanktest.services.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/* данный класс обновляет курсы валют при запуске */
@Component
public class DataStart {

    private final MoneyService moneyService;

    @Autowired
    public DataStart(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @PostConstruct
    public void start() {
        moneyService.update();
    }
}

