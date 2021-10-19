package com.exchangeRate.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("exchange")
public class ExchangeProperties {
    String base;
    String apiUrl;
    String apiKey;
}
