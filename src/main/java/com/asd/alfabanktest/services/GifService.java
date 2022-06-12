package com.asd.alfabanktest.services;

import com.asd.alfabanktest.entity.GifEntity;
import com.asd.alfabanktest.repo.GiphyRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GifService implements GifEntity {

    private final GiphyRepo giphyRepo;
    @Value("iK0NiQL6zfWLKzJMta8vQ3vLNA6rgJLi")
    private String apiKey;

    public GifService(GiphyRepo giphyRepo) {
        this.giphyRepo = giphyRepo;
    }

    @Override
    public ResponseEntity<Map> getGif(String tag) {
        return giphyRepo.gifResponse(apiKey, tag);
    }
}
