package com.exchangeRate;

import com.exchangeRate.config.GifServiceMocks;
import com.exchangeRate.config.WireMockConfig;
import com.exchangeRate.service.GifService;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class GifServiceGetGifTest {

    @Autowired
    private WireMockServer mockGifService;

    @Autowired
    private GifService gifService;


    @BeforeEach
    void setYesterdayUp() throws IOException {
        GifServiceMocks.setupMockGetGifResponse(mockGifService);
    }

    @Test
    public void CheckNotEmptyResponse() {
        assertTrue(gifService.getGif("TestGifId").length > 0);
        mockGifService.stop();
    }

}
