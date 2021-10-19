package com.exchangeRate.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.io.IOException;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class CurrencyTodaysMocks {
    public static void setupMockCurrencyResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/latest.json?app_id=exchangeTestKey&base=testBase&symbols=testCurrency"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        CurrencyTodaysMocks.class.getClassLoader().getResourceAsStream("payload/GetValueResponse.json"),
                                        defaultCharset()))));
    }
}