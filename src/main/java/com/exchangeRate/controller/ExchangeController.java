package com.exchangeRate.controller;

import com.exchangeRate.service.ExchangeService;
import com.exchangeRate.service.GifIdService;
import com.exchangeRate.service.GifService;
import feign.FeignException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private GifIdService gifIdService;

    @Autowired
    private GifService gifService;

    @SneakyThrows
    @GetMapping(value = "/api/{currency}", produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] gifResponse(@PathVariable("currency") String currency) {
        String gifId;
        String yesterdayDate = getYesterday();
        try {
            double todaysValue = exchangeService
                    .getTodaysValue(currency)
                    .getRates()
                    .get(currency.toUpperCase());
            double yesterdaysValue = exchangeService
                    .getYesterdaysValue(currency, yesterdayDate)
                    .getRates()
                    .get(currency.toUpperCase());

            if(todaysValue > yesterdaysValue) {
                gifId = (String) gifIdService.getRichGifId().getData().get("id");
            }
            else if(todaysValue < yesterdaysValue) {
                gifId = (String) gifIdService.getBrokeGifId().getData().get("id");
            }
            else {
                gifId = "3DONeV4N5Qz3gIpOue";                   //if today's and yestarday's values are equal
            }
            return gifService.getGif(gifId);
        } catch (FeignException e) {
            System.out.println(e.contentUTF8());
            gifId = "aYpmlCXgX9dc09dbpl";                       //error gif
        }
        return gifService.getGif(gifId);
    }

    private String getYesterday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        System.out.println(dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }
}
