package com.leetchi.api.client;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;

import static org.apache.commons.lang.StringUtils.isEmpty;

public class Leetchi {
    static String baseUrl = "https://api-preprod.leetchi.com/";
    static String partnerId;
    static PrivateKey privateKey;
    static String password = "";

    public static String baseUrl() {
        return baseUrl;
    }

    public static StaticConfig config() {
        if(Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        return new StaticConfig();
    }

    public static void privateKey(PrivateKey privateKey) {
        Leetchi.privateKey =  privateKey;
    }

    public static void checkConfig() {
        if(isEmpty(partnerId) || null == privateKey) {
            throw new IllegalStateException("partnerId and privateKey must be provided");
        }
    }

    public static String password() {
        return password;
    }

    public static PrivateKey privateKey() {
        return privateKey;
    }

    public static class StaticConfig {
        public StaticConfig privateKey(File file) throws IOException {
            KeyPair kp = (KeyPair) new PEMReader(new FileReader(file), new PasswordFinder() {
                public char[] getPassword() {
                    return password.toCharArray();
                }}).readObject();
            Leetchi.privateKey(kp.getPrivate());
            return this;
        }

        public StaticConfig privateKey(File file, String password) throws IOException {
            return password(password).privateKey(file);
        }

        public StaticConfig partnerId(String partnerId) {
            Leetchi.partnerId(partnerId);
            return this;
        }

        public StaticConfig baseUrl(String baseUrl) {
            Leetchi.baseUrl = baseUrl;
            return this;
        }

        void reset() {
            baseUrl = "https://api-preprod.leetchi.com/";
            partnerId = null;
            privateKey = null;
            password = "";
        }

        public StaticConfig password(String password) {
            Leetchi.password = password;
            return this;
        }
    }

    public static void partnerId(String partnerId) {
        Leetchi.partnerId = partnerId;
    }
}
