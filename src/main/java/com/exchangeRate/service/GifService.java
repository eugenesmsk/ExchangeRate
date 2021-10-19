package com.exchangeRate.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "gifService", url = "${gifUrl}")
public interface GifService {

    @GetMapping(value = "/{gifId}/giphy.gif")
    byte[] getGif(@PathVariable("gifId") String gifId);
}