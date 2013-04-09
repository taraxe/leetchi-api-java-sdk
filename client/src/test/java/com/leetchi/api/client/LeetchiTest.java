package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import org.bouncycastle.openssl.PEMWriter;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public abstract class LeetchiTest {
    final static String PASSWORD = "password";
    final static String ALGORITHM = "RSA";
    final static int STUB_PORT = 8086;
    final static String LEETCHI_STUB_URL = "http://localhost:" + STUB_PORT + "/";

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    File pemFile;

    KeyPair kp;

    @Before
    public void before() throws Exception {
        Leetchi.config().reset();
        prepareAuthTests();
    }

    public void prepareAuthTests() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        pemFile = folder.newFile("example.pem");
        kpg.initialize(2048);
        kp = kpg.genKeyPair();

        PEMWriter pemWriter = new PEMWriter(new FileWriter(pemFile));
        pemWriter.writeObject(kp, ALGORITHM, PASSWORD.toCharArray(), new SecureRandom());
        pemWriter.flush();
        pemWriter.close();
    }
}
