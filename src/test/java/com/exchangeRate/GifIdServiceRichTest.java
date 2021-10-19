package com.exchangeRate;

import com.exchangeRate.config.GetRichGifIdMocks;
import com.exchangeRate.config.WireMockConfig;
import com.exchangeRate.service.GifIdService;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class GifIdServiceRichTest {

    @Autowired
    private WireMockServer mockGifIdService;

    @Autowired
    private GifIdService gifIdService;


    @BeforeEach
    void setYesterdayUp() throws IOException {
        GetRichGifIdMocks.setupMockGetRichIdResponse(mockGifIdService);
    }

    @Test
    public void CheckNotEmptyResponse() {
        assertFalse(gifIdService.getRichGifId().getData().isEmpty());
    }

    @Test
    public void CheckNostEmptyResponse() {
        assertTrue(gifIdService.getRichGifId().getData().containsKey("id"));
    }
}
