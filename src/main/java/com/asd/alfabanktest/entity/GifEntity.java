package com.asd.alfabanktest.entity;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GifEntity {

    ResponseEntity<Map> getGif(String key);
}
