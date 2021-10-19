package com.exchangeRate;

import com.exchangeRate.config.GetBrokeGifIdMocks;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class GifIdClientBrokeTest {

    @Autowired
    private WireMockServer mockGifIdClient;

    @Autowired
    private GifIdClient gifIdClient;


    @BeforeEach
    void setYesterdayUp() throws IOException {
        GetBrokeGifIdMocks.setupMockGetBrokeIdResponse(mockGifIdClient);
    }

    @Test
    public void CheckNotEmptyResponse() {
        assertFalse(gifIdClient.getBrokeGifId().getData().isEmpty());
    }

    @Test
    public void CheckNostEmptyResponse() {
        assertTrue(gifIdClient.getBrokeGifId().getData().containsKey("id"));
    }

}
