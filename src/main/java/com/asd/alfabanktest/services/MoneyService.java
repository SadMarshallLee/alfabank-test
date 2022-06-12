package com.asd.alfabanktest.services;

import com.asd.alfabanktest.entity.MoneyEntity;
import com.asd.alfabanktest.model.BigMoney;
import com.asd.alfabanktest.repo.BigMoneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class MoneyService {

    private BigMoney prevCurrency;
    private BigMoney presentCurrency;

    private final BigMoneyRepo bigMoneyRepo;

    private final SimpleDateFormat date;
    private final SimpleDateFormat time;
    @Value("34fc50366d0f43cf927f2e44935e360c")
    private String apiId;
    @Value("RUB")
    private String base;

    @Autowired
    protected MoneyService(BigMoneyRepo bigMoneyRepo,
                           @Qualifier("date_bean") SimpleDateFormat date,
                           @Qualifier("time_bean") SimpleDateFormat time) {
        this.bigMoneyRepo = bigMoneyRepo;
        this.date = date;
        this.time = time;
    }

    public int getKey(String charCode) {
        this.update();
        Double prevCoeff = getCoefficient(prevCurrency, charCode);
        Double presentCoeff = getCoefficient(presentCurrency, charCode);

        if (presentCoeff != null && prevCoeff != null) {
            if (presentCoeff > prevCoeff) {
                return -1;
            } else if (prevCoeff > presentCoeff) {
                return 1;
            }
        }
        return 0;
    }

    public List<String> getCharCode() {
        List<String> result = null;
        if (this.presentCurrency.getRates() != null) {
            result = new ArrayList<>(this.presentCurrency.getRates().keySet());
        }
        return result;
    }

    public void update() {
        Long time = System.currentTimeMillis();
        updatePresentCurrency(time);
        updatePrevCurrency(time);
    }

    private void updatePresentCurrency(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String presentDate = date.format(calendar.getTime());
        if (presentCurrency == null ||
                !this.time
                        .format(presentCurrency.getTimeMark() * 1000L)
                        .equals(presentDate)) {
            presentCurrency = bigMoneyRepo.getLastCurrencyRate(presentDate, apiId);
        }
    }

    private void updatePrevCurrency(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String presentDate = date.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String newDate = date.format(calendar.getTime());
        if (prevCurrency == null
                || (!this.date
                        .format(prevCurrency.getTimeMark() * 1000L)
                        .equals(presentDate)
                && !date
                        .format(prevCurrency.getTimeMark() * 1000L)
                        .equals(newDate))) {
            prevCurrency = bigMoneyRepo.getLastCurrencyRate(presentDate, apiId);
        }
    }

    private Double getCoefficient(BigMoney rates, String charCode) {
        Double result = null;
        Double rate = null;
        Double OERRate = null;
        Double defaultRate = null;
        Map<String, Double> map;

        if (rates != null && rates.getRates() != null) {
            map = rates.getRates();
            rate = map.get(charCode);
            OERRate = map.get(base);
            defaultRate = map.get(rates.getBase());
        }

        if (rate != null && OERRate != null && defaultRate != null) {
            result = new BigDecimal((defaultRate / OERRate) * rate)
                    .setScale(4, RoundingMode.UP)
                    .doubleValue();
        }
        return result;
    }
}
