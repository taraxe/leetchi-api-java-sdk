package com.leetchi.api.client;

import net.sf.json.JSONObject;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.URL;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class LeetchiAPIExample {

    public static final String LEETCHI_API_BASE_URL = "https://api-preprod.leetchi.com/";
    public static final String partnerID = "example";
    public static final String keyPath = "example.pem";
    public static final String password  = "";

    private final URL baseLeetchiURL;
    private final KeyPair key;

    public LeetchiAPIExample() throws IOException {
        baseLeetchiURL = new URL(LEETCHI_API_BASE_URL);
        key = loadKey();
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        LeetchiAPIExample main = new LeetchiAPIExample();
        main.createUser();

    }

    private void createUser() throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, NoSuchProviderException, IOException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Map map = new HashMap();
        map.put("FirstName", "John");
        map.put("LastName", "Doe");
        map.put("Email", "john.doe@unknow.com");
        map.put("IP","127.0.0.1");
        map.put("CanRegisterMeanOfPayment", false);
        map.put("Nationality", "French");
        map.put("PersonType", "NATURAL_PERSON");
        JSONObject jsonObject = JSONObject.fromObject(map);
        request("users", HttpMethod.POST, jsonObject.toString());
    }

    private KeyPair loadKey() throws IOException {
        InputStream keyStream = getClass().getResourceAsStream(keyPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(keyStream));
        Security.addProvider(new BouncyCastleProvider());
        KeyPair kp = (KeyPair) new PEMReader(br, new PasswordFinder() {
            public char[] getPassword() {
                return password.toCharArray();
            }}).readObject();
        return kp;
    }

    private String createAuthSignature(HttpMethod httpMethod, String path, String body) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String data = String.format("%s|%s|", httpMethod, path);

        if (!HttpMethod.GET.equals(httpMethod)&& !HttpMethod.DELETE.equals(httpMethod))
            data += String.format("%s|", body);

        Signature signer = Signature.getInstance("SHA1withRSA", new BouncyCastleProvider());
        signer.initSign(key.getPrivate());
        signer.update(data.getBytes());
        byte[] result = signer.sign();
        return new String(Base64.encode(result));
    }

    private String buildRequestUrlPath(String path)
    {
        String resourcePath = trimSlashes(path);
        return String.format("/v1/partner/%s/%s?ts=%s", partnerID, resourcePath, System.currentTimeMillis());
    }

    private String trimSlashes(String string) {
        return string.replaceAll("(^/)|(/$)", "");
    }

    private void request(String path, HttpMethod httpMethod, String body) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        System.out.println(String.format("%s %s", httpMethod, path));
        String requestUrlPath = buildRequestUrlPath(path);
        String signature = createAuthSignature(httpMethod, requestUrlPath, body);

        System.out.println("signature = " + signature);
        URL url = new URL(baseLeetchiURL, requestUrlPath);
        String stringURL = url.toString();

        HttpClient client = new DefaultHttpClient();

        HttpUriRequest httpRequest = null;
        switch (httpMethod) {
            case GET :
                httpRequest = new HttpGet(stringURL);
                break;
            case POST :
                HttpPost httpPost = new HttpPost(stringURL);
                httpPost.setEntity(new StringEntity(body));
                httpRequest = httpPost;
                break;
            case PUT :
                HttpPut httpPut = new HttpPut(stringURL);
                httpPut.setEntity(new StringEntity(body));
                httpRequest = httpPut;
                break;
            case DELETE :
                httpRequest = new HttpDelete(stringURL);
                break;
        }

        httpRequest.setHeader("X-Leetchi-Signature", signature);
        httpRequest.setHeader("Content-Type", "application/json");

        HttpResponse response = client.execute(httpRequest);
        HttpEntity entity = response.getEntity();

        StatusLine status = response.getStatusLine();
        System.out.println("status = " + status);

        InputStream content = entity.getContent();
        String responseBody = IOUtils.toString(content, "UTF-8");
        System.out.println("responseBody = " + responseBody);
    }

    private enum HttpMethod {
        GET, POST, PUT, DELETE
    }

}
