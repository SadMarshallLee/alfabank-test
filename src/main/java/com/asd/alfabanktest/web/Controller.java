package com.asd.alfabanktest.web;

import com.asd.alfabanktest.services.GifService;
import com.asd.alfabanktest.services.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alfabanktest")
public class Controller {

    private final MoneyService moneyService;
    private final GifService gifService;

    @Value("rich")
    private String richTag;
    @Value("broke")
    private String brokeTag;
    @Value("error")
    private String errorTag;

    @Autowired
    public Controller(MoneyService moneyService, GifService gifService) {
        this.moneyService = moneyService;
        this.gifService = gifService;
    }

    @GetMapping("/getmemymoney")
    public List<String> getMoneyList() {
        return moneyService.getCharCode();
    }

    @GetMapping("/getgif/{code}")
    public ResponseEntity<Map> getGif(@PathVariable String code) {
        ResponseEntity<Map> result;
        int gifKey;
        String gifTag = null;
        if (code == null) {
            gifKey = 0;
            gifTag = this.errorTag;
        } else {
            gifKey = moneyService.getKey(code);
        }

        if (code != null) {
            gifKey = moneyService.getKey(code);
        }

        /* собственно, здесь выдается нужная гифка */
        switch (gifKey) {
            case 1 -> gifTag = this.richTag;
            case -1 -> gifTag = this.brokeTag;
        }

        result = gifService.getGif(gifTag);
        return result;
    }
}
