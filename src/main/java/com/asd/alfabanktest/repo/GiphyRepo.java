package com.asd.alfabanktest.repo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/* api гифок отсюда: https://developers.giphy.com/docs/api/#quick-start-guide*/

@FeignClient(name = "giphyClient", url = "https://api.giphy.com/v1/gifs")
public interface GiphyRepo {

    @GetMapping("/random")
    ResponseEntity<Map> gifResponse(@RequestParam("api_key") String apiKey,
                                    @RequestParam("tag") String tag);
}
