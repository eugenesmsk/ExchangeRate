package com.exchangeRate.client;

import com.exchangeRate.model.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="exchangeClient", url = "${apiExchangeUrl}")
public interface ExchangeClient {
        @GetMapping(value = "/latest.json?app_id=${exchangeApiKey}&base=${base}&symbols={currency}")
    Currency getTodaysValue(@PathVariable("currency") String currency);

    @GetMapping(value = "/historical/{date}.json?app_id=${exchangeApiKey}&base=${base}&symbols={currency}")
    Currency getYesterdaysValue(@PathVariable("currency") String currency,
                                @PathVariable("date") String yesterday);
}