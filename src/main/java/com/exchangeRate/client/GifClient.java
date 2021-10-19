package com.exchangeRate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "gifService", url = "${gifs.gifUrl}")
public interface GifClient {

    @GetMapping(value = "/{gifId}/giphy.gif")
    byte[] getGif(@PathVariable("gifId") String gifId);
}