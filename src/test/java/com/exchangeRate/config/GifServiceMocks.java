package com.exchangeRate.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.io.IOException;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class GifServiceMocks {
    public static void setupMockGetGifResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/TestGifId/giphy.gif"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.IMAGE_GIF_VALUE)
                        .withBody(
                                copyToString(
                                        CurrencyYesterdaysMocks.class.getClassLoader().getResourceAsStream("payload/GifData.txt"),
                                        defaultCharset()))));
    }
}