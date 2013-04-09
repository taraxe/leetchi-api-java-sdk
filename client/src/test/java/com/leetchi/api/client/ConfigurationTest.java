package com.leetchi.api.client;

import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

public class ConfigurationTest extends LeetchiTest {

    @Test
    public void configureClient() throws IOException {
        Leetchi.config()
                .privateKey(pemFile)
                .partnerId("MyPartnerId")
                .baseUrl(LEETCHI_STUB_URL)
                .password(PASSWORD);
        assertThat(Leetchi.privateKey()).isEqualTo(kp.getPrivate());
    }

    @Test
    public void defaultConfig() {
        assertThat(Leetchi.password()).isNotNull();
    }

    @Test(expected = IllegalStateException.class)
    public void partnerIdShouldBeSet() {
        Leetchi.privateKey(kp.getPrivate());
        Leetchi.baseUrl(LEETCHI_STUB_URL);
        Leetchi.checkConfig();
    }

    @Test(expected = IllegalStateException.class)
    public void privateKeyShouldBeSet() {
        Leetchi.partnerId("partnerId");
        Leetchi.baseUrl(LEETCHI_STUB_URL);
        Leetchi.checkConfig();
    }

    @Test(expected = IllegalStateException.class)
    public void baseUrlShouldBeSet() {
        Leetchi.partnerId("partnerId");
        Leetchi.privateKey(kp.getPrivate());
        Leetchi.checkConfig();
    }
}
