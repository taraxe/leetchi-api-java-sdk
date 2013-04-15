package com.leetchi.api.client;

import com.leetchi.api.client.model.Entity;
import com.leetchi.api.client.model.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;
import org.bouncycastle.util.encoders.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;

import static org.apache.commons.lang3.StringUtils.*;

public class Leetchi {

    public static final String PROD_URL = "https://api.leetchi.com/";
    public static final String PREPROD_URL = "https://api-preprod.leetchi.com/";
    static String baseUrl;
    static String partnerId;
    static PrivateKey privateKey;
    static String password = "";

    static ObjectMapper mapper = new ObjectMapper();

    public static void checkConfig() {
        if (isEmpty(partnerId) || null == privateKey || isEmpty(baseUrl)) {
            throw new IllegalStateException("partnerId and privateKey must be provided");
        }
    }

    public static StaticConfig config() {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        return new StaticConfig();
    }

    public static String baseUrl() {
        return baseUrl;
    }

    static void baseUrl(String baseUrl) {
        Leetchi.baseUrl = baseUrl;
    }

    static void privateKey(PrivateKey privateKey) {
        Leetchi.privateKey = privateKey;
    }

    public static String password() {
        return password;
    }

    public static PrivateKey privateKey() {
        return privateKey;
    }

    static void partnerId(String partnerId) {
        Leetchi.partnerId = partnerId;
    }

    public static <T extends Entity> T create(T entity) throws Exception {
        final String json = stringify(entity);
        String jsonResponse = post(entity.path(), json);
        return (T) mapper.readValue(jsonResponse, entity.getClass());
    }

    public static <T> T create(String path, T entity) throws Exception {
        final String json = stringify(entity);
        String jsonResponse = post(path, json);
        return (T) mapper.readValue(jsonResponse, entity.getClass());
    }

    public static <T> T fetch(String path, Class<T> clazz) throws Exception {
        String jsonResponse = get(path);
        return (T) mapper.readValue(jsonResponse, clazz);
    }

    public static <T> T fetch(String path, TypeReference type) throws Exception {
        String jsonResponse = get(path);
        return (T) mapper.readValue(jsonResponse, type);
    }

    public static <T extends Entity> T patch(T entity) throws Exception {
        final String json = stringify(entity);
        String jsonResponse = patch(entity.path(entity.getId()) , json);
        return (T) mapper.readValue(jsonResponse, entity.getClass());
    }

    public static <T> T patch(String path, T entity) throws Exception {
        final String json = stringify(entity);
        String jsonResponse = patch(path , json);
        return (T) mapper.readValue(jsonResponse, entity.getClass());
    }

    public static <T> T put(String path, T entity) throws Exception {
        final String json = stringify(entity);
        String jsonResponse = put(path, json);
        return (T) mapper.readValue(jsonResponse, entity.getClass());
    }

    public static <T extends Entity> T put( T entity) throws Exception {
        final String json = stringify(entity);
        String jsonResponse = put(entity.path(entity.getId()), json);
        return (T) mapper.readValue(jsonResponse, entity.getClass());
    }

    public static class StaticConfig {

        public StaticConfig privateKey(File file) throws IOException {
            KeyPair kp = (KeyPair) new PEMReader(new FileReader(file), new PasswordFinder() {
                public char[] getPassword() {
                    return password.toCharArray();
                }
            }).readObject();
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
            baseUrl = null;
            partnerId = null;
            privateKey = null;
            password = "";
        }

        public StaticConfig password(String password) {
            Leetchi.password = password;
            return this;
        }

    }

    private static <T> String stringify(T pojo) throws IOException {
        final StringWriter writer = new StringWriter();
        mapper.writeValue(writer, pojo);
        return writer.toString();
    }

    private static String patch(String path, String body) throws Exception {
        HttpPatch httpPatch = new HttpPatch(url(path));
        httpPatch.setEntity(new StringEntity(body));
        System.out.println(String.format("%s %s", httpPatch.getMethod(), path));

        addSignature(httpPatch, createAuthSignature(httpPatch, body));

        return executeRequest(httpPatch);
    }

    private static String put(String path, String body) throws Exception {
        HttpPut httpPut = new HttpPut(url(path));
        httpPut.setEntity(new StringEntity(body));
        System.out.println(String.format("%s %s", httpPut.getMethod(), path));

        addSignature(httpPut, createAuthSignature(httpPut, body));

        return executeRequest(httpPut);
    }

    private static String delete(String path, String body) throws Exception {
        HttpDelete httpDelete = new HttpDelete(url(path));
        System.out.println(String.format("%s %s", httpDelete.getMethod(), path));

        addSignature(httpDelete, createAuthSignature(httpDelete, body));

        return executeRequest(httpDelete);
    }

    private static String post(String path, String body) throws Exception {
        HttpPost httpPost = new HttpPost(url(path));
        httpPost.setEntity(new StringEntity(body));
        System.out.println(String.format("%s %s", httpPost.getMethod(), path));

        addSignature(httpPost, createAuthSignature(httpPost, body));

        return executeRequest(httpPost);
    }

    private static String get(String path) throws Exception {
        HttpUriRequest httpRequest = new HttpGet(url(path));
        System.out.println(String.format("%s %s", httpRequest.getMethod(), path));

        addSignature(httpRequest, createAuthSignature(httpRequest));

        return executeRequest(httpRequest);
    }

    private static void addSignature(HttpUriRequest httpRequest, String signature) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        httpRequest.setHeader("X-Leetchi-Signature", signature);
        httpRequest.setHeader("Content-Type", "application/json");
    }

    private static String url(String path) {
        String requestUrlPath = buildRequestUrlPath(path);
        return baseUrl + requestUrlPath;
    }

    private static String executeRequest(HttpUriRequest httpRequest) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpRequest);
        HttpEntity entity = response.getEntity();

        StatusLine status = response.getStatusLine();
        System.out.println("status = " + status);

        InputStream content = entity.getContent();
        String responseBody = IOUtils.toString(content, "UTF-8");
        System.out.println("responseBody = " + responseBody);
        return responseBody;
    }

    private static String createAuthSignature(HttpUriRequest httpUriRequest, String body) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String path = httpUriRequest.getURI().getPath();
        String data = String.format("%s|%s|", httpUriRequest.getMethod(), path);

        if (isNotEmpty(body)) {
            data += String.format("%s|", body);
        }

        Signature signer = Signature.getInstance("SHA1withRSA", new BouncyCastleProvider());
        signer.initSign(privateKey);
        signer.update(data.getBytes());
        byte[] result = signer.sign();
        final String signature = new String(Base64.encode(result));
        System.out.println("signature = " + signature);
        return signature;
    }

    private static String createAuthSignature(HttpUriRequest httpUriRequest) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        return createAuthSignature(httpUriRequest, EMPTY);
    }

    private static String buildRequestUrlPath(String path) {
        String resourcePath = trimSlashes(path);
        return String.format("/v1/partner/%s/%s?ts=%s", partnerId, resourcePath, System.currentTimeMillis());
    }

    private static String trimSlashes(String string) {
        return string.replaceAll("(^/)|(/$)", "");
    }
}
