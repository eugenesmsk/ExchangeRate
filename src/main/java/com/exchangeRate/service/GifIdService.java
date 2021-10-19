package com.exchangeRate.service;

import com.exchangeRate.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "gifUrlService", url = "${giphyApiUrl}")
public interface GifIdService {

    @GetMapping(value = "/random?api_key=${giphyApiKey}&tag=rich&rating=g")
    Gif getRichGifId();

    @GetMapping(value = "/random?api_key=${giphyApiKey}&tag=broke&rating=g")
    Gif getBrokeGifId();
}