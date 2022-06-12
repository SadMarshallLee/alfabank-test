package com.asd.alfabanktest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/* конфигурирем дату в формат json */
@Configuration
public class DateConfig {

    @Bean("date_bean")
    public SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean("time_bean")
    public SimpleDateFormat timeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH");
    }
}
