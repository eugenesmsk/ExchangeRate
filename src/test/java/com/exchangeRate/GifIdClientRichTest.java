package com.exchangeRate;

import com.exchangeRate.config.GetRichGifIdMocks;
import com.exchangeRate.config.WireMockConfig;
import com.exchangeRate.client.GifIdClient;
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
public class GifIdClientRichTest {

    @Autowired
    private WireMockServer mockGifIdClient;

    @Autowired
    private GifIdClient gifIdClient;


    @BeforeEach
    void setYesterdayUp() throws IOException {
        GetRichGifIdMocks.setupMockGetRichIdResponse(mockGifIdClient);
    }

    @Test
    public void CheckNotEmptyResponse() {
        assertFalse(gifIdClient.getRichGifId().getData().isEmpty());
    }

    @Test
    public void CheckNostEmptyResponse() {
        assertTrue(gifIdClient.getRichGifId().getData().containsKey("id"));
    }
}
