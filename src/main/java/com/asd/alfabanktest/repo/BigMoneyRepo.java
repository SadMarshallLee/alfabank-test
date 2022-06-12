package com.asd.alfabanktest.repo;

import com.asd.alfabanktest.model.BigMoney;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/* api отсюда: https://docs.openexchangerates.org/*/

@FeignClient(name = "OERClient", url = "https://openexchangerates.org/api")
public interface BigMoneyRepo {

    @GetMapping("/latest.json")
    BigMoney getCurrencyRate(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{date}.json")
    BigMoney getLastCurrencyRate(@PathVariable String date,
                                 @RequestParam("app_id") String appId);
}
