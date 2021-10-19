package com.exchangeRate.controller;

import com.exchangeRate.client.ExchangeClient;
import com.exchangeRate.client.GifIdClient;
import com.exchangeRate.client.GifClient;
import feign.FeignException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@Slf4j
public class ExchangeController {

    @Value("${gifs.errorGifId}")
    String errorGifId;
    @Value("${gifs.equalValuesGifId}")
    String equalValuesGifId;

    @Autowired
    private ExchangeClient exchangeClient;

    @Autowired
    private GifIdClient gifIdClient;

    @Autowired
    private GifClient gifClient;

    @SneakyThrows
    @GetMapping(value = "/api/{currency}", produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] gifResponse(@PathVariable("currency") String currency) {
        String gifId;
        try {
            gifId = getGifId(currency);
            return gifClient.getGif(gifId);
        } catch (FeignException e) {
            log.error(e.contentUTF8());
            gifId = errorGifId;                            //error gif
        }
        return gifClient.getGif(gifId);
    }

    private String getYesterday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = yesterdayComputation();
        return dateFormat.format(cal.getTime());
    }

    private Calendar yesterdayComputation() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal;
    }

    private String getGifId(String currency) {
        String gifId;
        String yesterdayDate = getYesterday();
        double todaysValue = exchangeClient
                .getTodaysValue(currency)
                .getRates()
                .get(currency.toUpperCase());
        double yesterdaysValue = exchangeClient
                .getYesterdaysValue(currency, yesterdayDate)
                .getRates()
                .get(currency.toUpperCase());

        if (todaysValue > yesterdaysValue) {
            gifId = (String) gifIdClient.getRichGifId().getData().get("id");
            return gifId;
        } else if (todaysValue < yesterdaysValue) {
            gifId = (String) gifIdClient.getBrokeGifId().getData().get("id");
            return gifId;
        } else {
            gifId = equalValuesGifId;                   //if today's and yestarday's values are equal
            return gifId;
        }
    }
}
