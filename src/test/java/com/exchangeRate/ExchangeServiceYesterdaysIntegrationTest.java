package com.exchangeRate;

import com.exchangeRate.config.CurrencyYesterdaysMocks;
import com.exchangeRate.config.WireMockConfig;
import com.exchangeRate.service.ExchangeService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class ExchangeServiceYesterdaysIntegrationTest {

    @Autowired
    private WireMockServer mockExchangeService;

    @Autowired
    private ExchangeService exchangeService;

    @BeforeEach
    void setYesterdayUp() throws IOException {
        CurrencyYesterdaysMocks.setupMockCurrencyYesterdayResponse(mockExchangeService);
    }

    @Test
    public void whenGetYesterdaysValue_thenReturnsOnlyOneValue() {
        assertEquals(1, exchangeService.getYesterdaysValue("testCurrency", "2021-10-15").getRates().size());
    }
}