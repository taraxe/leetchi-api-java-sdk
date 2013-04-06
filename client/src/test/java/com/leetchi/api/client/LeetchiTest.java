package com.leetchi.api.client;

import com.leetchi.api.client.Leetchi;
import org.bouncycastle.openssl.PEMWriter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import static org.fest.assertions.Assertions.assertThat;

public class LeetchiTest {
    final String password = "password";
    final String algorithm = "RSA";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private File pemFile;
    private KeyPair kp;

    @Before
    public void before() throws Exception {
        Leetchi.config().reset();
        prepareAuthTests();
    }

    @Test
    public void configureClient() throws IOException {
        Leetchi.config()
                .privateKey(pemFile)
                .partnerId("MyPartnerId")
                .baseUrl("https://api-preprod.leetchi.com/")
                .password(password);
        assertThat(Leetchi.privateKey()).isEqualTo(kp.getPrivate());
    }

    @Test
    public void defaultConfig() {
        assertThat(Leetchi.baseUrl()).isNotEmpty();
        assertThat(Leetchi.password()).isNotNull();
    }

    @Test(expected = IllegalStateException.class)
    public void partnerIdShouldBeSet() {
        Leetchi.privateKey(kp.getPrivate());
        Leetchi.checkConfig();
    }

    @Test(expected = IllegalStateException.class)
    public void privateKeyShouldBeSet() {
        Leetchi.partnerId("partnerId");
        Leetchi.checkConfig();
    }


    public void prepareAuthTests() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
        pemFile = folder.newFile("example.pem");
        kpg.initialize(2048);
        kp = kpg.genKeyPair();

        PEMWriter pemWriter = new PEMWriter(new FileWriter(pemFile));
        pemWriter.writeObject(kp, algorithm, password.toCharArray(), new SecureRandom());
        pemWriter.flush();
        pemWriter.close();
    }
}
