package com.exchangeRate.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("gifs")
public class GifsProperties {
    String apiUrl;
    String gifUrl;
    String apiKey;
    String equalValuesGifId;
    String errorGifId;
}
