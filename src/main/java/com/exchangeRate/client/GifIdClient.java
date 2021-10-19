package com.exchangeRate.client;

import com.exchangeRate.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "gifIdClient", url = "${gifs.apiUrl}")
public interface GifIdClient {

    @GetMapping(value = "/random?api_key=${gifs.apiKey}&tag=rich&rating=g")
    Gif getRichGifId();

    @GetMapping(value = "/random?api_key=${gifs.apiKey}&tag=broke&rating=g")
    Gif getBrokeGifId();
}