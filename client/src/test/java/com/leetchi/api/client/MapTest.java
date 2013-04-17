package com.leetchi.api.client;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import com.leetchi.api.client.model.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class MapTest extends LeetchiTest {

    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("UserTestRules.properties").getPath());
        server.startAndWait();
    }

    @AfterClass
    public static void afterClass() {
        server.stopAndWait();
    }

    @Before
    public void before() throws Exception {
        super.before();
        Leetchi.config()
                .privateKey(pemFile)
                .partnerId("MyPartnerId")
                .baseUrl(LEETCHI_STUB_URL)
                .password(PASSWORD);
    }

    @Test
    public void createUserTest() throws Exception {
        Map<String, ?> user = Leetchi.create(User.PATH, userMap());

        assertThat(user.get("Email")).isEqualTo("mark@leetchi.com");
    }

    private Map<String, ?> userMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("FirstName", "Mark");
        map.put("LastName", "Zuckeberg");
        map.put("Email", "mark@leetchi.com");
        map.put("Nationality", "FR");
        map.put("PersonType", User.NATURAL_PERSON_TYPE);
        map.put("Tag", "Custom info from app");
        return map;
    }

    @Test
    public void fetchUserTest() throws Exception {
        Map<String, ?> user = Leetchi.fetch(User.path(User.PATH, 15L), Map.class);

        assertThat(user.get("Email")).isEqualTo("mark@leetchi.com");
    }

    @Test
    public void patchUserTest() throws Exception {
        Map<String, ?> user = Leetchi.patch(User.path(User.PATH, 15L), userMap());

        assertThat(user.get("Email")).isEqualTo("mark@leetchi.com");
    }

    @Test
    public void putUserTest() throws Exception {
        Map<String, ?> user = Leetchi.put(User.path(User.PATH, 15L), userMap());

        assertThat(user.get("Email")).isEqualTo("mark@leetchi.com");
    }
}
